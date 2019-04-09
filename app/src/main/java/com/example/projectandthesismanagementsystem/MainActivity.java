package com.example.projectandthesismanagementsystem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.configs.PrefConfig;
import com.example.projectandthesismanagementsystem.models.Name;
import com.example.projectandthesismanagementsystem.retrofit.ApiClient;
import com.example.projectandthesismanagementsystem.retrofit.ApiInterface;
import com.example.projectandthesismanagementsystem.signup.SignUp;

import java.util.ArrayList;

import br.com.mauker.materialsearchview.MaterialSearchView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    public static ApiInterface apiInterface;
    ArrayList<Name> suggestion=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();

    //widgets
    private MaterialSearchView materialSearchView;
    private Button mOpenButton;
    private Button mSignUp;
    private Button mSignIn;
    private Button mSingOut;
    private Button mSubmitWork;
    private TextView mTeachers;
    private TextView mStudents;
    private TextView mProjects;
    private ScrollView mScrollView;

    public static PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig=new PrefConfig(this);

        mTeachers=findViewById(R.id.teachers);
        mStudents=findViewById(R.id.students);
        mProjects=findViewById(R.id.projects);
        mProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Project Selected",Toast.LENGTH_SHORT).show();
            }
        });
        mSignUp=findViewById(R.id.main_signup_btn);
        mSignIn=findViewById(R.id.main_signin_btn);
        mSingOut=findViewById(R.id.main_signout_btn);
        materialSearchView=findViewById(R.id.search_view);
        mOpenButton=findViewById(R.id.buttonId);
        mScrollView=findViewById(R.id.scroll);
        mSubmitWork=findViewById(R.id.submit_work);

        if(prefConfig.readLoginStatus()){
            if(!prefConfig.readUser().equals("teachers")){
                mSubmitWork.setVisibility(View.VISIBLE);
            }
            mSingOut.setVisibility(View.VISIBLE);
            mSignIn.setVisibility(View.GONE);
            mSignUp.setVisibility(View.GONE);
        }else {

            mSubmitWork.setVisibility(View.GONE);
            mSingOut.setVisibility(View.GONE);
            mSignIn.setVisibility(View.VISIBLE);
            mSignUp.setVisibility(View.VISIBLE);
        }

        mSubmitWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SubmitActivity.class);
                startActivity(intent);
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        intiName();
    }

    private void getName(final String type){

        Call<ArrayList<Name>> call=apiInterface.getNames(type);
        call.enqueue(new Callback<ArrayList<Name>>() {
            @Override
            public void onResponse(Call<ArrayList<Name>> call, Response<ArrayList<Name>> response) {
                suggestion=response.body();
                init(type);
                Toast.makeText(MainActivity.this,type+" Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ArrayList<Name>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Database Failed! "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    private void intiName() {
        mTeachers.setTextColor(Color.RED);
        materialSearchView.clearAll();
        getName("Teachers");
        mTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeachers.setTextColor(Color.RED);
                mProjects.setTextColor(Color.WHITE);
                mStudents.setTextColor(Color.WHITE);
                materialSearchView.clearAll();
                materialSearchView.clearHistory();
                materialSearchView.openSearch();
                getName("Teachers");

            }
        });
        mStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeachers.setTextColor(Color.WHITE);
                mProjects.setTextColor(Color.WHITE);
                mStudents.setTextColor(Color.RED);
                materialSearchView.clearAll();
                materialSearchView.clearHistory();
                materialSearchView.openSearch();
                getName("Students");

            }
        });
        mProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeachers.setTextColor(Color.WHITE);
                mProjects.setTextColor(Color.RED);
                mStudents.setTextColor(Color.WHITE);
                materialSearchView.clearAll();
                materialSearchView.clearHistory();
                materialSearchView.openSearch();
                getName("Projects");

            }
        });

        mSingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefConfig.writeLoginStatus(false);
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }

    private void init(final String type) {
        names.clear();

        for(int i=0; i<suggestion.size(); i++){

            names.add(suggestion.get(i).getName());
        }

        mSignUp=findViewById(R.id.main_signup_btn);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        mOpenButton=findViewById(R.id.buttonId);
        mOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.scrollTo(0,330);
                if(!materialSearchView.isOpen()){
                    materialSearchView.openSearch();
                }
            }
        });

        materialSearchView.addSuggestions(names);
        materialSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String suggestion = materialSearchView.getSuggestionAtPosition(position);

                materialSearchView.setQuery(suggestion, true);

                Intent detailIntent=new Intent(MainActivity.this,Details.class);
                detailIntent.putExtra(getString(R.string.Details_person_name),suggestion);
                detailIntent.putExtra(getString(R.string.Type_name),type);
                if(!type.equals("Projects"))
                startActivity(detailIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (materialSearchView.isOpen()) {
            // Close the search on the back button press.
            materialSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}

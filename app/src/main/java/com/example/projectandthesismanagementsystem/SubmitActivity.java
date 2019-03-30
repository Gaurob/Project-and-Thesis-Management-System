package com.example.projectandthesismanagementsystem;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.models.Name;
import com.example.projectandthesismanagementsystem.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mDescription;
    private EditText mTitle;
    private Spinner mAdvisor;
    private Spinner mTechnology;
    private RadioGroup mPlatform;
    private RadioButton mRadioButton;
    private RadioButton mWeb;
    private RadioButton mAndoird;
    private RadioButton mIos;
    private EditText mGithubID;
    private Button mSubmitButton;



    private Integer teacher_id=null;
    private String technology=null;
    private String platform=null;
    private String adviser=null;

    //Lists
    ArrayList<Name> mNames=new ArrayList<>();
    List<String>list= new ArrayList<>();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        mDescription=findViewById(R.id.project_description);
        mTitle=findViewById(R.id.project_title);
        mGithubID=findViewById(R.id.github_id);
        mWeb=findViewById(R.id.radio_web);
        mWeb.setOnClickListener(this);
        mAndoird=findViewById(R.id.radio_android);
        mAndoird.setOnClickListener(this);
        mIos=findViewById(R.id.radio_ios);
        mIos.setOnClickListener(this);
        mAdvisor=findViewById(R.id.spinner_adviser);
        mTechnology=findViewById(R.id.spinner_technology);
        mPlatform=findViewById(R.id.radioPlatform);

        mSubmitButton=findViewById(R.id.submit_btn);
        mSubmitButton.setOnClickListener(this);
        init();

    }

    private void init() {
        Call<ArrayList<Name>> call=MainActivity.apiInterface.getNames("Teachers");
        call.enqueue(new Callback<ArrayList<Name>>() {
            @Override
            public void onResponse(Call<ArrayList<Name>> call, Response<ArrayList<Name>> response) {
                mProgressDialog.dismiss();
                mNames=response.body();
                for(int i=0; i<mNames.size(); i++){
                    list.add(mNames.get(i).toString());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SubmitActivity.this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mAdvisor.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Name>> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(SubmitActivity.this,"Database Failed! "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        mAdvisor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teacher_id=new Integer(-1);
                teacher_id=mNames.get(position).getTeacherID();
                adviser=new String();
                adviser=mNames.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SubmitActivity.this,"Have to select an adviser", Toast.LENGTH_LONG).show();
            }
        });


        mTechnology.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                technology=new String();
                technology=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void submitForm() {

        mProgressDialog.show();

        int platforms=mPlatform.getCheckedRadioButtonId();
        mRadioButton=findViewById(platforms);
        platform= (String) mRadioButton.getText();
        if(!platform.equals("Web")){
            technology="";
        }
        String desc=mDescription.getText().toString();
        String title=mTitle.getText().toString();
        String git=mGithubID.getText().toString();
        int student_id=MainActivity.prefConfig.readUserId();

        if(adviser==null || teacher_id==null ||  platform==null || desc.equals("") || title.equals("") || git.equals("") ){
            if(technology==null && platform.equals("Web")){
                Toast.makeText(SubmitActivity.this,"Every field must be selected!",Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
                return;
            }

        }

        Call<User> call=MainActivity.apiInterface.submitProject(title,adviser,desc,platform,technology,git,student_id,teacher_id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mProgressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body().getResponse().equals("ok")){
                        Toast.makeText(SubmitActivity.this,"Project Submitted ",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SubmitActivity.this,"Project Submission Error!",Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(SubmitActivity.this,"Submission Failed!",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_btn:
                submitForm();
                break;
            case R.id.radio_web:
                Toast.makeText(SubmitActivity.this,"Web",Toast.LENGTH_SHORT).show();
                mTechnology.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_android:
                Toast.makeText(SubmitActivity.this,"Android",Toast.LENGTH_SHORT).show();
                mTechnology.setVisibility(View.GONE);
                break;
            case R.id.radio_ios:
                Toast.makeText(SubmitActivity.this,"Ios",Toast.LENGTH_SHORT).show();
                mTechnology.setVisibility(View.GONE);
                break;
        }
    }
}

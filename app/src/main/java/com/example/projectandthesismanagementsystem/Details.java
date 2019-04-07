package com.example.projectandthesismanagementsystem;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.adapters.ProjectAdapter;
import com.example.projectandthesismanagementsystem.models.Project;
import com.example.projectandthesismanagementsystem.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {
    private TextView mName;
    private TextView mInstitution;
    private TextView mDept;
    private TextView mReg;
    private TextView mEmail;
    private TextView mPhone;
    private TextView mType;
    private User mUser;
    private LinearLayout mRegLay;

    ProgressDialog mProgressDialog;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mName=findViewById(R.id.student_deatils_name);
        mInstitution=findViewById(R.id.student_deatils_institution);
        mDept=findViewById(R.id.student_deatils_dept);
        mReg=findViewById(R.id.student_deatils_reg);
        mEmail=findViewById(R.id.student_deatils_email);
        mPhone=findViewById(R.id.student_deatils_phone);
        mType=findViewById(R.id.Student_details_type);

        recyclerView=findViewById(R.id.main_detail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRegLay=findViewById(R.id.reg_layout);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);


        String name= (String) getIntent().getExtras().get(getResources().getString(R.string.Details_person_name));
        String type= (String) getIntent().getExtras().get(getResources().getString(R.string.Type_name));
        if(type.equals("Students")){
            mType.setText("Student Details");
            mProgressDialog.show();
            getContents(type,name);
        }
        else if(type.equals("Teachers")){
            mType.setText("Teacher Details");
            mRegLay.setVisibility(View.GONE);
            getContents(type,name);
            mProgressDialog.show();
        }




    }

    private void getContents(final String type, String name) {
        Call<User>call=MainActivity.apiInterface.getUserDetails(name,type.toLowerCase());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    mUser=response.body();
                    init(type);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(Details.this,"Database Failed! "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(String type) {
        mProgressDialog.dismiss();



        mName.setText(mUser.getUserName());
        mInstitution.setText(mUser.getInstitution());
        mDept.setText(mUser.getDepartment());
        if(type.equals("Students")){
            mRegLay.setVisibility(View.VISIBLE);
            mReg.setText(mUser.getReg());
        }
        mEmail.setText(mUser.getEmail());
        mPhone.setText(mUser.getCell());
        ProjectAdapter adapter=new ProjectAdapter(mUser.getProjects(),Details.this);
        recyclerView.setAdapter(adapter);
    }
}

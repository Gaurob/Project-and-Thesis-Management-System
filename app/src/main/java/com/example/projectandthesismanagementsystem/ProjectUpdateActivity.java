package com.example.projectandthesismanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.models.Project;
import com.example.projectandthesismanagementsystem.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectUpdateActivity extends AppCompatActivity {

    private Project mProject;

    private TextView mName;
    private TextView mAdviser;
    private TextView mTitle;
    private TextView mDescription;
    private TextView mPlatform;
    private TextView mTechnology;
    private TextView mGithub;
    private RadioButton mFirst;
    private RadioButton mSecond;
    private RadioButton mThird;
    private RadioButton mAccepted;
    private TextView mComment;
    private Button mCommit_update;

    ProgressDialog mProgressDialog;


    Boolean mIsFirst=false;
    Boolean mIsSecond=false;
    Boolean mIsThird=false;
    Boolean mIsAccepted=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_update);

        mProject= (Project) getIntent().getSerializableExtra(getString(R.string.UPDATE_OBJECT));

        mName=findViewById(R.id.project_dtls_student_name_u);
        mAdviser=findViewById(R.id.project_dtls_advisor_id_u);
        mTitle=findViewById(R.id.project_dtls_project_title_u);
        mDescription=findViewById(R.id.project_dtls_description_u);
        mPlatform=findViewById(R.id.project_dtls_platform_u);
        mTechnology=findViewById(R.id.project_dtls_Technology_u);
        mGithub=findViewById(R.id.project_dtls_Github_u);
        mFirst=findViewById(R.id.update_first);
        mSecond=findViewById(R.id.update_second);
        mThird=findViewById(R.id.update_third);
        mComment=findViewById(R.id.update_comment);
        mAccepted=findViewById(R.id.update_accepted);
        mCommit_update=findViewById(R.id.update_btn);

        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);

        mCommit_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                updateUser();
            }
        });

        mName.setText(mProject.getStudentName());
        mAdviser.setText(mProject.getAdviser_name());
        mTitle.setText(mProject.getTitle());
        mDescription.setText(mProject.getDescription());
        mPlatform.setText(mProject.getPlatform());
        mTechnology.setText(mProject.getTechnology());
        mGithub.setText(mProject.getGithub());
        mComment.setText(mProject.getComment());
    }

    private void updateUser() {
        if(mFirst.isChecked()){
            mIsFirst=new Boolean(true);
        }
        if(mSecond.isChecked()){
            mIsSecond=new Boolean(true);
        }
        if(mThird.isChecked()){
            mIsThird=new Boolean(true);
        }
        if(mAccepted.isChecked()){
            mIsAccepted=new Boolean(true);
        }
        String updateComment=mComment.getText().toString();
        int projectId=mProject.getProject_id();

        Call<User>call=MainActivity.apiInterface.updateProject(projectId,mIsFirst,mIsSecond,mIsThird,mIsAccepted,updateComment);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mProgressDialog.dismiss();
                if(response.body().getResponse().equals("ok")){
                    Toast.makeText(ProjectUpdateActivity.this,"Updated!", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(ProjectUpdateActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(ProjectUpdateActivity.this,"Failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(ProjectUpdateActivity.this,t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}

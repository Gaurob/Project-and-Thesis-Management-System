package com.example.projectandthesismanagementsystem;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.models.Project;

import org.w3c.dom.Text;

public class ProjectDetailsActivity extends AppCompatActivity {

    private Project mProject;

    private TextView mName;
    private TextView mAdviser;
    private TextView mTitle;
    private TextView mDescription;
    private TextView mPlatform;
    private TextView mTechnology;
    private TextView mGithub;
    private ImageView mFirst;
    private ImageView mSecond;
    private ImageView mThird;
    private ImageView mAccepted;
    private TextView mComment;
    private Button mUpdate;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        mProject= (Project) getIntent().getSerializableExtra(getString(R.string.EXTRA_PROJECT));
        mName=findViewById(R.id.project_dtls_student_name);
        mAdviser=findViewById(R.id.project_dtls_advisor_id);
        mTitle=findViewById(R.id.project_dtls_project_title);
        mDescription=findViewById(R.id.project_dtls_description);
        mPlatform=findViewById(R.id.project_dtls_platform);
        mTechnology=findViewById(R.id.project_dtls_Technology);
        mGithub=findViewById(R.id.project_dtls_Github);
        mAccepted=findViewById(R.id.project_dtls_Accepted);
        mFirst=findViewById(R.id.first);
        mSecond=findViewById(R.id.second);
        mThird=findViewById(R.id.third);
        mComment=findViewById(R.id.project_dtls_comment);
        mUpdate=findViewById(R.id.update_btn_detail);

        if(MainActivity.prefConfig.readLoginStatus() && mProject.getTeacher_id() == MainActivity.prefConfig.readUserId()){
            mUpdate.setVisibility(View.VISIBLE);
        }
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProjectDetailsActivity.this,ProjectUpdateActivity.class);
                intent.putExtra(getString(R.string.UPDATE_OBJECT),mProject);
                startActivity(intent);
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

        if(mProject.getFirst_seg()==1){
            mFirst.setImageDrawable(getResources().getDrawable(R.drawable.baseline_done_white_18dp));
        }
        if(mProject.getSeconde_Seg()==1){
            mSecond.setImageDrawable(getResources().getDrawable(R.drawable.baseline_done_white_18dp));
        }
        if(mProject.getThird_Seg()==1){
            mThird.setImageDrawable(getResources().getDrawable(R.drawable.baseline_done_white_18dp));
        }
        if(mProject.getAccepted()==1){
            mAccepted.setImageDrawable(getResources().getDrawable(R.drawable.baseline_done_white_18dp));
        }

    }
}

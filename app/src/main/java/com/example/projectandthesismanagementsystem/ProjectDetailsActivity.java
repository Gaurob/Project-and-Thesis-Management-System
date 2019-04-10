package com.example.projectandthesismanagementsystem;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView mComment;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        mName=findViewById(R.id.project_dtls_student_name);
        mAdviser=findViewById(R.id.project_dtls_advisor_id);
        mTitle=findViewById(R.id.project_dtls_project_title);
        mDescription=findViewById(R.id.project_dtls_description);
        mPlatform=findViewById(R.id.project_dtls_platform);
        mTechnology=findViewById(R.id.project_dtls_Technology);
        mGithub=findViewById(R.id.project_dtls_Github);
        mFirst=findViewById(R.id.first);
        mSecond=findViewById(R.id.second);
        mThird=findViewById(R.id.third);
        mComment=findViewById(R.id.project_dtls_comment);

        mProject= (Project) getIntent().getSerializableExtra(getString(R.string.EXTRA_PROJECT));

        mName.setText(mProject.getStudentName());
        mAdviser.setText(mProject.getAdviser_name());
        mTitle.setText(mProject.getTitle());
        mDescription.setText(mProject.getDescription());
        mPlatform.setText(mProject.getPlatform());
        mTechnology.setText(mProject.getTechnology());
        mGithub.setText(mProject.getGithub());
        mComment.setText(mProject.getComment());

        if(mProject.getFirst_seg()){
            mFirst.setImageDrawable(getDrawable(R.drawable.baseline_done_white_18dp));
        }
        if(mProject.getSeconde_Seg()){
            mSecond.setImageDrawable(getDrawable(R.drawable.baseline_done_white_18dp));
        }
        if(mProject.getThird_Seg()){
            mThird.setImageDrawable(getDrawable(R.drawable.baseline_done_white_18dp));
        }

    }
}

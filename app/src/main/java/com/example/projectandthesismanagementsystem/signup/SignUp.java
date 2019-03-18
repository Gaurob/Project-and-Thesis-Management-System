package com.example.projectandthesismanagementsystem.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectandthesismanagementsystem.R;

public class SignUp extends AppCompatActivity {
    private Button mTeacher;
    private Button mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mTeacher=findViewById(R.id.teachers_signup);
        mTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,Teachers_SignUp.class);
                startActivity(intent);
            }
        });
        mStudent=findViewById(R.id.student_signup);
        mStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,Student_SignUp.class);
                startActivity(intent);
            }
        });
    }
}

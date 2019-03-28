package com.example.projectandthesismanagementsystem.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.MainActivity;
import com.example.projectandthesismanagementsystem.R;
import com.example.projectandthesismanagementsystem.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teachers_SignUp extends AppCompatActivity {

    private EditText mName;
    private EditText mIntitution;
    private EditText mDepartment;
    private EditText mCell;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mSignUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers__sign_up);
        mName=findViewById(R.id.teacher_name);
        mIntitution=findViewById(R.id.teacher_institution);
        mDepartment=findViewById(R.id.teacher_dept);
        mCell=findViewById(R.id.teacher_phone_no);
        mEmail=findViewById(R.id.teacher_email);
        mPassword=findViewById(R.id.teacher_password);
        mConfirmPassword=findViewById(R.id.teacher_password_confirm);
        mSignUpButton=findViewById(R.id.teacher_signup_btn);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupUser();
            }
        });

    }

    private void signupUser() {
        String name=mName.getText().toString();
        String insti=mIntitution.getText().toString();
        String dept=mDepartment.getText().toString();
        String cell=mCell.getText().toString();
        String email=mEmail.getText().toString();
        String pass=mPassword.getText().toString();
        String confirmpass=mConfirmPassword.getText().toString();
        if(!pass.equals(confirmpass)){
            return;
        }
        Call<User> call= MainActivity.apiInterface.performRegisterTeacher(email,pass,name,insti,dept,cell);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    if(response.body().getResponse().equals("ok")){
                        MainActivity.prefConfig.writeLoginStatus(true);
                        MainActivity.prefConfig.writeUser(response.body().getUser());
                        MainActivity.prefConfig.writeInsti(response.body().getInstitution());
                        MainActivity.prefConfig.writeUserId(response.body().getUserId());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(Teachers_SignUp.this,t.getMessage(),Toast.LENGTH_LONG);

            }
        });

    }
}

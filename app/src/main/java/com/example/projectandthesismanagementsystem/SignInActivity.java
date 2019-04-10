package com.example.projectandthesismanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.models.User;
import com.example.projectandthesismanagementsystem.signup.Student_SignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mSignInButton;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mEmail=findViewById(R.id.login_email);
        mPassword=findViewById(R.id.login_password);
        mSignInButton=findViewById(R.id.signIn_btn);

        mProgressDialog=new ProgressDialog(SignInActivity.this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                signInUser();
            }
        });
    }

    private void signInUser() {
        String email=mEmail.getText().toString();
        String pass=mPassword.getText().toString();
        if(email.equals("") || pass.equals("")){
            mProgressDialog.dismiss();
            Toast.makeText(SignInActivity.this, "Every field must be filled!",Toast.LENGTH_LONG).show();
            return;
        }

        Call<User> call=MainActivity.apiInterface.performLogin(email,pass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mProgressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body().getResponse().equals("ok")){
                        MainActivity.prefConfig.writeLoginStatus(true);
                        MainActivity.prefConfig.writeUser(response.body().getUser());
                        MainActivity.prefConfig.writeInsti(response.body().getInstitution());
                        MainActivity.prefConfig.writeUserId(response.body().getUserId());
                        Toast.makeText(SignInActivity.this,"Signed In!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignInActivity.this,MainActivity.class));
                    }
                    else {
                        Toast.makeText(SignInActivity.this,"Failed!", Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(SignInActivity.this,t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}

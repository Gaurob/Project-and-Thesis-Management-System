package com.example.projectandthesismanagementsystem.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.MainActivity;
import com.example.projectandthesismanagementsystem.R;
import com.example.projectandthesismanagementsystem.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_SignUp extends AppCompatActivity {

    private EditText Id,Name,Institution,Dept,Reg,Email,Password,Confim_Password,Phone;
    private Button Submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__sign_up);


        Name=findViewById(R.id.student_name);
        Institution=findViewById(R.id.student_institution);
        Dept=findViewById(R.id.student_dept);
        Reg=findViewById(R.id.student_reg);
        Email=findViewById(R.id.student_email);
        Password=findViewById(R.id.student_password);
        Confim_Password=findViewById(R.id.student_confirm_password);
        Phone=findViewById(R.id.student_phone_no);
        Submit=findViewById(R.id.student_reg_submit_id);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });
    }

    private void registerStudent() {
        String name=Name.getText().toString();
        String insti=Institution.getText().toString();
        String dept=Dept.getText().toString();
        final String reg=Reg.getText().toString();
        String email=Email.getText().toString();
        String pass=Password.getText().toString();
        String conPass=Confim_Password.getText().toString();
        String phone=Phone.getText().toString();

        if(name.equals("") || insti.equals("") || dept.equals("") || reg.equals("") || email.equals("") ||pass.equals("") ||
                phone.equals("")){
            Toast.makeText(Student_SignUp.this, "Every field must be filled!",Toast.LENGTH_LONG).show();
            return;

        }

        if(!pass.equals(conPass)){
            Toast.makeText(Student_SignUp.this,"Passwords Don't Match",Toast.LENGTH_LONG).show();
            return;
        }
        Call<User>call= MainActivity.apiInterface.performRegisterStudent(email,pass,name,insti,reg,dept,phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    if(response.body().getResponse().equals("ok")){
                        MainActivity.prefConfig.writeLoginStatus(true);
                        MainActivity.prefConfig.writeUser(response.body().getUser());
                        MainActivity.prefConfig.writeInsti(response.body().getInstitution());
                        MainActivity.prefConfig.writeUserId(response.body().getUserId());
                        Toast.makeText(Student_SignUp.this,"Registered!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Student_SignUp.this,MainActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Student_SignUp.this,"Failed! "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

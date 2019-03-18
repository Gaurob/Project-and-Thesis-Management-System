package com.example.projectandthesismanagementsystem;

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

    //Lists
    ArrayList<Name> mNames=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

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
        mTechnology=findViewById(R.id.spinner_adviser);
        mPlatform=findViewById(R.id.radioPlatform);

        mSubmitButton=findViewById(R.id.submit_btn);
        init();

        mSubmitButton.setOnClickListener(this);
    }

    private void init() {
        Call<ArrayList<Name>> call=MainActivity.apiInterface.getNames("Teachers");
        call.enqueue(new Callback<ArrayList<Name>>() {
            @Override
            public void onResponse(Call<ArrayList<Name>> call, Response<ArrayList<Name>> response) {
                mNames=response.body();
                List<String>list=new ArrayList<>();
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
                Toast.makeText(SubmitActivity.this,"Database Failed! "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        mAdvisor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teacher_id=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SubmitActivity.this,"Have to select an adviser", Toast.LENGTH_LONG).show();
            }
        });


        mTechnology.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                technology=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void submitForm() {

        int platforms=mPlatform.getCheckedRadioButtonId();
        mRadioButton=findViewById(platforms);
        platform= (String) mRadioButton.getText();
        String desc=mDescription.getText().toString();
        String title=mTitle.getText().toString();
        String git=mGithubID.getText().toString();

        if(teacher_id==null || technology==null || platform==null || desc.equals("") || title.equals("") || git.equals("") ){
            Toast.makeText(SubmitActivity.this,"Every field must be selected!",Toast.LENGTH_LONG).show();
            return;
        }




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_btn:
                submitForm();
            case R.id.radio_web:
                mTechnology.setVisibility(View.VISIBLE);
            case R.id.radio_android:
                mTechnology.setVisibility(View.GONE);
            case R.id.radio_ios:
                mTechnology.setVisibility(View.GONE);
        }
    }
}

package com.example.projectandthesismanagementsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandthesismanagementsystem.ProjectDetailsActivity;
import com.example.projectandthesismanagementsystem.R;
import com.example.projectandthesismanagementsystem.models.Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> {

    List<Project> myList;
    Context context;

    public ProjectAdapter(List<Project> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.project,viewGroup,false);
        Toast.makeText(context,"SSSS",Toast.LENGTH_LONG).show();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.project_name.setText(myList.get(i).getTitle());
        myViewHolder.student_name.setText(myList.get(i).getStudentName());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(), ProjectDetailsActivity.class);
                intent.putExtra(context.getString(R.string.EXTRA_PROJECT),  myList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView project_name;
        TextView student_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            project_name=itemView.findViewById(R.id.project_name);
            student_name=itemView.findViewById(R.id.project_student_name);
            cardView=itemView.findViewById(R.id.project_cardview);
        }
    }
}

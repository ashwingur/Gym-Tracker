package com.example.gymtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseNameAdapter extends RecyclerView.Adapter<ExerciseNameAdapter.MyViewHolder> {

    List<String> exerciseNames;
    Context context;
    EditText exerciseNameEt;

    public ExerciseNameAdapter(List<String> exerciseNames, Context context, EditText exerciseNameEt) {
        this.exerciseNames = exerciseNames;
        this.context = context;
        this.exerciseNameEt = exerciseNameEt;
    }

    public void setExerciseNames(List<String> exerciseNames) {
        this.exerciseNames = exerciseNames;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_name, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameTv.setText(exerciseNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the edit text
                ExerciseNameAdapter.this.exerciseNameEt.setText(exerciseNames.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseNames.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView nameTv;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.exercise_name_tv);
            parentLayout = itemView.findViewById(R.id.exercise_name_layout);
        }
    }
}

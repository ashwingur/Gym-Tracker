package com.example.gymtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExerciseProgressAdapter extends RecyclerView.Adapter<ExerciseProgressAdapter.MyViewHolderProgress> {

    List<Exercise> exercises;
    Context context;

    public ExerciseProgressAdapter(List<Exercise> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;
    }

    public void setExercises(List<Exercise> exercises){
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public MyViewHolderProgress onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_progress_layout, parent, false);
        MyViewHolderProgress holder = new MyViewHolderProgress(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderProgress holder, int position) {
        holder.dateTv.setText(new SimpleDateFormat("dd/MM/yyyy").format(exercises.get(position).unixTime));
        holder.weightTv.setText(Integer.toString(exercises.get(position).weight));
        holder.setsTv.setText(Integer.toString(exercises.get(position).sets));
        holder.repsTv.setText(Integer.toString(exercises.get(position).reps));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the edit text
                //ExerciseProgressAdapter.this.exerciseNameEt.setText(exerciseNames.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }


    public class MyViewHolderProgress extends RecyclerView.ViewHolder{
        TextView dateTv, weightTv, repsTv, setsTv;
        ConstraintLayout parentLayout;

        public MyViewHolderProgress(@NonNull View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.date_progress_tv);
            weightTv = itemView.findViewById(R.id.weight_progress_tv);
            repsTv = itemView.findViewById(R.id.reps_progress_tv);
            setsTv = itemView.findViewById(R.id.sets_progress_tv);
            parentLayout = itemView.findViewById(R.id.exercise_progress_layout);
        }
    }
}

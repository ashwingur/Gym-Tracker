package com.example.gymtracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
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
    AlertDialog.Builder builder;
    ExerciseProgress exerciseProgress;

    public ExerciseProgressAdapter(List<Exercise> exercises, Context context, ExerciseProgress exerciseProgress) {
        this.exercises = exercises;
        this.context = context;
        this.builder = new AlertDialog.Builder(context);
        this.exerciseProgress = exerciseProgress;
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

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                ExerciseDataBaseHelper dataBaseHelper = new ExerciseDataBaseHelper(ExerciseProgressAdapter.this.context);
                                int actualPosition = holder.getAdapterPosition();
                                dataBaseHelper.deleteOne(exercises.get(actualPosition));
                                Exercise e = exercises.remove(actualPosition);
                                exerciseProgress.exercises.remove(e);
                                ExerciseProgressAdapter.this.notifyItemRemoved(actualPosition);

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                builder.setMessage("Delete this entry?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return false;
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

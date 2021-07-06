package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExerciseLogger extends AppCompatActivity {
    public Set<String> exerciseSet;
    public List<String> exerciseList;
    public static final String SHARED_PREFS = "SHARED_PREFS";
    public static final String EXERCISES = "EXERCISES";

    public Button addBtn;
    public EditText exerciseEt, weightEt, setsEt, repsEt;
    public RecyclerView recyclerView;
    public ExerciseDataBaseHelper exerciseDataBaseHelper;

    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager layoutManager;

    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_logger);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        exerciseSet = new HashSet<String>(sharedPreferences.getStringSet(EXERCISES, new HashSet<String>()));

        addBtn = findViewById(R.id.add_btn);
        exerciseEt = findViewById(R.id.exercise_et);
        weightEt = findViewById(R.id.weight_et);
        setsEt = findViewById(R.id.sets_et);
        repsEt = findViewById(R.id.reps_et);

        exerciseDataBaseHelper = new ExerciseDataBaseHelper(ExerciseLogger.this);
        exerciseList = new ArrayList<String>(exerciseSet);

        Log.d("Exercise", exerciseList.toString());

        // Setting the recycler
        recyclerView = findViewById(R.id.exercise_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ExerciseNameAdapter(exerciseList, this, exerciseEt);
        recyclerView.setAdapter(mAdapter);



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });

    }

    public void addExercise(){
        Log.d("Exercise", "addExercise called");
        if (TextUtils.isEmpty(exerciseEt.getText()) || TextUtils.isEmpty(weightEt.getText()) ||
            TextUtils.isEmpty(setsEt.getText()) || TextUtils.isEmpty(repsEt.getText())){
            Toast.makeText(this, "Missing Data", Toast.LENGTH_SHORT).show();
            return;
        }

        Exercise exercise = new Exercise(-1,exerciseEt.getText().toString(), Integer.parseInt(weightEt.getText().toString()),
                Integer.parseInt(repsEt.getText().toString()), Integer.parseInt(setsEt.getText().toString()), System.currentTimeMillis());
        boolean result = exerciseDataBaseHelper.addOne(exercise);
        if (result){
            boolean isNew = exerciseSet.add(exerciseEt.getText().toString());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet(EXERCISES, new HashSet<String>(exerciseSet));
            editor.apply();

            if (isNew){
                // Also add to exercise list so Recycler updates
                exerciseList.add(exerciseEt.getText().toString());
                mAdapter.notifyItemInserted(exerciseList.size() - 1);
            }

            Log.d("Exercise", "exerciseSet: " + exerciseSet.toString());
            Toast.makeText(this, String.format("Added exercise: %s", exercise.getName()), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, String.format("Added exercise: %s", exerciseEt.getText().toString()), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: Failed to add exercise", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateRecyclerView(){

    }
}
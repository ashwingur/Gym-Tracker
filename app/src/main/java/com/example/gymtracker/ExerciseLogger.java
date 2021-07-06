package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class ExerciseLogger extends AppCompatActivity {
    public Set<String> exerciseSet;
    public static final String SHARED_PREFS = "SHARED_PREFS";
    public static final String EXERCISES = "EXERCISES";

    public Button addBtn;
    public EditText exerciseEt, weightEt, setsEt, repsEt;
    public RecyclerView recyclerView;
    public ExerciseDataBaseHelper exerciseDataBaseHelper;

    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_logger);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        exerciseSet = sharedPreferences.getStringSet(EXERCISES, null);
        if (exerciseSet == null){
            exerciseSet = new HashSet<String>();
        }

        addBtn = findViewById(R.id.add_btn);
        exerciseEt = findViewById(R.id.exercise_et);
        weightEt = findViewById(R.id.weight_et);
        setsEt = findViewById(R.id.sets_et);
        repsEt = findViewById(R.id.reps_et);

        exerciseDataBaseHelper = new ExerciseDataBaseHelper(ExerciseLogger.this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });

    }

    public void addExercise(){
        if (TextUtils.isEmpty(exerciseEt.getText()) || TextUtils.isEmpty(weightEt.getText()) ||
            TextUtils.isEmpty(setsEt.getText()) || TextUtils.isEmpty(repsEt.getText())){
            Toast.makeText(this, "Missing Data", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("Exercise", "Here");
        Exercise exercise = new Exercise(-1,exerciseEt.getText().toString(), Integer.parseInt(weightEt.getText().toString()),
                Integer.parseInt(repsEt.getText().toString()), Integer.parseInt(setsEt.getText().toString()), System.currentTimeMillis());
        Log.d("Exercise", "Here1");
        boolean result = exerciseDataBaseHelper.addOne(exercise);
        Log.d("Exercise", String.format("Result: %b, %s", result, exercise.toString()));
        if (result){
            exerciseSet.add(exerciseEt.getText().toString());
            sharedPreferences.edit().putStringSet(EXERCISES,exerciseSet);
            Toast.makeText(this, String.format("Added exercise: %s", exercise.toString()), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, String.format("Added exercise: %s", exerciseEt.getText().toString()), Toast.LENGTH_SHORT).show();
            Log.d("Exercise", "Made toast because result is true");
        } else {
            Toast.makeText(this, "Error: Failed to add exercise", Toast.LENGTH_SHORT).show();
        }

    }
}
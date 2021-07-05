package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
        Log.d("ButtonTest", "This msg is run");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });
    }

    public void addExercise(){
        Log.d("ButtonTest", "This button is run");
        if (TextUtils.isEmpty(exerciseEt.getText()) || TextUtils.isEmpty(weightEt.getText()) ||
            TextUtils.isEmpty(setsEt.getText()) || TextUtils.isEmpty(repsEt.getText())){
            Toast.makeText(this, "Missing Data", Toast.LENGTH_SHORT).show();
            return;
        }
        exerciseSet.add(exerciseEt.getText().toString());
        Toast.makeText(this, String.format("Added exercise: %s", exerciseEt.getText().toString()), Toast.LENGTH_SHORT).show();
    }
}
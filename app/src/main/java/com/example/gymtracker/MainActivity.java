package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button logExerciseBtn, viewExerciseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logExerciseBtn = findViewById(R.id.logExerciseBtn);
        viewExerciseBtn = findViewById(R.id.viewExerciseBtn);

        logExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logExercise();
            }
        });

        viewExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewExercise();
            }
        });
    }

    public void logExercise(){
        Intent intent = new Intent(MainActivity.this, ExerciseLogger.class);
        MainActivity.this.startActivity(intent);
    }

    public void viewExercise(){
        Intent intent = new Intent(MainActivity.this, ExerciseProgress.class);
        MainActivity.this.startActivity(intent);
    }


}
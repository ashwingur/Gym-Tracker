package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button logExerciseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logExerciseBtn = findViewById(R.id.logExerciseBtn);

        logExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logExercise();
            }
        });
    }

    public void logExercise(){
        Intent intent = new Intent(MainActivity.this, ExerciseLogger.class);
        MainActivity.this.startActivity(intent);
    }
}
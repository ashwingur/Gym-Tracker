package com.example.gymtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ExerciseProgress extends AppCompatActivity{

    private Menu menu;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;

    private RecyclerView recyclerView;
    public ExerciseProgressAdapter mAdapter;
    public RecyclerView.LayoutManager layoutManager;

    private List<String> exerciseNames;
    private List<Exercise> exercises;

    ExerciseDataBaseHelper dataBaseHelper;

    public static final String SHARED_PREFS = "SHARED_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_progress);

        exerciseNames = new ArrayList<String>(getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getStringSet(ExerciseLogger.EXERCISES, new HashSet<>()));
        Collections.sort(exerciseNames);
        spinner = findViewById(R.id.exerciseProgressSpinner);
        arrayAdapter = new ArrayAdapter<String>(ExerciseProgress.this, android.R.layout.simple_spinner_item, exerciseNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                exercises = dataBaseHelper.getExerciseByName(exerciseNames.get(position));
                mAdapter.setExercises(exercises);
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dataBaseHelper = new ExerciseDataBaseHelper(ExerciseProgress.this);
        if (exerciseNames.size() > 0){
            exercises = dataBaseHelper.getExerciseByName(exerciseNames.get(0));
        } else {
            exercises = new ArrayList<Exercise>();
        }

        // Setting the recycler
        recyclerView = findViewById(R.id.exerciseProgressRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ExerciseProgressAdapter(exercises, this);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Set the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exercise_progress_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.date_latest_menu:
                Toast.makeText(ExerciseProgress.this, "Sort date latest", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.date_earliest_menu:
                Toast.makeText(ExerciseProgress.this, "Sort date earliest", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reps_ascending_menu:
                Toast.makeText(ExerciseProgress.this, "Sort reps ascending", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reps_descending_menu:
                Toast.makeText(ExerciseProgress.this, "Sort reps descendig", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.weight_ascend_menu:
                Toast.makeText(ExerciseProgress.this, "Sort weight ascending", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.weight_descend_menu:
                Toast.makeText(ExerciseProgress.this, "Sort weight descending", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
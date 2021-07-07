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

    public List<String> exerciseNames;
    public List<Exercise> exercises;

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
        mAdapter = new ExerciseProgressAdapter(exercises, this, this);
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
                Toast.makeText(ExerciseProgress.this, "Sort by latest date", Toast.LENGTH_SHORT).show();
                Collections.sort(exercises, Exercise.ExerciseDateLatestComparator);
                break;
            case R.id.date_earliest_menu:
                Collections.sort(exercises, Exercise.ExerciseDateEarliestComparator);
                Toast.makeText(ExerciseProgress.this, "Sort by earliest date", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reps_ascending_menu:
                Collections.sort(exercises, Exercise.ExerciseRepsAscendingComparator);
                Toast.makeText(ExerciseProgress.this, "Sort by reps ascending", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reps_descending_menu:
                Collections.sort(exercises, Exercise.ExerciseRepsDescendingComparator);
                Toast.makeText(ExerciseProgress.this, "Sort by reps descending", Toast.LENGTH_SHORT).show();
                break;
            case R.id.weight_ascend_menu:
                Toast.makeText(ExerciseProgress.this, "Sort by weight ascending", Toast.LENGTH_SHORT).show();
                Collections.sort(exercises, Exercise.ExerciseWeightAscendingComparator);
                break;
            case R.id.weight_descend_menu:
                Collections.sort(exercises, Exercise.ExerciseWeightDescendingComparator);
                Toast.makeText(ExerciseProgress.this, "Sort by weight descending", Toast.LENGTH_SHORT).show();
                break;
        }
        mAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }
}
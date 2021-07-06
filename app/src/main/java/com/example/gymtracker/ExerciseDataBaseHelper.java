package com.example.gymtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExerciseDataBaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "exercise.db";
    public static final String EXERCISE_TABLE = "EXERCISE_TABLE";
    public static final String EXERCISE_NAME = "EXERCISE_NAME";
    public static final String EXERCISE_WEIGHT = "EXERCISE_WEIGHT";
    public static final String EXERCISE_REPS = "EXERCISE_REPS";
    public static final String EXERCISE_SETS = "EXERCISE_SETS";
    public static final String EXERCISE_DATE = "EXERCISE_DATE";
    public static final String EXERCISE_ID = "EXERCISE_ID";

    public ExerciseDataBaseHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INT, %s INT, %s INT, %s INT)",
                EXERCISE_TABLE, EXERCISE_ID, EXERCISE_NAME, EXERCISE_DATE, EXERCISE_WEIGHT, EXERCISE_REPS, EXERCISE_SETS);


        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Exercise exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EXERCISE_NAME, exercise.getName());
        contentValues.put(EXERCISE_REPS, exercise.getReps());
        contentValues.put(EXERCISE_WEIGHT, exercise.getWeight());
        contentValues.put(EXERCISE_SETS, exercise.getSets());
        contentValues.put(EXERCISE_DATE, exercise.getUnixTime());

        long result = db.insert(EXERCISE_TABLE, null, contentValues);

        return result == -1 ? false : true;
    }
}

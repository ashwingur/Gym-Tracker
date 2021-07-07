package com.example.gymtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        db.close();

        return result == -1 ? false : true;
    }

    public boolean deleteOne(Exercise exercise){
        Log.d("Exercise", "Deleting exercise " + exercise);
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + EXERCISE_TABLE + " WHERE " + EXERCISE_ID + " = " + exercise.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        Log.d("Exercise", "Deleting exercise " + exercise + " result: " + cursor.moveToFirst());
        return cursor.moveToFirst();
    }

    public List<Exercise> getAllExercises(){
        List<Exercise> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + EXERCISE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            // Loop through all results and create new customer object for each
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                long unixTime = cursor.getLong(2);
                int weight = cursor.getInt(3);
                int reps = cursor.getInt(4);
                int sets = cursor.getInt(5);
                returnList.add(new Exercise(id, name, weight, reps, sets, unixTime));
            } while (cursor.moveToNext());

        } else {
            // Nothing found
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<Exercise> getExerciseByName(String exerciseName){
        List<Exercise> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + EXERCISE_TABLE + " WHERE " + EXERCISE_NAME + " LIKE '" + exerciseName + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            // Loop through all results and create new customer object for each
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                long unixTime = cursor.getLong(2);
                int weight = cursor.getInt(3);
                int reps = cursor.getInt(4);
                int sets = cursor.getInt(5);
                returnList.add(new Exercise(id, name, weight, reps, sets, unixTime));
            } while (cursor.moveToNext());

        } else {
            // Nothing found
        }
        cursor.close();
        db.close();
        return returnList;
    }
}

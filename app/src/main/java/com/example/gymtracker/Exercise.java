package com.example.gymtracker;

import java.util.Comparator;
import java.util.Date;

public class Exercise {

    public int id;
    public String name;
    public int weight;
    public int reps;
    public int sets;
    public long unixTime;
    public Date date;

    public Exercise(int id, String name, int weight, int reps, int sets, long unixTime) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.unixTime = unixTime;
        this.date = new Date(unixTime);
    }

    public static Comparator<Exercise> ExerciseDateLatestComparator = new Comparator<Exercise>() {
        @Override
        public int compare(Exercise e1, Exercise e2) {
            return (int) (e1.unixTime - e2.unixTime);
        }
    };

    public static Comparator<Exercise> ExerciseDateEarliestComparator = new Comparator<Exercise>() {
        @Override
        public int compare(Exercise e1, Exercise e2) {
            return (int) (e2.unixTime - e1.unixTime);
        }
    };

    public static Comparator<Exercise> ExerciseWeightDescendingComparator = new Comparator<Exercise>() {
        @Override
        public int compare(Exercise e1, Exercise e2) {
            int result = (int) (e2.weight - e1.weight);
            if (result == 0){
                // If they are the same, then sort by date
                return ExerciseDateLatestComparator.compare(e1, e2);
            }
            return result;
        }
    };

    public static Comparator<Exercise> ExerciseWeightAscendingComparator = new Comparator<Exercise>() {
        @Override
        public int compare(Exercise e1, Exercise e2) {
            int result = (int) (e1.weight - e2.weight);
            if (result == 0){
                // If they are the same, then sort by date
                return ExerciseDateLatestComparator.compare(e1, e2);
            }
            return result;
        }
    };

    public static Comparator<Exercise> ExerciseRepsAscendingComparator = new Comparator<Exercise>() {
        @Override
        public int compare(Exercise e1, Exercise e2) {
            int result = (int) (e1.reps - e2.reps);
            if (result == 0){
                // If they are the same, then sort by date
                return ExerciseDateLatestComparator.compare(e1, e2);
            }
            return result;
        }
    };

    public static Comparator<Exercise> ExerciseRepsDescendingComparator = new Comparator<Exercise>() {
        @Override
        public int compare(Exercise e1, Exercise e2) {
            int result = (int) (e2.reps - e1.reps);
            if (result == 0){
                // If they are the same, then sort by date
                return ExerciseDateLatestComparator.compare(e1, e2);
            }
            return result;
        }
    };


    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", reps=" + reps +
                ", sets=" + sets +
                ", date=" + date.toString() +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public Date getDate() {
        return date;
    }
}

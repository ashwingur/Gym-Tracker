package com.example.gymtracker;

import java.util.Date;

public class Exercise {

    public String name;
    public int weight;
    public int reps;
    public int sets;
    public long unixTime;
    public Date date;

    public Exercise(String name, int weight, int reps, int sets, long unixTime) {
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.unixTime = unixTime;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", reps=" + reps +
                ", sets=" + sets +
                ", date=" + date.toString() +
                '}';
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

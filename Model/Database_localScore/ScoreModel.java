package com.example.test3;

public class ScoreModel {

    private int eScore;
    private int mScore;
    private int hScore;

    private int id;

    public ScoreModel (int id, int eScore, int mScore,int hScore) {
        this.eScore = eScore;
        this.mScore = mScore;
        this.hScore = hScore;
        this.id = id;
    }

    public void setScore(int score) {
        this.eScore = score;
    }

    public int getEScore() {
        return eScore;
    }
    public int getMScore() {
        return mScore;
    }
    public int getHScore() {
        return hScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
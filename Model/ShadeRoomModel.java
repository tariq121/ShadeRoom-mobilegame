package com.example.test3;

import java.util.Random;

public class ShadeRoomModel {
    public static  int num_rows;
    public static  int num_cols;
    private boolean[][] lights;
    private int score = 0;

    public ShadeRoomModel() { lights = new boolean[num_rows][num_cols];}
    public ShadeRoomModel(int r,int c) {
        this.num_rows=r;
        this.num_cols=c;
        lights = new boolean[num_rows][num_cols];
    }

    public void setRow(int r){
        this.num_rows=r;
    }
    public void setColumn(int c){
        this.num_cols=c;
    }

    public void newGame(){
        Random rnd = new Random();
        int randRow = rnd.nextInt(num_rows);
        int randCol = rnd.nextInt(num_cols);
        for( int row = 0; row<num_rows; row++){
            for(int col = 0; col< num_cols; col++){
                if(row == randRow && col == randCol){
                    lights[row][col] = true;
                }else {
                    lights[row][col] = false;
                }
            }
        }
    }

    public boolean isLightsOn(int row, int col){return lights[row][col]; }

    public int getScore(){return score;}
    public void resetScore(){this.score = 0;}
    public void setScore(int sec){this.score += sec;}





}
package com.example.test3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HardActivity extends Activity {
    private ShadeRoomModel mGame = new ShadeRoomModel(3,3);
    private Button[][] mButton;
    private int mOnColor;
    private int mOffColor;
    TextView scoreText;
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds;
    private long totalTime = 1500;
    private boolean timerRunning;
    private int offSet = 0x10000000;
    private DatabaseHelper db = new DatabaseHelper(HardActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hard);
        db.addOne(new ScoreModel(-1,0,0,0));

        mOnColor = getRandomColor();
        mOffColor = (mOnColor+offSet);
        mButton = new Button[mGame.num_rows][mGame.num_cols];
        timeLeftInMilliseconds = totalTime;
        scoreText = (TextView) findViewById(R.id.Score);
        countdownText = (TextView) findViewById(R.id.Timer);

        GridLayout gridlayout = findViewById(R.id.light_grid);
        int childIndex = 0;
        for(int row = 0; row < mGame.num_rows; row++){
            for(int col = 0; col < mGame.num_cols; col++){
                mButton[row][col] = (Button)gridlayout.getChildAt(childIndex);
                childIndex++;
            }
        }

        StartGame();

    }

    private void StartGame(){
        mGame.newGame();
        setColors();
        startTimer();
        timerRunning = true;
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(128, rnd.nextInt(255-220+1)+220, rnd.nextInt(255-220+1)+220, rnd.nextInt(255-220+1)+220);
    }

    public void onLightClick(View view){
        boolean buttonFound = false;
        for(int row = 0; row < mGame.num_rows && !buttonFound; row++) {
            for (int col = 0; col < mGame.num_cols && !buttonFound; col++) {
                if(view == mButton[row][col]){
                    if((mGame.isLightsOn(row,col))){
                        setScoreText();
                        resetTimer();
                        setNewGame(view);
                    }else{
                        ShowPopup();
                    }
                    buttonFound = true;
                }
            }
        }

    }

    public void setNewGame(View view){StartGame();}

    private void setColors(){
        mOnColor = getRandomColor();
        mOffColor = (mOnColor+offSet);
        for(int row = 0; row < mGame.num_rows; row++){
            for(int col = 0; col < mGame.num_cols; col++){
                if(mGame.isLightsOn(row,col)){
                    mButton[row][col].setBackgroundColor(mOnColor);
                }else{
                    mButton[row][col].setBackgroundColor(mOffColor);
                }
            }
        }
    }

    public void setScoreText(){
        int seconds = (int) timeLeftInMilliseconds/100;
        mGame.setScore(seconds);
        scoreText.setText("Score: "+ mGame.getScore());
    }

    public void startTimer () {
        if (timerRunning){countDownTimer.cancel();}
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 100) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                checkTimer();
            }
        }.start();
    }

    public void resetTimer(){
        countDownTimer.cancel();
        timeLeftInMilliseconds = totalTime;
        countDownTimer.start();
    }

    public void updateTimer(){
        countdownText.setText("Time: "+(timeLeftInMilliseconds/100));
    }

    public void checkTimer(){
        if(!timerRunning){
            timerRunning = true;
            ShowPopup();
        }
    }

    private PopupWindow POPUP_WINDOW_SCORE = null;

    private void ShowPopup() {
        countDownTimer.cancel();
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_window, null);

        // Creating the PopupWindow
        POPUP_WINDOW_SCORE = new PopupWindow(this);
        POPUP_WINDOW_SCORE.setContentView(layout);
        POPUP_WINDOW_SCORE.setWidth(width);
        POPUP_WINDOW_SCORE.setHeight(height);
        POPUP_WINDOW_SCORE.setFocusable(true);

        // prevent clickable background
        POPUP_WINDOW_SCORE.setBackgroundDrawable(null);

        POPUP_WINDOW_SCORE.showAtLocation(layout, Gravity.CENTER, 1, 1);

        TextView txtMessage = (TextView) layout.findViewById(R.id.layout_popup_scoreMessage);
        txtMessage.setText("Score:"+mGame.getScore());
        if(mGame.getScore()>db.getHDataScore()) {
            db.updateDataScore(mGame.getScore(),3);
        }

        // Getting a reference to button one and do something
        Button butRestart = (Button) layout.findViewById(R.id.layout_popup_butRestart);
        butRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Something
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                //Close Window
                POPUP_WINDOW_SCORE.dismiss();
            }
        });

        // Getting a reference to button two and do something
        Button butHome = (Button) layout.findViewById(R.id.layout_popup_butHome);
        butHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Something
                Intent intent = new Intent(HardActivity.this, MainActivity.class);
                startActivity(intent);
                //Close Window
                POPUP_WINDOW_SCORE.dismiss();
            }
        });

        // Getting a reference to button three and do something
        Button butScore = (Button) layout.findViewById(R.id.layout_popup_butScore);
        butScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Something
                Intent intent = new Intent(HardActivity.this, ScoreActivity.class);
                startActivity(intent);
                //Close Window
                POPUP_WINDOW_SCORE.dismiss();
            }
        });
    }

    public void switchToHome(View view){
        countDownTimer.cancel();
        Intent intent = new Intent(HardActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void pauseGame(View view) {
        countDownTimer.cancel();
        countdownText.setText("Time: " + (timeLeftInMilliseconds / 100));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_imageview, null);
        builder.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 100) {
                    @Override
                    public void onTick(long l) {
                        timeLeftInMilliseconds = l;
                        updateTimer();
                    }

                    @Override
                    public void onFinish() {
                        timerRunning = false;
                        checkTimer();

                    }
                }.start();

            }
        });
        builder.setView(dialogLayout);
        builder.show();
    }
}
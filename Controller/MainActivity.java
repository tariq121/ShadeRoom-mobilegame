package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int screenHeight;
    private int screenWidth;

    private ImageView redBox;
    private ImageView yellowBox;
    private ImageView blueBox;
    private ImageView greenBox;
    private ImageView orangeBox;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private float redX;
    private float redY;
    private float yellowX;
    private float yellowY;
    private float blueX;
    private float blueY;
    private float greenX;
    private float greenY;
    private float orangeX;
    private float orangeY;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redBox = (ImageView) findViewById(R.id.redSquare);
        yellowBox = (ImageView) findViewById(R.id.yellowSquare);
        orangeBox = (ImageView) findViewById(R.id.orangeSquare);
        greenBox = (ImageView) findViewById(R.id.greenSquare);
        blueBox = (ImageView) findViewById(R.id.darkblueSquare);

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        screenHeight = p.y;
        screenWidth = p.x;

        redBox.setX(-80.0f);
        redBox.setY(screenHeight + 80.0f);

        yellowBox.setX(-80.0f);
        yellowBox.setY(screenHeight + 80.0f);

        greenBox.setX(-80.0f);
        greenBox.setY(screenHeight + 80.0f);

        blueBox.setX(-80.0f);
        blueBox.setY(screenHeight + 80.0f);

        orangeBox.setX(-80.0f);
        orangeBox.setY(screenHeight + 80.0f);

        timer.schedule(new TimerTask() {
            @Override

            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run(){
                        changePosition();
                    }
                });

            }
        },0,20);



    }


    public void changePosition() {

        redY+=10;
        if (redBox.getY() > screenHeight ) {
            redX = (float) Math.floor(Math.random() *(screenWidth-redBox.getWidth()));
            redY = -100.0f;
        }
        redBox.setX(redX);
        redBox.setY(redY);

        yellowY+=5;
        if (yellowBox.getY() > screenHeight ) {
            yellowX = (float) Math.floor(Math.random() *(screenWidth-yellowBox.getWidth()));
            yellowY = -100.0f;
        }
        yellowBox.setX(yellowX);
        yellowBox.setY(yellowY);

        blueY+=13;
        if (blueBox.getY() > screenHeight ) {
            blueX = (float) Math.floor(Math.random() *(screenWidth-blueBox.getWidth()));
            blueY = -100.0f;
        }
        blueBox.setX(blueX);
        blueBox.setY(blueY);

        greenY+=7;
        if (greenBox.getY() > screenHeight ) {
            greenX = (float) Math.floor(Math.random() *(screenWidth-greenBox.getWidth()));
            greenY = -100.0f;
        }
        greenBox.setX(greenX);
        greenBox.setY(greenY);

        orangeY+=9;
        if (orangeBox.getY() > screenHeight ) {
            orangeX = (float) Math.floor(Math.random() *(screenWidth-orangeBox.getWidth()));
            orangeY = -100.0f;
        }
        orangeBox.setX(orangeX);
        orangeBox.setY(orangeY);


    }


    public void switchToEasy(View view){
        Intent intent = new Intent(MainActivity.this, EasyActivity.class);
        startActivity(intent);
    }
    public void switchToMedium(View view){
        Intent intent = new Intent(MainActivity.this, MediumActivity.class);
        startActivity(intent);
    }
    public void switchToHard(View view){
        Intent intent = new Intent(MainActivity.this, HardActivity.class);
        startActivity(intent);
    }
    public void switchToScore(View view){
        Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
        startActivity(intent);
    }

}
package com.example.test3;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;


public class ScoreActivity extends Activity {
    //private DatabaseHelper db;

    //Text views to be edited
    TextView easyScoreText;
    TextView mediumScoreText;
    TextView hardScoreText;
    private DatabaseHelper db = new DatabaseHelper(ScoreActivity.this);;
    private ScoreModel initModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score);

        easyScoreText = (TextView) findViewById(R.id.easy_score);
        mediumScoreText = (TextView) findViewById(R.id.medium_score);
        hardScoreText = (TextView) findViewById(R.id.hard_score);
        initModel = new ScoreModel(-1,0,0,0);
        db.addOne(initModel);
        //Getdatabase
        //db = new DatabaseHelper(ScoreActivity.this);


        easyScoreText.setText("Easy score: " + db.getEDataScore());
        mediumScoreText.setText("Medium score: " + db.getMDataScore());
        hardScoreText.setText("Hard score: " + db.getHDataScore());
    }


    @Override
    protected void onResume() {
        super.onResume();
        easyScoreText.setText("Easy score: " + db.getEDataScore());
        mediumScoreText.setText("Medium score: " + db.getMDataScore());
        hardScoreText.setText("Hard score: " + db.getHDataScore());
    }


}
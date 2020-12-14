package com.example.test3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.ScriptC;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SCORE_TABLE ="SCORE_TABLE";
    public static final String EASY_SCORE = "EASYSCORE";
    public static final String MED_SCORE = "MEDSCORE";
    public static final String HARD_SCORE = "HARDSCORE";
    public static final String ID = "ID";

    public DatabaseHelper(@Nullable Context context) {super(context, "database_score.db",null,1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE " + SCORE_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EASY_SCORE + " INT, " + MED_SCORE + " INT, " + HARD_SCORE + " INT) " ;

        db.execSQL(statement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(ScoreModel model) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EASY_SCORE, model.getEScore());
        cv.put(MED_SCORE, model.getMScore());
        cv.put(HARD_SCORE, model.getHScore());


        long insert = db.insert(SCORE_TABLE,null,cv);
        if (insert>=0) {
            return true;
        }
        return false;
    }

    public int getEDataScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        int result =-1;
        String statement = "SELECT " +EASY_SCORE + " FROM " + SCORE_TABLE + " ORDER BY " + ID + " LIMIT 1";
        Cursor cursor = db.rawQuery(statement,null);
        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        db.close();
        cursor.close();
        return result;
    }
    public int getMDataScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        int result =-1;
        String statement = "SELECT " +MED_SCORE + " FROM " + SCORE_TABLE + " ORDER BY " + ID + " LIMIT 1";
        Cursor cursor = db.rawQuery(statement,null);
        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        db.close();
        cursor.close();
        return result;
    }
    public int getHDataScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        int result =-1;
        String statement = "SELECT " +HARD_SCORE + " FROM " + SCORE_TABLE + " ORDER BY " + ID + " LIMIT 1";
        Cursor cursor = db.rawQuery(statement,null);
        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        db.close();
        cursor.close();
        return result;
    }
    public void updateDataScore(int x,int y) {
        String COLUMN;
        if (y==1) {
            COLUMN = EASY_SCORE;
        } else if (y==2) {
            COLUMN = MED_SCORE;
        } else {
            COLUMN = HARD_SCORE;
        }


        String statement = "UPDATE " + SCORE_TABLE + " SET " + COLUMN + " = " + x + " WHERE " + ID + " = 1;";
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(statement);
        db.close();
    }
}

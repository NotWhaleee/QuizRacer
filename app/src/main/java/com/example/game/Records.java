package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Records extends AppCompatActivity {
    TextView recordstitle;
    TextView question;
    Button answerNO;
    Button answerYES;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSettings;


    Toast toastDone;


    //DBHelper dbHelper;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.records);

        toastDone = Toast.makeText(getApplicationContext(), "Готово", Toast.LENGTH_SHORT);

        recordstitle = (TextView) findViewById(R.id.recordstitle);
        question = (TextView) findViewById(R.id.question);
        answerNO = (Button) findViewById(R.id.answerNO);
        answerYES = (Button) findViewById(R.id.answerYES);

        answerNO.setBackgroundResource(R.drawable.selectorbutton);
        answerYES.setBackgroundResource(R.drawable.selectorbutton);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);



        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            recordstitle.setText("Рекорд: "+String.valueOf(mSettings.getInt(APP_PREFERENCES_COUNTER, 0))+", множитель счета х"+String.valueOf(MainActivity.lvl));
        }else {
            recordstitle.setText("Рекорд: 0, множитель счета х"+ String.valueOf(MainActivity.lvl));
        }


        answerNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Records.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });


        answerYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putInt(APP_PREFERENCES_COUNTER, 0);
                    editor.apply();
                }

                toastDone.show();

                Intent intent = new Intent(Records.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });



        recordstitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();


        Cursor cursor = database.query(DBHelper.DATABASE_NAME, null, null, null, null, null, null);
        //cursor.moveToFirst();
        //int scoreIndex = cursor.getColumnIndex(DBHelper.KEY_SCORE);
        //recordstitle.setText("score = " + cursor.getString(scoreIndex));
        //Log.d("mLog", "score = " + cursor.getString(scoreIndex));
        if(cursor.moveToFirst()){
            int scoreIndex = cursor.getColumnIndex(DBHelper.KEY_SCORE);
                do {
                    recordstitle.setText("score = " + cursor.getString(scoreIndex));
                }while (cursor.moveToNext());

        } else
            recordstitle.setText("no records");

        cursor.close();
        dbHelper.close();*/
    }

    public void onBackPressed(){
        Intent intent = new Intent(Records.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}


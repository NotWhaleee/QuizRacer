package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    public static int lvl = 1;
    Button play, settings,testImage,info,records;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSettings;


    TextView homelvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*          <Button
        android:id="@+id/play"
        style="@style/lvlbutton"
        android:layout_width="270dp"
        android:layout_height="60dp"
        android:layout_marginBottom="50dp"
        android:onClick="play"
        android:text="@string/play"
        android:textColor="@color/colorWhite"
        android:textSize="35sp" />*/

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        //Bundle arguments = getIntent().getExtras();
        //lvl = arguments.getInt("lvl");


        homelvl=(TextView)findViewById(R.id.homelvl);
        homelvl.setText("Уровень сложности: "+Integer.toString(lvl));

        //play = findViewById(R.id.play);

        settings = findViewById(R.id.settings);
        testImage = findViewById(R.id.testImage);
        info = findViewById(R.id.info);
        records = findViewById(R.id.records);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            records.setText("Рекорд: "+String.valueOf(mSettings.getInt(APP_PREFERENCES_COUNTER, 0)));
        }else {
            records.setText("Рекорд: 0");
        }
        //play.setBackgroundResource(R.drawable.selectorbutton);
        testImage.setBackgroundResource(R.drawable.selectorbutton);
        settings.setBackgroundResource(R.drawable.selectorbutton);
        info.setBackgroundResource(R.drawable.selectorbutton);
        records.setBackgroundResource(R.drawable.selectorbutton);


        testImage.setVisibility(testImage.VISIBLE);
        //play = (Button)findViewById(R.id.play);
       // play.setVisibility(testImage.GONE);

/*        play.setOnClickListener(new View.OnClickListener() {
            @OverrideЫ
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, play.class);
                intent.putExtra("lvl",lvl);
                startActivity(intent);
            }
        });*/
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }

        });
        testImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, testImage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }

        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }

        });
        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Records.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }

        });

    }



    public void play(View view) {


    }

    public void settings(View view) {
    }
    public void onBackPressed(){
    }

    public void info(View view) {
    }

    public void scoreboard(View view) {
    }

}
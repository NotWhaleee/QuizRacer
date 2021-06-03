package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    Button lvl1,lvl2,lvl3;
    TextView settingtitle;
    //int lvl = 1;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.settings);

        //Bundle arguments = getIntent().getExtras();
        //lvl = arguments.getInt("lvl");

        settingtitle = (TextView) findViewById(R.id.settingtitle);
        settingtitle.setText("Уровень сложности");//@string/settingtitle

        lvl1 = (Button) findViewById(R.id.lvl1);
        lvl2 = (Button) findViewById(R.id.lvl2);
        lvl3 = (Button) findViewById(R.id.lvl3);

        lvl1.setText("Легко, счет х1");
        lvl2.setText("Нормально, счет х2");
        lvl3.setText("Сложно, счет х3");

        //lvl1.setBackgroundResource(R.drawable.selectorbutton);
        if(MainActivity.lvl == 1){
            lvl1.setBackgroundResource(R.drawable.buttonlvlpressed);
            lvl2.setBackgroundResource(R.drawable.selectorbutton);
            lvl3.setBackgroundResource(R.drawable.selectorbutton);
        }
        if(MainActivity.lvl == 2){
            lvl2.setBackgroundResource(R.drawable.buttonlvlpressed);
            lvl1.setBackgroundResource(R.drawable.selectorbutton);
            lvl3.setBackgroundResource(R.drawable.selectorbutton);
        }
        if(MainActivity.lvl == 3){
            lvl3.setBackgroundResource(R.drawable.buttonlvlpressed);
            lvl1.setBackgroundResource(R.drawable.selectorbutton);
            lvl2.setBackgroundResource(R.drawable.selectorbutton);
        }


        //lvl1.setBackground();

        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.lvl = 1;
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        lvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.lvl = 2;
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        lvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.lvl = 3;
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

    }

    public void lvl1(View view) {
    }

    public void lvl2(View view) {

    }

    public void lvl3(View view) {

    }
    public void onBackPressed(){
        Intent intent = new Intent(Settings.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
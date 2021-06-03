package com.example.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class play extends AppCompatActivity {
    Button answer1,answer2,answer3,answer4;
    TextView num1,num2,sym,questionm,timeleft;
    int Num1,Num2,Sym,ans1,ans2,ans3,ans4,res;
    char CSym;
    int min1 = -10,max1 = 50,min2,max2;
    int hp = 5;
    int time = 5;
    int allTime = 0;
    int T = 5;
    int score = 0;
    Boolean play = true;
    VideoView videoPlayer;
    private Handler h;
    @SuppressLint("HandlerLeak")
    @Override

    protected void onCreate(/*LayoutInflater inflater, @Nullable ViewGroup container, @Nullable */Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getSupportActionBar().hide();
        h = new Handler() {
            public void handleMessage(Message msg) {
                if(msg.what == -1){
                    questionm.setText(getTimeString(0));
                    answer1.setVisibility(answer1.GONE);
                    answer2.setVisibility(answer2.VISIBLE);
                    answer2.setText("Выход");
                    answer3.setVisibility(answer3.VISIBLE);
                    answer3.setText("Начать заново");
                    answer4.setVisibility(answer4.GONE);
                    num1.setVisibility(num1.GONE);
                    sym.setText("Вы проиграли!");
                    num2.setVisibility(num2.GONE);
                    questionm.setVisibility(questionm.GONE);
                }else {
                    allTime++;
                    timeleft.setText(getTimeString(time));
                }



            };
        };
        videoPlayer =  (VideoView)findViewById(R.id.videoPlayer);


        Uri animation= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.anim15);
        videoPlayer.setVideoURI(animation);
        if(MainActivity.lvl == 1) T = 10;
        if(MainActivity.lvl == 2) T = 7;
        if(MainActivity.lvl == 3) T = 5;
        time = T;
        play = true;


        final int duration = 57; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! не забудь поменять!!!!!!!!!!!!!!!!!!!!!!!!!


        videoPlayer.start();



        num1=(TextView)findViewById(R.id.num1);
        num2=(TextView)findViewById(R.id.num2);
        sym=(TextView)findViewById(R.id.sym);
        timeleft=(TextView)findViewById(R.id.time);
        questionm=(TextView)findViewById(R.id.questionm);
        answer1=(Button)findViewById(R.id.answer1);
        answer2=(Button)findViewById(R.id.answer2);
        answer3=(Button)findViewById(R.id.answer3);
        answer4=(Button)findViewById(R.id.answer4);
        updater.start();


        answer1.setBackgroundResource(R.drawable.selectorbutton);
        answer2.setBackgroundResource(R.drawable.selectorbutton);
        answer3.setBackgroundResource(R.drawable.selectorbutton);
        answer4.setBackgroundResource(R.drawable.selectorbutton);


        game();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans1 != res){
/*                        Intent intent = new Intent(play.this, MainActivity.class);
                        startActivity(intent);*/
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                    answer1.setVisibility(answer1.GONE);

                    hp--;
                    if(hp == 0){
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.GONE);
                        answer2.setVisibility(answer2.VISIBLE);
                        answer2.setText("Выход");
                        answer3.setVisibility(answer3.VISIBLE);
                        answer3.setText("Начать заново");
                        answer4.setVisibility(answer4.GONE);
                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: "+ score);
                        sym.setText("Вы проиграли!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }
                }
                if (ans1 == res){
                    score++;
                    answer2.setVisibility(answer2.VISIBLE);
                    answer3.setVisibility(answer3.VISIBLE);
                    answer4.setVisibility(answer4.VISIBLE);
                    time = T;
                    if(allTime>=duration){
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.GONE);
                        answer2.setVisibility(answer2.VISIBLE);
                        answer2.setText("Выход");
                        answer3.setVisibility(answer3.VISIBLE);
                        answer3.setText("Начать заново");
                        answer4.setVisibility(answer4.GONE);
                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: "+ score);
                        sym.setText("Победа!!!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }else {
                        time = T;
                        game();
                    }
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(answer2.getText()=="Выход"){
                    Intent intent = new Intent(play.this, MainActivity.class);
                    startActivity(intent);
                }
                if (ans2 != res){
/*                        Intent intent = new Intent(play.this, MainActivity.class);
                        startActivity(intent);*/
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                    answer2.setVisibility(answer2.GONE);
                    hp--;
                    if(hp == 0){
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.GONE);
                        answer2.setVisibility(answer2.VISIBLE);
                        answer2.setText("Выход");
                        answer3.setVisibility(answer3.VISIBLE);
                        answer3.setText("Начать заново");
                        answer4.setVisibility(answer4.GONE);
                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: "+ score);
                        sym.setText("Вы проиграли!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }
                }
                if (ans2 == res){
                    score++;
                    answer1.setVisibility(answer1.VISIBLE);
                    answer2.setVisibility(answer2.VISIBLE);
                    answer3.setVisibility(answer3.VISIBLE);
                    answer4.setVisibility(answer4.VISIBLE);
                    if(allTime>=duration){
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.GONE);
                        answer2.setVisibility(answer2.VISIBLE);
                        answer2.setText("Выход");
                        answer3.setVisibility(answer3.VISIBLE);
                        answer3.setText("Начать заново");
                        answer4.setVisibility(answer4.GONE);
                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: "+ score);
                        sym.setText("Победа!!!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }else {
                        time = T;
                        game();
                    }
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer3.getText()=="Начать заново"){
                    Intent intent = new Intent(play.this, play.class);
                    startActivity(intent);
                }
                if (ans3 != res){

                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                    answer3.setVisibility(answer3.GONE);
                    hp--;
                    if(hp == 0){
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.GONE);
                        answer2.setVisibility(answer2.VISIBLE);
                        answer2.setText("Выход");
                        answer3.setVisibility(answer3.VISIBLE);
                        answer3.setText("Начать заново");
                        answer4.setVisibility(answer4.GONE);
                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: "+ score);
                        sym.setText("Вы проиграли!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }
                }
                if (ans3 == res){
                    score++;
                    answer1.setVisibility(answer1.VISIBLE);
                    answer2.setVisibility(answer2.VISIBLE);
                    answer3.setVisibility(answer3.VISIBLE);
                    answer4.setVisibility(answer4.VISIBLE);
                    if(allTime>=duration){
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.GONE);
                        answer2.setVisibility(answer2.VISIBLE);
                        answer2.setText("Выход");
                        answer3.setVisibility(answer3.VISIBLE);
                        answer3.setText("Начать заново");
                        answer4.setVisibility(answer4.GONE);
                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: "+ score);
                        sym.setText("Победа!!!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }else {
                        time = T;
                        game();
                    }
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans4 != res){
/*                        Intent intent = new Intent(play.this, MainActivity.class);
                        startActivity(intent);*/
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                    answer4.setVisibility(answer4.GONE);
                    hp--;
                    if(hp == 0){
                        pause(videoPlayer);
                        play = false;
                        answer1.setVisibility(answer1.GONE);
                        answer2.setVisibility(answer2.VISIBLE);
                        answer2.setText("Выход");
                        answer3.setVisibility(answer3.VISIBLE);
                        answer3.setText("Начать заново");
                        answer4.setVisibility(answer4.GONE);
                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: "+ score);
                        sym.setText("Вы проиграли!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);

                    }
                }
                if (ans4 == res){
                    score++;
                    answer1.setVisibility(answer1.VISIBLE);
                    answer2.setVisibility(answer2.VISIBLE);
                    answer3.setVisibility(answer3.VISIBLE);
                    answer4.setVisibility(answer4.VISIBLE);
                    if(allTime>=duration){
                        pause(videoPlayer);
                        play = false;
                        answer1.setVisibility(answer1.GONE);
                        answer2.setVisibility(answer2.VISIBLE);
                        answer2.setText("Выход");
                        answer3.setVisibility(answer3.VISIBLE);
                        answer3.setText("Начать заново");
                        answer4.setVisibility(answer4.GONE);
                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: "+ score);
                        sym.setText("Победа!!!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }else {
                        time = T;
                        game();
                    }
                }
            }
        });
    }


    public void runOutOfTime(View view){
        answer1.setVisibility(answer1.GONE);
        answer2.setVisibility(answer2.VISIBLE);
        answer2.setText("Выход");
        answer3.setVisibility(answer3.VISIBLE);
        answer3.setText("Начать заново");
        answer4.setVisibility(answer4.GONE);
        num1.setVisibility(num1.GONE);
        sym.setText("Вы проиграли!");
        num2.setVisibility(num2.GONE);
        questionm.setVisibility(questionm.GONE);
    }
    public void play(View view){
        videoPlayer.start();
    }
    public void pause(View view){
        videoPlayer.pause();
    }
    public void stop(View view){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }
    public void game(){

        Sym = srandom(MainActivity.lvl);
        Num1 = random(MainActivity.lvl,Sym);
        Num2 = random2(MainActivity.lvl,Sym,Num1);
        switch (Sym){
            case 0:
                CSym = '+';
                break;
            case 1:
                CSym = '-';
                break;
            case 2:
                CSym = '*';
                break;
            case 3:
                CSym = '/';
                break;
            default:
                CSym = '0';
                break;
        }
        num1.setText(Integer.toString(Num1));
        num2.setText(Integer.toString(Num2));
        sym.setText(Character.toString(CSym));
        res = result(Num1,Num2,Sym);
        int arr[];
        arr = new int [4];
        arr[0] = res - 10;
        arr[1] = res + 7;
        arr[2] = res - 6;
        arr[3] = res + 4;
        int x = 0;
        boolean f = true;
        for (int i = 1; i != 0;x++){
            if (x == 4) x = 0;
            int tempSym = (int) (Math.random() * 1);
            if (tempSym == 1) tempSym = 1;
            if (tempSym == 2) tempSym = -1;
            if (tempSym == 0) tempSym = -1;
            if (tempSym != 1 && tempSym != -1) tempSym = 99999;
            System.out.println(tempSym);
            i = (int) (Math.random() * 4);
            arr[x] = res +(tempSym*((int) (Math.random() * (i*i))));
            for (int j = 0; j < 4; j++){
                for (int j1 = j+1; j1 < 4; j1++){
                    if(arr[j] == arr[j1]) {
                        f = false;
                        j = 5;
                        j1 = 5;
                        i = 1;
                    }
                }
            }
        }
        ans1 = arr[0];
        ans2 = arr[1];
        ans3 = arr[2];
        ans4 = arr[3];
        answer1.setText(Integer.toString(ans1));
        answer2.setText(Integer.toString(ans2));
        answer3.setText(Integer.toString(ans3));
        answer4.setText(Integer.toString(ans4));
    }
    // }
    public int result(int Num1,int Num2,int Sym){
        switch (Sym){
            case 0:
                res = Num1 + Num2;
                break;
            case 1:
                res = Num1 - Num2;
                break;
            case 2:
                res = Num1 * Num2;
                break;
            case 3:
                res = Num1 / Num2;
                break;
            default:
                res = -999999999;
                break;
        }
        return res;
    }
    public int random(int lvl,int Sym){
        switch (lvl){
            case 1:
                min1 = 0;
                max1 = 500;
                min2 = 0;
                max2 = 10;
                break;
            case 2:
                min1 = -500;
                max1 = 1500;
                min2 = -15;
                max2 = 15;
                break;
            case 3:
                min1 = -9999;
                max1 = 9999;
                min2 = -200;
                max2 = 200;
                break;
            default:
                min1 = 0;
                max1 = 0;
                min2 = 0;
                max2 = 0;
        }
        int a=0;
        if(Sym == 0 || Sym == 1){
            a = min1 + (int) (Math.random() * max1);
        }
        if(Sym == 2){
            a = min2 + (int) (Math.random() * max2);
        }
        if(Sym == 3){
            a = min2 + (int) (Math.random() * max2);
        }

        return a;
    }
    public int random2(int lvl,int Sym,int Num1){
        switch (lvl){
            case 1:
                min1 = 0;
                max1 = 500;
                min2 = 0;
                max2 = 10;
                break;
            case 2:
                min1 = -500;
                max1 = 1500;
                min2 = -15;
                max2 = 15;
                break;
            case 3:
                min1 = -9999;
                max1 = 9999;
                min2 = -200;
                max2 = 200;
                break;
            default:
                min1 = 0;
                max1 = 0;
                min2 = 0;
                max2 = 0;
        }
        int a=0;
        if(Sym == 0 || Sym == 1){
            a = min1 + (int) (Math.random() * max1);
        }
        if(Sym == 2){
            a = min2 + (int) (Math.random() * max2);
        }
        if(Sym == 3){
            do {
                a = min2 + (int) (Math.random() * max2);
            }while (Num1 % a != 0);
        }

        return a;
    }
    public int srandom(int lvl){
        int a,range = 99;
        if (lvl == 1){
            range = 2;
        }
        if (lvl == 2 || lvl == 3){
            range = 4;
        }
        a = (int) (Math.random() * range);
        return a;
    }
    public void answer1(View view) {
    }

    public void answer2(View view) {
    }

    public void answer3(View view) {
    }

    public void answer4(View view) {
    }
    Thread updater = new Thread() {
        @Override
        public void run() {
            try {
                while (play) {
                    h.sendEmptyMessage(time);
                    //h.sendEmptyMessage(allTime);
                    Thread.sleep(1000);
                    if(time == -1 /*||  allTime == duration1*/){
                        pause(videoPlayer);
                        play = false;
                    }
                    if(time == 0){
                        pause(videoPlayer);
                    }
                    //allTime++;
                    time--;
                }
            }
            catch (InterruptedException e) {
            }

        }
    };

    public String getTimeString(int time){
        String time_str = "0:00";
        if(time<10){
            time_str="0:0"+time;
        }else if(time<60){
            time_str="0:"+time;
        }else{
            int min = time/60;
            if((time-min*60)<10) {
                time_str = min + ":0" + (time - min * 60);
            }else{
                time_str=min+":"+(time-min*60);
            }
        }
        return time_str;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



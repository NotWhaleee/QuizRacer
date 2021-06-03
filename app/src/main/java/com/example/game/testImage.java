package com.example.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.Random;


public class testImage extends AppCompatActivity {
    Button answer1, answer2, answer3, answer4;
    TextView num1, num2, sym, questionm, timeleft;
    int Num1, Num2, Sym, ans1, ans2, ans3, ans4, res;
    char CSym;
    int min1 = -10, max1 = 50, min2, max2;
    int hp = 5;
    int time = 5;
    int allTime = 0;
    int T = 5;
    int score = 0;
    Boolean play = true;
    VideoView videoPlayer;
    private Handler h;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSettings;

    @SuppressLint("HandlerLeak")
    @Override

    protected void onCreate(/*LayoutInflater inflater, @Nullable ViewGroup container, @Nullable */Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);

        getSupportActionBar().hide();
        //final Scoreboard Scoreboard = new Scoreboard();

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        h = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == -1) {
                    answer1.setVisibility(answer1.VISIBLE);
                    answer2.setVisibility(answer2.GONE);
                    answer1.setText("Выход");
                    answer3.setVisibility(answer3.GONE);
                    answer4.setVisibility(answer4.VISIBLE);
                    answer4.setText("Заново");

                    num1.setVisibility(num1.GONE);
                    timeleft.setText("Ваш счет: " + score);
                    if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                        if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                    }else {
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putInt(APP_PREFERENCES_COUNTER, score);
                        editor.apply();
                    }
                    sym.setText("Вы проиграли!");
                    num2.setVisibility(num2.GONE);
                    questionm.setVisibility(questionm.GONE);
                } else {
                    allTime++;
                    timeleft.setText(getTimeString(time));
                }
            };
        };
        videoPlayer = (VideoView) findViewById(R.id.videoPlayer);


        Uri animation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.anim15);
        videoPlayer.setVideoURI(animation);
        if (MainActivity.lvl == 1) T = 10;
        if (MainActivity.lvl == 2) T = 7;
        if (MainActivity.lvl == 3) T = 5;
        time = T;
        play = true;


        final int duration = 57; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! не забудь поменять!!!!!!!!!!!!!!!!!!!!!!!!!


        videoPlayer.start();


        num1 = (TextView) findViewById(R.id.num1);
        num2 = (TextView) findViewById(R.id.num2);
        sym = (TextView) findViewById(R.id.sym);
        timeleft = (TextView) findViewById(R.id.time);
        questionm = (TextView) findViewById(R.id.questionm);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        updater.start();


        answer1.setBackgroundResource(R.drawable.selectorbutton);
        answer2.setBackgroundResource(R.drawable.selectorbutton);
        answer3.setBackgroundResource(R.drawable.selectorbutton);
        answer4.setBackgroundResource(R.drawable.selectorbutton);


        game();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (answer1.getText() == "Выход") {
                    Intent intent = new Intent(testImage.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);



                }
                if (ans1 != res) {
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
                    if (hp == 0) {
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.VISIBLE);
                        answer2.setVisibility(answer2.GONE);
                        answer1.setText("Выход");
                        answer3.setVisibility(answer3.GONE);
                        answer4.setVisibility(answer4.VISIBLE);
                        answer4.setText("Заново");

                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: " + score);
                        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                            if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt(APP_PREFERENCES_COUNTER, score);
                                editor.apply();
                            }
                        }else {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                        sym.setText("Вы проиграли!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }
                }
                if (ans1 == res) {
                    score+=MainActivity.lvl;
                    answer2.setVisibility(answer2.VISIBLE);
                    answer3.setVisibility(answer3.VISIBLE);
                    answer4.setVisibility(answer4.VISIBLE);
                    time = T;
                    if (allTime >= duration) {
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.VISIBLE);
                        answer2.setVisibility(answer2.GONE);
                        answer1.setText("Выход");
                        answer3.setVisibility(answer3.GONE);
                        answer4.setVisibility(answer4.VISIBLE);
                        answer4.setText("Заново");

                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: " + score);
                        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                            if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt(APP_PREFERENCES_COUNTER, score);
                                editor.apply();
                            }
                        }else {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                        sym.setText("Победа!!!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    } else {
                        time = T;
                        game();
                    }
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ans2 != res) {
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
                    if (hp == 0) {
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.VISIBLE);
                        answer2.setVisibility(answer2.GONE);
                        answer1.setText("Выход");
                        answer3.setVisibility(answer3.GONE);
                        answer4.setVisibility(answer4.VISIBLE);
                        answer4.setText("Заново");

                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: " + score);
                        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                            if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt(APP_PREFERENCES_COUNTER, score);
                                editor.apply();
                            }
                        }else {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                        sym.setText("Вы проиграли!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }
                }
                if (ans2 == res) {
                    score+=MainActivity.lvl;
                    answer1.setVisibility(answer1.VISIBLE);
                    answer2.setVisibility(answer2.VISIBLE);
                    answer3.setVisibility(answer3.VISIBLE);
                    answer4.setVisibility(answer4.VISIBLE);
                    if (allTime >= duration) {
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.VISIBLE);
                        answer2.setVisibility(answer2.GONE);
                        answer1.setText("Выход");
                        answer3.setVisibility(answer3.GONE);
                        answer4.setVisibility(answer4.VISIBLE);
                        answer4.setText("Заново");

                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: " + score);
                        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                            if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt(APP_PREFERENCES_COUNTER, score);
                                editor.apply();
                            }
                        }else {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                        sym.setText("Победа!!!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    } else {
                        time = T;
                        game();
                    }
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ans3 != res) {

                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                    answer3.setVisibility(answer3.GONE);
                    hp--;
                    if (hp == 0) {
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.VISIBLE);
                        answer2.setVisibility(answer2.GONE);
                        answer1.setText("Выход");
                        answer3.setVisibility(answer3.GONE);
                        answer4.setVisibility(answer4.VISIBLE);
                        answer4.setText("Заново");

                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: " + score);
                        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                            if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt(APP_PREFERENCES_COUNTER, score);
                                editor.apply();
                            }
                        }else {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                        sym.setText("Вы проиграли!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    }
                }
                if (ans3 == res) {
                    score+=MainActivity.lvl;
                    answer1.setVisibility(answer1.VISIBLE);
                    answer2.setVisibility(answer2.VISIBLE);
                    answer3.setVisibility(answer3.VISIBLE);
                    answer4.setVisibility(answer4.VISIBLE);
                    if (allTime >= duration) {
                        pause(videoPlayer);
                        play = false;

                        answer1.setVisibility(answer1.VISIBLE);
                        answer2.setVisibility(answer2.GONE);
                        answer1.setText("Выход");
                        answer3.setVisibility(answer3.GONE);
                        answer4.setVisibility(answer4.VISIBLE);
                        answer4.setText("Заново");

                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: " + score);
                        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                            if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt(APP_PREFERENCES_COUNTER, score);
                                editor.apply();
                            }
                        }else {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                        sym.setText("Победа!!!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    } else {
                        time = T;
                        game();
                    }
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer4.getText() == "Заново") {
                    Intent intent = new Intent(testImage.this, testImage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                }
                if (ans4 != res) {
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
                    if (hp == 0) {
                        pause(videoPlayer);
                        play = false;
                        answer1.setVisibility(answer1.VISIBLE);
                        answer2.setVisibility(answer2.GONE);
                        answer1.setText("Выход");
                        answer3.setVisibility(answer3.GONE);
                        answer4.setVisibility(answer4.VISIBLE);
                        answer4.setText("Заново");

                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: " + score);
                        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                            if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt(APP_PREFERENCES_COUNTER, score);
                                editor.apply();
                            }
                        }else {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                        sym.setText("Вы проиграли!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);

                    }
                }
                if (ans4 == res) {
                    score+=MainActivity.lvl;
                    answer1.setVisibility(answer1.VISIBLE);
                    answer2.setVisibility(answer2.VISIBLE);
                    answer3.setVisibility(answer3.VISIBLE);
                    answer4.setVisibility(answer4.VISIBLE);
                    if (allTime >= duration) {
                        pause(videoPlayer);
                        play = false;
                        answer1.setVisibility(answer1.VISIBLE);
                        answer2.setVisibility(answer2.GONE);
                        answer1.setText("Выход");
                        answer3.setVisibility(answer3.GONE);
                        answer4.setVisibility(answer4.VISIBLE);
                        answer4.setText("Заново");

                        num1.setVisibility(num1.GONE);
                        timeleft.setText("Ваш счет: " + score);
                        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
                            if (score > mSettings.getInt(APP_PREFERENCES_COUNTER, 0)){
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt(APP_PREFERENCES_COUNTER, score);
                                editor.apply();
                            }
                        }else {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNTER, score);
                            editor.apply();
                        }
                        sym.setText("Победа!!!");
                        num2.setVisibility(num2.GONE);
                        questionm.setVisibility(questionm.GONE);
                    } else {
                        time = T;
                        game();
                    }
                }
            }
        });
    }


    public void runOutOfTime(View view) {
        answer1.setVisibility(answer1.VISIBLE);
        answer2.setVisibility(answer2.GONE);
        answer2.setText("Выход");
        answer3.setVisibility(answer3.VISIBLE);
        answer3.setText("Начать заново");
        answer4.setVisibility(answer4.GONE);
        num1.setVisibility(num1.GONE);
        sym.setText("Вы проиграли!");
        num2.setVisibility(num2.GONE);
        questionm.setVisibility(questionm.GONE);
    }

    public void play(View view) {
        videoPlayer.start();
    }

    public void pause(View view) {
        videoPlayer.pause();
    }

    public void stop(View view) {
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }

    public void game() {

        Sym = srandom(MainActivity.lvl);
        Num1 = random(MainActivity.lvl, Sym);
        Num2 = random2(MainActivity.lvl, Sym, Num1);
        switch (Sym) {
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
        res = result(Num1, Num2, Sym);
        int arr[];
        arr = new int[4];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            boolean found = false;
            int r = 0;
            do {
                found = false;
                int tempSym = (int) (Math.random() * 1);
                if (tempSym == 0) tempSym = -1;
                r = res + tempSym * random.nextInt(10);
                if (r < 0) r = -r;
                //Here we check if the number is not on the array yet
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == r) {
                        found = true;
                        break;
                    }
                }
            } while (found);

            arr[i] = r;
        }
        boolean f = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res) f = true;
        }
        if (!f) {
            int x = random.nextInt(4);
            arr[x] = res;
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
    public int result(int Num1, int Num2, int Sym) {
        switch (Sym) {
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

    public int random(int lvl, int Sym) {
        switch (lvl) {
            case 1:
                min1 = 0;
                max1 = 20;
                min2 = 0;
                max2 = 10;
                break;
            case 2:
                min1 = 0;
                max1 = 100;
                min2 = 0;
                max2 = 10;
                break;
            case 3:
                min1 = 0;
                max1 = 500;
                min2 = 0;
                max2 = 20;
                break;
            default:
                min1 = 0;
                max1 = 0;
                min2 = 0;
                max2 = 0;
        }
        int a = 0;
        if (Sym == 0 || Sym == 1) {
            a = min1 + (int) (Math.random() * max1);
        }
        if (Sym == 2) {
            a = min2 + (int) (Math.random() * max2);
        }
        if (Sym == 3) {
            a = min2 + (int) (Math.random() * max2);
        }

        return a;
    }

    public int random2(int lvl, int Sym, int Num1) {
        switch (lvl) {
            case 1:
                min1 = 0;
                max1 = Num1;
                break;
            case 2:
                min1 = 0;
                max1 = Num1;
                min2 = 1;
                max2 = 10;
                break;
            case 3:
                min1 = 0;
                max1 = Num1;
                min2 = 1;
                max2 = 20;
                break;
            default:
                min1 = 0;
                max1 = 0;
                min2 = 0;
                max2 = 0;
        }
        int a = 0;
        if (Sym == 0 || Sym == 1) {
            a = min1 + (int) (Math.random() * max1);
        }
        if (Sym == 2) {
            a = min2 + (int) (Math.random() * max2);
        }
        if (Sym == 3) {
            do {
                a = min2 + (int) (Math.random() * max2);
            } while (Num1 % a != 0);
        }

        return a;
    }

    public int srandom(int lvl) {
        int a, range = 99;
        if (lvl == 1) {
            range = 2;
        }
        if (lvl == 2 || lvl == 3) {
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
                    if (time == -1 /*||  allTime == duration1*/) {
                        pause(videoPlayer);
                        play = false;
                    }
                    if (time == 0) {
                        pause(videoPlayer);
                    }
                    //allTime++;
                    time--;
                }
            } catch (InterruptedException e) {
            }

        }
    };

    public String getTimeString(int time) {
        String time_str = "0:00";
        if (time < 10) {
            time_str = "0:0" + time;
        } else if (time < 60) {
            time_str = "0:" + time;
        } else {
            int min = time / 60;
            if ((time - min * 60) < 10) {
                time_str = min + ":0" + (time - min * 60);
            } else {
                time_str = min + ":" + (time - min * 60);
            }
        }
        return time_str;
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(testImage.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    protected void onPause() {
        super.onPause();
        finish();
    }
}


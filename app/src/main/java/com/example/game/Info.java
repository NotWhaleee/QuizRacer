package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity {
    TextView creators;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        getSupportActionBar().hide();
        back = findViewById(R.id.back);
        back.setBackgroundResource(R.drawable.selectorbutton);
        creators=(TextView)findViewById(R.id.creators);
        creators.setBackgroundResource(R.drawable.buttonlvlpressed);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.itschool);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Info.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }

        });
    }

    public void back(View view) {
    }
    public void onBackPressed(){
        Intent intent = new Intent(Info.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

}

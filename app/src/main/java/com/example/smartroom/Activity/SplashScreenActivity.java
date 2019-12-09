package com.example.smartroom.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartroom.R;
import com.example.smartroom.model.Token;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView text;
    private Thread mThread;
    private final String SharedReferencesFile ="sharedReferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo=findViewById(R.id.image);
        text=findViewById(R.id.text);

        startAnimation();
        onPostResume();

    }

    private void startAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation translate = AnimationUtils.loadAnimation(this, R.anim.move);

        logo.setAnimation(rotate);
        text.setAnimation(translate);

        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 3500) {
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                SplashScreenActivity.this.finish();
            }
        };
        mThread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences=getSharedPreferences(SharedReferencesFile,MODE_PRIVATE);
        String token=preferences.getString("token","");
        if (token.equals("")){
            startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
        }
        else startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
    }
}

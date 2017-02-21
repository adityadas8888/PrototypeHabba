package com.example.aditya.prototypehabba.map.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.aditya.prototypehabba.R;
import com.example.aditya.prototypehabba.map.Intro_Slider.PrefManager;
import com.example.aditya.prototypehabba.map.Intro_Slider.WelcomeActivity;
import com.example.aditya.prototypehabba.map.ResideMenu.MainActivity;

import static android.view.Gravity.CENTER;

public class SplashIdea extends AppCompatActivity {
    private PrefManager prefManager;
    int wid,hei;
    int size;
    float alp;

    RelativeLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_splash_idea);
        overridePendingTransition(R.anim.slide_in_left_fast,R.anim.slide_out_right_fast);
        final TextView button = (TextView) findViewById(R.id.button);
        mLayout=(RelativeLayout)findViewById(R.id.activity_splash_idea);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                ViewGroup.LayoutParams layPar=button.getLayoutParams();
                wid=layPar.width;
                hei=layPar.height;
                size=14;
                alp=1f;
                mLayout.setGravity(CENTER);
                //for(int j=0;j<10;j++){
                    for(int i=0;i<100;i++) {
                        wid++;
                        hei++;
                        size++;
                        try {
                            Thread.sleep(10);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //button.setLayoutParams(new RelativeLayout.LayoutParams(wid,hei));
                                    button.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
                                }
                            });

                        } catch (Exception e) {

                        }
                        button.post(new Runnable() {
                            @Override
                            public void run() {
                                button.requestLayout(); //has to be called in UI thread
                            }
                        });

                    }
                try{
                    Thread.sleep(500);
                    //button.setAlpha(0.0f);
                }
                catch (Exception A){

                }
                for(int j=0;j<100;j++){
                    alp=alp-0.01f;
                    try{
                        Thread.sleep(1);
                        button.setAlpha(alp);
                    }
                    catch (Exception z){

                    }
                }
            }

        });
        thread.start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(SplashIdea.this, WelcomeActivity.class);
                startActivity(intent1);
            }
        }, 800);
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(SplashIdea.this, MainActivity.class));
        finish();
    }


}

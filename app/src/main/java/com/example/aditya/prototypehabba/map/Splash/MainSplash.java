package com.example.aditya.prototypehabba.map.Splash;

//package com.codefive.adichi.myapplication;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.aditya.prototypehabba.R;
import com.example.aditya.prototypehabba.map.ResideMenu.MainActivity;

import android.widget.Toast;
import android.widget.VideoView;
public class MainSplash extends Activity
{
    private String current;
    String path ;
    VideoView mVideoView;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN        //Does the immersive screen android 4.4+though
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        SharedPreferences preferences = getSharedPreferences("info", Context.MODE_PRIVATE);
        final int flag = preferences.getInt("seen", 0);
        path="com.example.aditya.prototypehabba.map.Splash//"+R.raw.ls;               //Video source linking
        mVideoView = (VideoView) findViewById (R.id.splash);

        if (path == null || path.length() == 0)
        {
            Toast.makeText(MainSplash.this, "File URL/path is empty",
                    Toast.LENGTH_LONG).show();
        }

        else
        {
            if (path.equals(current) && mVideoView != null)
            {
                mVideoView.start();
                mVideoView.requestFocus();
                return;
            }
            current = path;
            mVideoView.setVideoPath (path);
            mVideoView.start();
            mVideoView.requestFocus();

        }
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer arg0)
            {
                if(flag !=0)
                {
                    Intent in = new Intent(MainSplash.this, MainActivity.class);
                    in.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                    finish();
                }
                else
                {
                    Intent in = new Intent(MainSplash.this, SplashIdea.class);
                    in.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity (in);
                    finish();
                }
            }
        });


    }
}

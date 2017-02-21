package com.example.aditya.prototypehabba.map.Devs;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.aditya.prototypehabba.R;

public class Devs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        setContentView(R.layout.devs);


    }
    public void call(View view){
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            String number = view.getTag().toString();
            callIntent.setData(Uri.parse("tel:"+number));
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Toast.makeText(getApplicationContext(), "Call has failed", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }

}

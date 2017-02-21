package com.example.aditya.prototypehabba.map.ResideMenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.example.aditya.prototypehabba.R;
import com.example.aditya.prototypehabba.map.Calender.Calender;
import com.example.aditya.prototypehabba.map.Map.MapsActivity;
import com.example.aditya.prototypehabba.map.Notification.NotificationsFragment;
import com.example.aditya.prototypehabba.map.Registration.Registration;
import com.example.aditya.prototypehabba.map.SlideMenu.SlideMenu;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private ResideMenuItem itemDeveloper,itemRegister,itemEvents,itemMaps,itemAbout,itemCalendar,itemNotification;

    private boolean com;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        com = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());
        if (com) {
                     }
        else {


            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                    MainActivity.this);
            alertDialog2.setTitle("YOU ARE OFFLINE");
            alertDialog2.setCancelable(false);
            alertDialog2.setMessage("CONNECT TO THE INTERNET AND TRYING AGAIN");
            alertDialog2.setPositiveButton("GO TO SETTINGS",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    });

            alertDialog2.setNegativeButton("EXIT",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    });

            alertDialog2.show();

        }

        setUpMenu();                                                //sets up the side menus
        if( savedInstanceState == null )
            changeFragment(new HomeFragment());//changes the Fragment to home
    }

    private void setUpMenu() {


        resideMenu = new ResideMenu(this);                             // attach to current activity;
        resideMenu.setBackground(R.drawable.menu_background);           //set the background
        resideMenu.attachToActivity(this);
        // resideMenu.setMenuListener(menuListener);                     //makes toast:not required
        resideMenu.setScaleValue(0.6f);                                   //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.

        // create menu items;
        itemAbout = new ResideMenuItem(this, R.drawable.info,"About Us");              //sets the menu pictures
        itemNotification = new ResideMenuItem(this, R.drawable.feed, "Feed");
        itemMaps = new ResideMenuItem(this,R.drawable.maps_thin, "Maps");
        itemEvents = new ResideMenuItem(this,R.drawable.event,"Events");
        itemCalendar = new ResideMenuItem(this,R.drawable.icon_calendar,"Calender");
        itemRegister = new ResideMenuItem(this,R.drawable.icon_profile,"Register");
        itemDeveloper = new ResideMenuItem(this,R.drawable.people,"Devs");
        itemAbout.setOnClickListener(this);
        itemNotification.setOnClickListener(this);
        itemMaps.setOnClickListener(this);
        itemEvents.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemRegister.setOnClickListener(this);
        itemDeveloper.setOnClickListener(this);
        resideMenu.addMenuItem(itemRegister,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemAbout, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemEvents, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNotification, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemMaps,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemDeveloper,ResideMenu.DIRECTION_LEFT);
        // You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemAbout){

           AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                    MainActivity.this);
            alertDialog2.setTitle("ACHARYA HABBA 2017");
            alertDialog2.setMessage("Acharya Habba  is the annual techno-cultural fest organised by Acharya Institute of Technology, Bangalore. It is a month-long event held in the month of March or April …" +
                    "http://wwww.acharyahabba.in");
            alertDialog2.setPositiveButton("GO TO SETTINGS",

                                        new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    });
            alertDialog2.show();

        }
        else if (view == itemNotification){

            resideMenu.closeMenu();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent1 = new Intent(MainActivity.this, NotificationsFragment.class);
                    startActivity(intent1);
                }
            }, 400);
        }
        else if (view ==itemMaps) {

            resideMenu.closeMenu();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent1);
                }
            }, 400);
        }
        else if (view ==itemEvents){
            resideMenu.closeMenu();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent1 = new Intent(MainActivity.this, SlideMenu.class);
                    startActivity(intent1);
                }
            }, 200);

        }
        else if (view ==itemCalendar){
            resideMenu.closeMenu();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent1 = new Intent(MainActivity.this, Calender.class);
                    startActivity(intent1);
                }
            }, 200);

        }
        else if (view ==itemRegister){
            Intent intent1 = new Intent(MainActivity.this, Registration.class);
            startActivity(intent1);

        }


        resideMenu.closeMenu();
    }
    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)                  //animation of the fragment transition
                .commit();
    }
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}


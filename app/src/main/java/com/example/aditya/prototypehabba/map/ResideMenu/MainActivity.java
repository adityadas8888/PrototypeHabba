package com.example.aditya.prototypehabba.map.ResideMenu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.example.aditya.prototypehabba.R;
import com.example.aditya.prototypehabba.map.Calender.Calender;
import com.example.aditya.prototypehabba.map.Map.MapsActivity;
import com.example.aditya.prototypehabba.map.Registration.Registration;
import com.example.aditya.prototypehabba.map.SlideMenu.SlideMenu;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    // private Context mContext;
    private ResideMenuItem itemHome;
    // private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemNotification;
    private ResideMenuItem itemMaps;
    private ResideMenuItem itemEvents;
    private ResideMenuItem itemRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mContext = this;
        setUpMenu();                                                //sets up the side menus
        if( savedInstanceState == null )
            changeFragment(new HomeFragment());//changes the Fragment to home
    }

    private void setUpMenu() {


        resideMenu = new ResideMenu(this);                             // attach to current activity;
        resideMenu.setBackground(R.mipmap.menu_background);           //set the background
        resideMenu.attachToActivity(this);
        // resideMenu.setMenuListener(menuListener);                     //makes toast:not required
        resideMenu.setScaleValue(0.6f);                                   //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");              //sets the menu pictures
        itemNotification = new ResideMenuItem(this, R.drawable.icon_settings, "Notification");
        itemMaps = new ResideMenuItem(this,R.drawable.icon_maps, "Maps");
        itemEvents = new ResideMenuItem(this,R.drawable.icon_settings,"Events");
        itemCalendar = new ResideMenuItem(this,R.drawable.icon_calendar,"Calender");
        itemRegister = new ResideMenuItem(this,R.drawable.icon_profile,"Register");

        itemHome.setOnClickListener(this);
        itemNotification.setOnClickListener(this);
        itemMaps.setOnClickListener(this);
        itemEvents.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemRegister.setOnClickListener(this);
        resideMenu.addMenuItem(itemRegister,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemEvents, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNotification, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemMaps,ResideMenu.DIRECTION_LEFT);
        // You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

      /*  findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });*/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new HomeFragment());
        }
        else if (view == itemNotification){
            changeFragment(new NotificationsFragment());
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


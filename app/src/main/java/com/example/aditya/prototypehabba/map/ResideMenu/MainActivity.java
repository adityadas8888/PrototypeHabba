package com.example.aditya.prototypehabba.map.ResideMenu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.aditya.prototypehabba.R;
import com.example.aditya.prototypehabba.map.MapsActivity;
import com.example.aditya.prototypehabba.map.SlideMenu.SlideMenu;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private Context mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemMaps;
    private ResideMenuItem itemEvents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mContext = this;
        setUpMenu();                                                //sets up the side menus
        if( savedInstanceState == null )
            changeFragment(new HomeFragment());                         //changes the Fragment to home
    }

    private void setUpMenu() {


        resideMenu = new ResideMenu(this);                             // attach to current activity;
        resideMenu.setBackground(R.drawable.menu_background);           //set the background
        resideMenu.attachToActivity(this);
        // resideMenu.setMenuListener(menuListener);                     //makes toast:not required
        resideMenu.setScaleValue(0.6f);                                   //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");              //sets the menu pictures
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");
        itemMaps = new ResideMenuItem(this,R.drawable.icon_maps, "Maps");
        itemEvents = new ResideMenuItem(this,R.drawable.icon_settings,"Events");
        itemHome.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemMaps.setOnClickListener(this);
        itemEvents.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemMaps,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemEvents, ResideMenu.DIRECTION_LEFT);
        // You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
      /*  findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
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
        }//else if (view == itemProfile){
        //  changeFragment(new ProfileFragment());
        //}else if (view == itemCalendar){
        //    changeFragment(new CalendarFragment());}
        else if (view == itemSettings){
            changeFragment(new SettingsFragment());
        }
        else if (view ==itemMaps){
            Toast.makeText(getBaseContext(),"clicked",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);

        } else if (view ==itemEvents){
            Toast.makeText(getBaseContext(),"clickedEvents",Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(MainActivity.this, SlideMenu.class);
            startActivity(intent1);

        }

        resideMenu.closeMenu();
    }

  /*  private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };*/

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)                  //animation of the fragment transition
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}


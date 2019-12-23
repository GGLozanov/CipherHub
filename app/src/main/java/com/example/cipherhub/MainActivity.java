package com.example.cipherhub; //main designated package

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import ui.QuadrupButtonFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpPagerInterface;
//import android.Animation.animation;

public class MainActivity extends Activity implements SetUpPagerInterface { //main class that inherits from AppCompatActivity (superclass for Activity)

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    // sharedPreferences for different user modes (novice cipher student, adept, master, etc.)

    @Override
    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new SectionFragment(), "Main Screen"); //add quadruple button fragment later on (once for main activity)
        adapter.addFragment(new QuadrupButtonFragment(), "Page 1");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { // overriding the OnCreate() default method for Activity to what we want.
        // Not much point in doing this in a polymorphic manner because this method is always overridden for any activity either way
        super.onCreate(savedInstanceState); // call parent constructor of AppCompatActivity and pass in the last saved instance (constant)

        setContentView(R.layout.activity_main); // set the screen's layout to the xml file in layout (can interact with it)

        Toolbar toolbar = findViewById(R.id.toolbar); // get Toolbar widget
        setSupportActionBar(toolbar); // set it as supporting action bar

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);
        viewPager.setCurrentItem(0);

    }

}

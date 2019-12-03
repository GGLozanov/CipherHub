package com.example.cipherhub; //main designated package

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ui.QuadrupButtonFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpPagerInterface;
//import android.Animation.animation;

public class MainActivity extends AppCompatActivity implements SetUpPagerInterface { //main class that inherits from AppCompatActivity (superclass for Activity)

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new SectionFragment(), "Main Screen"); //add quadruple button fragment later on (once for main activity)
        adapter.addFragment(new QuadrupButtonFragment(), "Page 1");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { //overriding the OnCreate() default method for Activity to what we want
        super.onCreate(savedInstanceState); //call parent constructor of AppCompatActivity and pass in the last saved instance (constant)

        setContentView(R.layout.activity_main); //set the screen's layout to the xml file in layout (can interact with it)

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);
        viewPager.setCurrentItem(0);

    }

}

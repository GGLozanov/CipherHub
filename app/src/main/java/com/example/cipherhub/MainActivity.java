package com.example.cipherhub; //main designated package

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

import adapters.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;
//import android.Animation.animation;

public class MainActivity extends Activity implements SetUpViewPager { //main class that inherits from AppCompatActivity (superclass for Activity)
    // sharedPreferences for different user modes (novice cipher student, adept, master, etc.)

    @Override
    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);

        HashMap<Fragment, String> fragmentMap = fragmentCollection.get(0);
        for(Fragment fragment : fragmentMap.keySet()) {
            adapter.addFragment(fragment, fragmentMap.get(fragment)); // get key by fragment and place it in adapter
        }

        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { // overriding the OnCreate() default method for Activity to what we want.
        // Not much point in doing this in a polymorphic manner because this method is always overridden for any activity either way
        super.onCreate(savedInstanceState); // call parent (Activity) implementation of onCreate() and pass in the last saved instance (constant)

        setContentView(R.layout.activity_main); // set the screen's layout to the xml file in layout (can interact with it)
        configureToolbar();

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);
        viewPager.setCurrentItem(0);

    }

}

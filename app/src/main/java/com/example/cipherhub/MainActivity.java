package com.example.cipherhub; //main designated package

import android.os.Bundle;
import android.util.Log;

import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;

import ui.ui_core.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;
//import android.Animation.animation;

public class MainActivity extends Activity implements SetUpViewPager { //main class that inherits from AppCompatActivity (superclass for Activity)

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    // sharedPreferences for different user modes (novice cipher student, adept, master, etc.)


    @Override
    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);

        for(Fragment fragment : fragmentCollection.get(0).keySet()) {
            Log.d("Value in ", fragmentCollection.get(0).get(fragment));

            adapter.addFragment(fragment, fragmentCollection.get(0).get(fragment)); // get key by fragment and place it in adapter
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

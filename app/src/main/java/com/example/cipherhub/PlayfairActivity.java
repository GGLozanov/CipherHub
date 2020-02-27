package com.example.cipherhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import java.util.HashMap;

import adapters.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;

public class PlayfairActivity extends Activity implements SetUpViewPager {

    @Override
    public void setUpViewPager(ViewPager viewPager) {
        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);

        fragmentMap = fragmentCollection.get(6);

        for(Fragment fragment : fragmentMap.keySet()) {
            fragmentPageAdapter.addFragment(fragment, fragmentMap.get(fragment));
        }

        viewPager.setAdapter(fragmentPageAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }
}

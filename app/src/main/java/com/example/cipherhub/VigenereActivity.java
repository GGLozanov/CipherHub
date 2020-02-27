package com.example.cipherhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;

import adapters.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;

public class VigenereActivity extends Activity implements SetUpViewPager {

    public void setUpViewPager(ViewPager viewPager) {
        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);

        fragmentMap = fragmentCollection.get(2);
        for(Fragment fragment : fragmentMap.keySet()) {
            fragmentPageAdapter.addFragment(fragment, fragmentMap.get(fragment));
        }
        viewPager.setAdapter(fragmentPageAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity);

        configureToolbar();

        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);
    }
}

package com.example.cipherhub;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.Fragment;

import ui.ui_core.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;

public class AtbashActivity extends Activity implements SetUpViewPager {

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        for(Fragment fragment : fragmentCollection.get(3).keySet()) {
            adapter.addFragment(fragment, fragmentCollection.get(3).get(fragment));
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atbash);

        configureToolbar();

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);
    }
}

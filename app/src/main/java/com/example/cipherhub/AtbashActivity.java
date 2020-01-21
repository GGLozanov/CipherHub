package com.example.cipherhub;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.Fragment;

import java.util.HashMap;

import adapters.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;

public class AtbashActivity extends Activity implements SetUpViewPager {

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);

        HashMap<Fragment, String> fragmentMap = fragmentCollection.get(3);
        for(Fragment fragment : fragmentMap.keySet()) {
            adapter.addFragment(fragment, fragmentMap.get(fragment));
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

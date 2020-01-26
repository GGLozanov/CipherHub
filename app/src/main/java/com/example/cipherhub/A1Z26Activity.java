package com.example.cipherhub;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import adapters.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class A1Z26Activity extends Activity implements SetUpViewPager {

    @Override
    public void setUpViewPager(ViewPager viewPager) {
        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);

        HashMap<Fragment, String> fragmentMap = fragmentCollection.get(5);

        for(Fragment fragment : fragmentMap.keySet()) {
            fragmentPageAdapter.addFragment(fragment, fragmentMap.get(fragment));
        }

        viewPager.setAdapter(fragmentPageAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a1z26);
        configureToolbar();

        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);
    }

}

package com.example.cipherhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;

import adapters.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;

public class CaesarActivity extends Activity implements SetUpViewPager {

    // include viewpager for activities -> done
    public void setUpViewPager(ViewPager viewPager) {
        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);

        HashMap<Fragment, String> fragmentMap = fragmentCollection.get(1);
        for(Fragment fragment : fragmentMap.keySet()) { // returns a set of the keys
            fragmentPageAdapter.addFragment(fragment, fragmentMap.get(fragment));
        }
        viewPager.setAdapter(fragmentPageAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_caesar);
        configureToolbar();

        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);
    }
}
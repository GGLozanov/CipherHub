package com.example.cipherhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;

import adapters.FragmentPageAdapter;
import ui.ui_core.SetUpViewPager;

public class PolybiusActivity extends Activity implements SetUpViewPager {

    public void setUpViewPager(ViewPager viewPager) { // UI template theme here
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);

        HashMap<Fragment, String> fragmentMap = fragmentCollection.get(4);
        for(Fragment fragment : fragmentMap.keySet()) {
            adapter.addFragment(fragment, fragmentMap.get(fragment));
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polybius);

        configureToolbar();

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);
    }

}

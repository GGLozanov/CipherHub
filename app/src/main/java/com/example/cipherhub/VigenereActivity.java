package com.example.cipherhub;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ui.KeyCipherFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpPagerInterface;

public class VigenereActivity extends AppCompatActivity implements SetUpPagerInterface {

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SectionFragment(), "Vigenere Screen");
        adapter.addFragment(new KeyCipherFragment(), "Vigenere Cipher");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vigenere);

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }
}

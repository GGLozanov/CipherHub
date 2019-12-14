package com.example.cipherhub;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ui.KeyCipherFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpPagerInterface;
import ui.ui_custom.ui_table_key_ciphers.AdditionalVigenereFragment;

public class VigenereActivity extends AppCompatActivity implements SetUpPagerInterface {

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new SectionFragment(), "Vigenere Screen");
        adapter.addFragment(new KeyCipherFragment(), "Vigenere Cipher");
        adapter.addFragment(new AdditionalVigenereFragment(), "Custom Vigenere");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vigenere);

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }
}

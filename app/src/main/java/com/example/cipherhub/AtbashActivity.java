package com.example.cipherhub;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ciphers.AtbashCipher;
import ui.CipherFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpPagerInterface;

public class AtbashActivity extends AppCompatActivity implements SetUpPagerInterface {

    EditText inputText;
    EditText atbashText;

    AtbashCipher atbashCipher;

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SectionFragment(), "Atbash Screen");
        adapter.addFragment(new CipherFragment(), "Atbash Cipher");
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atbash);

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }
}

package com.example.cipherhub;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ciphers.PolybiusCipher;
import ui.CipherFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpPagerInterface;

public class PolybiusActivity extends AppCompatActivity implements SetUpPagerInterface {

    EditText inputText;
    EditText polybiusText;
    TextView lengthView;

    PolybiusCipher polybiusCipher;

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SectionFragment(), "Polybius Screen");
        adapter.addFragment(new CipherFragment(), "Polybius Cipher");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polybius);

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }

}

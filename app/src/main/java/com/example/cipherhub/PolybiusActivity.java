package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ciphers.PolybiusCipher;
import ui.CipherFragment;
import ui.QuadrupButtonFragment;
import ui.SectionFragment;
import ui.SectionPageAdapter;
import ui.SetUpPagerInterface;

public class PolybiusActivity extends AppCompatActivity implements SetUpPagerInterface {

    EditText inputText;
    EditText polybiusText;
    TextView lengthView;

    PolybiusCipher polybiusCipher;

    ViewPager viewPager;
    SectionPageAdapter sectionPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SectionFragment(), "Polybius Screen");
        adapter.addFragment(new CipherFragment(), "Polybius Cipher");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polybius);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }

}

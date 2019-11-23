package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ciphers.AtbashCipher;
import ui.SectionFragment;
import ui.SectionPageAdapter;
import ui.SetUpPagerInterface;

public class AtbashActivity extends AppCompatActivity implements SetUpPagerInterface {

    EditText inputText;
    EditText atbashText;

    AtbashCipher atbashCipher;

    ViewPager viewPager;
    SectionPageAdapter sectionPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SectionFragment(), "Atbash Screen");
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atbash);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

        TextWatcher InputtoAtbash = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!atbashCipher.getEncodeState()) {
                    atbashCipher.setDecodeEvoked(true);
                    atbashText.setText(atbashCipher.AtbashEncode(s.toString()));
                }
                atbashCipher.setEncodeEvoked(false);
            }
        };

        TextWatcher AtbashtoInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!atbashCipher.getDecodeState()) {
                    atbashCipher.setEncodeEvoked(true);
                    inputText.setText(atbashCipher.AtbashDecode(s.toString()));
                }
                atbashCipher.setDecodeEvoked(false);
            }
        };

        atbashCipher = new AtbashCipher("abcdefghijklmnopqrstuvwxyz");

        atbashCipher.setIOTexts((EditText) findViewById(R.id.AtbashStringInput), (EditText) findViewById(R.id.AtbashStringOutput));

        inputText = atbashCipher.getInputText();
        atbashText = atbashCipher.getOutputText();

        inputText.addTextChangedListener(InputtoAtbash);
        atbashText.addTextChangedListener(AtbashtoInput);
    }
}

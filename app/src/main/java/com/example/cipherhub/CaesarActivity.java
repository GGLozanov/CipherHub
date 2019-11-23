package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ciphers.CaesarCipher;
import ui.SectionFragment;
import ui.SectionPageAdapter;
import ui.SetUpPagerInterface;

public class CaesarActivity extends AppCompatActivity implements SetUpPagerInterface {

    CaesarCipher caesarCipher;
    EditText caesarText;
    EditText normalInput;

    //include viewpager for activities -> done

    ViewPager viewPager;
    SectionPageAdapter sectionPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SectionFragment(), "Caesar Screen");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

        caesarCipher = new CaesarCipher(3);

        TextWatcher normaltoCaesarListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!caesarCipher.getEncodeState()) {
                    caesarCipher.setDecodeEvoked(true);
                    caesarText.setText(caesarCipher.CaesarEncoder(s));
                }
                caesarCipher.setEncodeEvoked(false);
            }
        };

        TextWatcher CaesartoNormalListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //normalInput.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!caesarCipher.getDecodeState()) {
                    caesarCipher.setEncodeEvoked(true);
                    normalInput.setText(caesarCipher.CaesarDecoder(s));
                    //isDecodeEvoked = false;
                }
                caesarCipher.setDecodeEvoked(false);
            }
        };

        caesarCipher.setIOTexts((EditText) findViewById(R.id.CaesarStringInput), (EditText) findViewById(R.id.CaesarStringOutput));

        normalInput = caesarCipher.getInputText();
        caesarText = caesarCipher.getOutputText();

        normalInput.addTextChangedListener(normaltoCaesarListener);
        caesarText.addTextChangedListener(CaesartoNormalListener);
    }
}
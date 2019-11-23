package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ciphers.PolybiusCipher;
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

        polybiusCipher = new PolybiusCipher();
        lengthView = (TextView) findViewById(R.id.lengthView);

        final TextWatcher InputToPolybius = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!polybiusCipher.getEncodeState()) {
                    polybiusCipher.setDecodeEvoked(true);
                    polybiusText.setText(polybiusCipher.PolybiusEncode(s.toString()));
                }
                polybiusCipher.setDecodeEvoked(false);
            }
        };


        final TextWatcher PolybiusToInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!polybiusCipher.getDecodeState()) {
                    polybiusCipher.setEncodeEvoked(true);
                    if(polybiusCipher.getCharacterValidator().pureCharacterLength(s.toString()) % 2 == 0) {
                        inputText.setText(polybiusCipher.PolybiusDecode(s.toString(), inputText.toString()));
                    }
                }

                polybiusCipher.setEncodeEvoked(false);
            }
        };

        polybiusCipher.setIOTexts((EditText) findViewById(R.id.PolybiusStringInput), (EditText) findViewById(R.id.PolybiusStringOutput));

        inputText = polybiusCipher.getInputText();
        polybiusText = polybiusCipher.getOutputText();

        inputText.addTextChangedListener(InputToPolybius);
        polybiusText.addTextChangedListener(PolybiusToInput);
    }

}

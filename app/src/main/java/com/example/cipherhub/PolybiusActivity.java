package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PolybiusActivity extends AppCompatActivity {

    EditText inputText;
    EditText polybiusText;

    PolybiusCipher polybiusCipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polybius);

        polybiusCipher = new PolybiusCipher();

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
                    if(s.toString().length() % 2 == 0) inputText.setText(polybiusCipher.PolybiusDecode(s.toString(), inputText.toString()));
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

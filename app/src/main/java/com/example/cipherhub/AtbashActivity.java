package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AtbashActivity extends AppCompatActivity {

    EditText inputText;
    EditText atbashText;

    AtbashCipher atbashCipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atbash);

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

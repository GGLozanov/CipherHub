package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CaesarActivity extends AppCompatActivity {

    CaesarCipher caesarCipher;
    EditText caesarText;
    EditText normalInput;

    //TextView caesarInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar);

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
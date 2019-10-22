package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CaesarActivity extends AppCompatActivity {
    EditText caesarText;
    EditText normalInput;
    int key = 3;
    boolean isCaesarEvoked = false;
    boolean isDecodeEvoked = false;
    //TextView caesarInput;
    private void CaesarDecoder(Editable base){
        String decodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++){
            int currentCharValue = (int) defaultInput.charAt(i);
            int asciiValue = currentCharValue + key;
            if(asciiValue > 'Z' && asciiValue < 'a')asciiValue += 26;
            if((asciiValue < 'A' && asciiValue != 32) || asciiValue > 'z')continue;
            decodedText += (char)asciiValue;
        }
        normalInput.setText(decodedText);
        isDecodeEvoked = true;
        //System.out.println(encodedText);
    }
    private void CaesarCipher(Editable base){
        String encodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++){
            int currentCharValue = (int) defaultInput.charAt(i);
            int asciiValue = currentCharValue - key;
            if(asciiValue > 'Z' && asciiValue < 'a')asciiValue += 26;
            if((asciiValue < 'A' && asciiValue != 32) || asciiValue > 'z')continue;
            encodedText += (char)asciiValue;
        }
        caesarText.setText(encodedText);
        isCaesarEvoked = true;
        //System.out.println(encodedText);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar);
        TextWatcher normaltoCaesarListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!isCaesarEvoked) {
                    isDecodeEvoked = true;
                    CaesarCipher(s);
                }
                isCaesarEvoked = false;
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
                if(!isDecodeEvoked) {
                    isCaesarEvoked = false;
                    CaesarDecoder(s);
                    //isDecodeEvoked = false;
                }
                isDecodeEvoked = false;
            }
        };
        normalInput = (EditText) findViewById(R.id.CaesarStringInput);
        caesarText = (EditText) findViewById(R.id.CaesarStringOutput);
        normalInput.addTextChangedListener(normaltoCaesarListener);
        caesarText.addTextChangedListener(CaesartoNormalListener);
    }
}
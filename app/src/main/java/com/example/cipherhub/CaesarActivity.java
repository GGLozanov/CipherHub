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

    ASCIIUtils characterValidator;

    //TextView caesarInput;
    private void CaesarDecoder(Editable base){
        String decodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++) {

            int currentCharValue = (int) defaultInput.charAt(i);

            if(characterValidator.isSpecialCharacter(currentCharValue)) {
                decodedText += (char)currentCharValue;
                continue;
            }

            if(characterValidator.isInvalidCharacter((currentCharValue))) continue;

            int asciiValue = currentCharValue + key;

            if(characterValidator.isCaesarDecodeSpecialCase(currentCharValue)) asciiValue -= 26;

            //if(((asciiValue != ' ' && (asciiValue < 'A')) || (asciiValue > 'z'))continue;

            decodedText += (char)asciiValue;
        }

        normalInput.setText(decodedText);
        isDecodeEvoked = true;

    }
    private void CaesarCipher(Editable base){
        String encodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++) {

            int currentCharValue = (int) defaultInput.charAt(i);

            if(characterValidator.isSpecialCharacter(currentCharValue)) {
                encodedText += (char)currentCharValue;
                continue;
            }

            if(characterValidator.isInvalidCharacter((currentCharValue))) continue;

            int asciiValue = currentCharValue - key;

            if(characterValidator.isCaesarEncodeSpecialCase(currentCharValue)) asciiValue += 26;

            encodedText += (char)asciiValue;
        }

        caesarText.setText(encodedText);
        isCaesarEvoked = true;

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
                    isCaesarEvoked = true;
                    CaesarDecoder(s);
                    //isDecodeEvoked = false;
                }
                isDecodeEvoked = false;
            }
        };

        normalInput = (EditText) findViewById(R.id.CaesarStringInput);
        caesarText = (EditText) findViewById(R.id.CaesarStringOutput);

        characterValidator = new ASCIIUtils();

        normalInput.addTextChangedListener(normaltoCaesarListener);
        caesarText.addTextChangedListener(CaesartoNormalListener);
    }
}
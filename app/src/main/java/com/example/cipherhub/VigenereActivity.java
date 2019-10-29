package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VigenereActivity extends AppCompatActivity {

    EditText inputText;
    EditText keyText;
    EditText vigenereText;
    TextView keyView;
    TextView newKeyView;

    VigenereCipher vigenereCipher;

    String keyTemplate;
    String currentkeyTemplate;
    String keyString;
    boolean isEncodeEvoked;
    boolean isDecodeEvoked;

    private void updateVariables() {
        keyTemplate = vigenereCipher.getKeyTemplate();
        currentkeyTemplate = vigenereCipher.getCurrentKeyTemplate();
        keyString = vigenereCipher.getKeyString();
        isEncodeEvoked = vigenereCipher.getEncodeState();
        isDecodeEvoked = vigenereCipher.getDecodeState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vigenere);

        inputText = (EditText) findViewById(R.id.VigenereStringInput);
        keyText = (EditText) findViewById(R.id.VigenereKey);
        vigenereText = (EditText) findViewById(R.id.ViginereStringOutput);
        keyView = (TextView) findViewById(R.id.KeyView);
        newKeyView = (TextView) findViewById(R.id.NewKeyView);

        vigenereCipher = new VigenereCipher();

        final TextWatcher InputToVigenereListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    //vigenereCipher.setKeyString(vigenereCipher.getKeyString()).setCurrentKeyTemplate(vigenereCipher.getCurrentKeyTemplate()).setTemplate(vigenereCipher.getKeyTemplate()).setEncodeEvoked(vigenereCipher.getEncodeState()).setDecodeEvoked(vigenereCipher.getDecodeState());

                    updateVariables();

                    if(keyTemplate.length() > s.length() || keyTemplate.isEmpty()) {
                        vigenereCipher.setKeyString(keyTemplate);
                        keyView.setText(vigenereCipher.getKeyString());
                        vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                        return; //< or >?
                    }

                    if(!isEncodeEvoked) {

                        if(vigenereCipher.isKeyChanged(keyTemplate, currentkeyTemplate)) { //replace key if key is changed
                            //should use compareEquals
                            //need to keep track of templates
                            //crashes
                            vigenereCipher.setKeyString(keyString.replaceAll(currentkeyTemplate, keyTemplate)); //crashes if key is entirely changed
                            vigenereCipher.setTemplate(keyTemplate);
                            vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                            vigenereCipher.setKeyString(currentkeyTemplate);
                        } else {
                            vigenereCipher.setKeyString(vigenereCipher.updateEncodingKey(s));
                            keyView.setText(vigenereCipher.getKeyString());
                        }
                        //keyString = vigenereCipher.getKeyString();

                        newKeyView.setText(keyTemplate);
                        vigenereCipher.setDecodeEvoked(true);
                        updateVariables();
                        vigenereText.setText(vigenereCipher.VigenereEncode(keyString, s.toString()));
                    }

                    vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                    //keyString = previousKeyString;
                    vigenereCipher.setEncodeEvoked(false); //might change

            }
        };

        final TextWatcher VigenereToInputListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) { //ALWAYS USE THE VARIABLE "s" AND NOT YOUR BULLSHIT EDITTEXTs

                    updateVariables();

                    if(keyTemplate.length() > s.length() || keyTemplate.isEmpty()) {
                        vigenereCipher.setKeyString(keyTemplate);
                        vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                        return;
                    }


                    if(!isDecodeEvoked) {

                        if(vigenereCipher.isKeyChanged(keyTemplate, currentkeyTemplate)) { //reset everything if key has changed
                            //should use compareEquals
                            //need to keep track of templates
                            vigenereCipher.setKeyString(keyString.replaceAll(currentkeyTemplate, keyTemplate)); //crashes if key is changed entirely
                            vigenereCipher.setTemplate(keyTemplate);
                            vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                            vigenereCipher.setKeyString(currentkeyTemplate);
                        } else {
                            vigenereCipher.updateDecodingKey(s);
                        }
                        keyView.setText(keyString);
                        vigenereCipher.setEncodeEvoked(true);
                        updateVariables();
                        inputText.setText(vigenereCipher.VigenereDecode(keyString, s.toString()));
                    }

                    vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                    vigenereCipher.setDecodeEvoked(false);
            }
        };

        TextWatcher KeyListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vigenereCipher.setTemplate(s.toString());
                keyTemplate = vigenereCipher.getKeyTemplate();
                //need to add functionality if user decided to actually replace key after it's been converted
                //crashes currently at that
                //if(keyTemplate.length() < inputText.length()) InputToVigenereListener.afterTextChanged((Editable) inputText);
                //else if(keyTemplate.length() < vigenereText.length()) VigenereToInputListener.afterTextChanged((Editable) vigenereText);
            }
        };

        inputText.addTextChangedListener(InputToVigenereListener); //adds the listener TO the object (not the other way around)
        vigenereText.addTextChangedListener(VigenereToInputListener);
        keyText.addTextChangedListener(KeyListener);
    }
}

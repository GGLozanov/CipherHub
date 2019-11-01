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
                        vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                       // vigenereCipher.updateEncodingKey(s);
                        return; //< or >?
                    }

                    if(!isEncodeEvoked) {

                        if(vigenereCipher.isKeyChanged(keyTemplate, currentkeyTemplate)) { //replace key if key is changed
                            //should use compareEquals
                            //need to keep track of templates
                            //crashes
                            vigenereCipher.setKeyString(keyString.replaceAll(currentkeyTemplate, keyTemplate)); //crashes if key is entirely changed
                            if(vigenereCipher.keyExceedsMessage(s)) vigenereCipher.trimKeyString(s); //trims the key if the new one is too long
                            vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                            vigenereCipher.resetEncodeIndexCounter();
                        }
                        else vigenereCipher.updateEncodingKey(s);
                        updateVariables();
                        keyView.setText(keyString);
                        vigenereCipher.setDecodeEvoked(true);
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
                        //newKeyView.setText(currentkeyTemplate);
                        //keyView.setText(keyTemplate);
                        return;
                    }

                    //PHANTOM LETTERS WHEN DELETING PARTS OF KEY AND WHEN WRITING

                    if(!isDecodeEvoked) {

                        if(vigenereCipher.isKeyChanged(keyTemplate, currentkeyTemplate)) { //reset everything if key has changed
                            //should use compareEquals
                            //need to keep track of template
                            vigenereCipher.setKeyString(keyString.replaceAll(currentkeyTemplate, keyTemplate)); //crashes if key is changed entirely
                            if(vigenereCipher.keyExceedsMessage(s)) vigenereCipher.trimKeyString(s);
                            vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                            vigenereCipher.resetDecodeIndexCounter();
                        }
                        else vigenereCipher.updateDecodingKey(s);
                        updateVariables();
                        keyView.setText(keyString);
                        vigenereCipher.setEncodeEvoked(true);
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
                Editable inputTextEditable = inputText.getText();
                Editable vigenereTextEditable = vigenereText.getText();
                if(keyTemplate.length() <= inputText.length()) InputToVigenereListener.afterTextChanged(inputTextEditable);
                else if(keyTemplate.length() <= vigenereText.length()) VigenereToInputListener.afterTextChanged(vigenereTextEditable);
            }
        };

        vigenereCipher.setKeyText((EditText) findViewById(R.id.VigenereKey));
        vigenereCipher.setIOTexts((EditText) findViewById(R.id.VigenereStringInput), (EditText) findViewById(R.id.VigenereStringOutput));

        inputText = vigenereCipher.getInputText();
        keyText = vigenereCipher.getKeyText();
        vigenereText = vigenereCipher.getOutputText();

        inputText.addTextChangedListener(InputToVigenereListener); //adds the listener TO the object (not the other way around)
        vigenereText.addTextChangedListener(VigenereToInputListener);
        keyText.addTextChangedListener(KeyListener);
    }
}

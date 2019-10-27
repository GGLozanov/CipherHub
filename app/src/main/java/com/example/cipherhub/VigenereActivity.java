package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VigenereActivity extends AppCompatActivity {

    EditText inputText;
    EditText keyText;
    EditText vigenereText;
    TextView keyView;
    TextView newKeyView;

    boolean isEncodeEvoked = false;
    boolean isDecodeEvoked = false;

    String keyString = "";
    String keyTemplate = "";
    String currentkeyTemplate = "";

    ASCIIUtils characterValidator;

    int keyEncodeIndexCounter = 0;
    int keyDecodeIndexCounter = 0;

    private void VigenereEncode(String key, String base) {
        //String elongatedKey = "ababa";
        //creates a string instance with allocated memory equal to that of an allocated char array of size base string's length
        //Uses the newly constructed String's replace() method with arguments oldCharacter and newCharacter, the former meaning characters up to the terminating null character, and the latter, the repeated string.
        // The new char array's characters are replaced from NULL to base (the ones you wish)

        System.out.println(key);

        //ABCDEFGHIJKLEM -> base
        //LEMONLEMONLEMO -> elongated_key

        String encodedText = "";

        for(int counter = 0; counter < base.length(); counter++) {

            int baseasciiValue = base.charAt(counter);
            int keyasciiValue = key.charAt(counter);

            if(characterValidator.isSpecialCharacter(baseasciiValue)) {
                encodedText += (char) (baseasciiValue);
                continue;
            }

            if(characterValidator.isSpecialCharacter(keyasciiValue)) {
                encodedText += (char) (keyasciiValue);
                continue;
            }
            if(characterValidator.isInvalidCharacter(baseasciiValue) || characterValidator.isInvalidCharacter(keyasciiValue)) continue;
            if(characterValidator.isInvalidCharacter(baseasciiValue) || characterValidator.isInvalidCharacter(keyasciiValue)) continue;
            if(characterValidator.isCapitalLetter(baseasciiValue) && characterValidator.isCapitalLetter(keyasciiValue)) {
                baseasciiValue -= 'A';
                keyasciiValue -= 'A';

                if(baseasciiValue + keyasciiValue + 'A' > 'Z') {
                    encodedText += (char) ('A' + ((baseasciiValue + keyasciiValue + 'A') - 'Z') - 1);
                }
                else encodedText += (char) ('A' + (baseasciiValue + keyasciiValue));
            }
            else if((characterValidator.isLowercaseLetter(baseasciiValue) && characterValidator.isLowercaseLetter(keyasciiValue))) {
                baseasciiValue -= 'a';
                keyasciiValue -= 'a';
                if(baseasciiValue + keyasciiValue + 'a' > 'z') {
                    encodedText += (char) ('a' + ((baseasciiValue + keyasciiValue + 'a')  - 'z') - 1);
                }
                else encodedText += (char) ('a' + baseasciiValue + keyasciiValue);
            }
        }

        vigenereText.setText(encodedText);
        isEncodeEvoked = true;
    }

    private void VigenereDecode(String key, String base) {
        //Vigenere doesn't actually decode, only encodes further
        //should implement decoding

        //String elongatedKey = "ababa";
        Log.i("Debug", key);

        String decodedText = "";

        for(int counter = 0; counter < base.length(); counter++) {

                int baseasciiValue = base.charAt(counter);
                int keyasciiValue = key.charAt(counter);

                if(characterValidator.isSpecialCharacter(baseasciiValue)) {
                    decodedText += (char) (baseasciiValue);
                    continue;
                }
                if(characterValidator.isSpecialCharacter(keyasciiValue)) {
                    decodedText += (char) (keyasciiValue);
                    continue;
                }
                if(characterValidator.isInvalidCharacter(baseasciiValue) || characterValidator.isInvalidCharacter(keyasciiValue)) continue;
                if(characterValidator.isCapitalLetter(baseasciiValue) && characterValidator.isCapitalLetter(keyasciiValue)) {
                    baseasciiValue -= 'A';
                    keyasciiValue -= 'A';
                    if(baseasciiValue + keyasciiValue + 'A' > 'Z') {
                        decodedText += (char) ('A' + ((baseasciiValue + keyasciiValue + 'A') - 'Z') - 1);
                    }
                    else decodedText += (char) ('A' + (baseasciiValue + keyasciiValue));
                }
                else if((characterValidator.isLowercaseLetter(baseasciiValue) && characterValidator.isLowercaseLetter(keyasciiValue))) {
                    baseasciiValue -= 'a';
                    keyasciiValue -= 'a';
                    if(baseasciiValue + keyasciiValue + 'a' > 'z') {
                        decodedText += (char) ('a' + ((baseasciiValue + keyasciiValue + 'a') - 'z') - 1);
                    }
                    else decodedText += (char) ('a' + (baseasciiValue + keyasciiValue) );
                }
        }

        inputText.setText(decodedText);
        isDecodeEvoked = true;
    }

    private String EnlargeKey(String template, Editable s, int characterIndex, boolean mode) {

        if(!characterValidator.isInvalidCharacter((int) s.toString().charAt(characterIndex)) ||
                !characterValidator.isSpecialCharacter((int) s.toString().charAt(characterIndex))) keyString += template.charAt(characterIndex);

        //need to add ability to shorten key if Editable is shortened (!)
        //keep track of previous Editable?

        if(keyString.length() > s.length()) {
            keyString = keyString.substring(0, Math.min(keyString.length(), s.length())); //crashes if there is no substring or key is smaller than editable
            if(mode) keyEncodeIndexCounter = 0;
            else keyDecodeIndexCounter = 0;
        }

        return keyString;
    }

    private boolean isKeyChanged(String keyString, String previousKeyString) {
        return !previousKeyString.equals(keyString);
    }

    private void updateEncodingKey(Editable s) {
        if(keyEncodeIndexCounter == keyTemplate.length()) keyEncodeIndexCounter = 0;
        keyString = EnlargeKey(keyTemplate, s, keyEncodeIndexCounter++, true);
    }

    private void updateDecodingKey(Editable s) {
        if(keyDecodeIndexCounter == keyTemplate.length()) keyDecodeIndexCounter = 0;
        EnlargeKey(keyTemplate, s, keyDecodeIndexCounter++, false); //goes out of bounds when changed
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

        characterValidator = new ASCIIUtils();

        TextWatcher KeyListener = new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {
                 keyTemplate = s.toString();
                 //need to add functionality if user decided to actually replace key after it's been converted
                 //crashes currently at that

             }
         };

        TextWatcher InputToVigenereListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if(keyTemplate.length() > s.length() || keyTemplate.isEmpty()) return; //< or >?

                    if(!isEncodeEvoked) {
                        if(keyTemplate.length() == s.length()) {
                            currentkeyTemplate = keyTemplate;
                            keyString = currentkeyTemplate;
                        }
                        if(isKeyChanged(keyTemplate, currentkeyTemplate)) { //replace key if key is changed
                            //should use compareEquals
                            //need to keep track of templates
                            keyString = keyString.replaceAll(currentkeyTemplate, keyTemplate);
                            currentkeyTemplate = keyTemplate;
                            keyEncodeIndexCounter = 0;
                        } else {
                            updateEncodingKey(s);
                        }
                        keyView.setText(keyString);
                        newKeyView.setText(keyTemplate);
                        isDecodeEvoked = true;
                        VigenereEncode(keyString, s.toString());
                    }

                    currentkeyTemplate = keyTemplate;
                    //keyString = previousKeyString;
                    isEncodeEvoked = false; //might change
            }
        };

        TextWatcher VigenereToInputListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) { //ALWAYS USE THE VARIABLE "s" AND NOT YOUR BULLSHIT EDITTEXTs
                if(keyString.length() > s.length() || keyString.isEmpty()) return;

                if(!isDecodeEvoked) {
                        if(keyString.length() == s.length()) {
                            currentkeyTemplate = keyTemplate;
                            keyString = currentkeyTemplate;
                        }
                        if(isKeyChanged(keyTemplate, currentkeyTemplate)) { //reset everything if key has changed
                            //should use compareEquals
                            //need to keep track of templates
                            keyString = keyString.replaceAll(currentkeyTemplate, keyTemplate);
                            currentkeyTemplate = keyTemplate;
                            keyDecodeIndexCounter = 0;
                        } else {
                            updateDecodingKey(s);
                        }
                        keyView.setText(keyString);
                        newKeyView.setText(currentkeyTemplate);
                        isEncodeEvoked = true;
                        VigenereDecode(keyString, s.toString());
                    }

                    currentkeyTemplate = keyTemplate;
                    isDecodeEvoked = false;
            }
        };

        inputText.addTextChangedListener(InputToVigenereListener); //adds the listener TO the object (not the other way around)
        vigenereText.addTextChangedListener(VigenereToInputListener);
        keyText.addTextChangedListener(KeyListener);
    }
}

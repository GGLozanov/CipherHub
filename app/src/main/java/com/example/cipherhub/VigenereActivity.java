package com.example.cipherhub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class VigenereActivity extends AppCompatActivity {

    EditText inputText;
    EditText keyText;
    EditText vigenereText;

    private boolean isEncodeEvoked = false;
    private boolean isDecodeEvoked = false;

    public String keyString;

    private void VigenereEncode(String key, String base) {
        String elongatedKey = new String(new char[base.length()]).replace("\0", key);

        //creates a string instance with allocated memory equal to that of an allocated char array of size base string's length
        //Uses the newly constructed String's replace() method with arguments oldCharacter and newCharacter, the former meaning characters up to the terminating null character, and the latter, the repeated string.
        // The new char array's characters are replaced from NULL to base (the ones you wish)

        System.out.println(elongatedKey);

        //ABCDEFGHIJKLEM -> base
        //LEMONLEMONLEMO -> elongated_key

        String encodedText = "";

        for(int counter = 0; counter < base.length(); counter++) {
            int baseasciiValue = base.charAt(counter);
            int keyasciiValue = elongatedKey.charAt(counter);
            encodedText += (char) ((baseasciiValue + keyasciiValue) - '@');
        }

        vigenereText.setText(encodedText);
        isEncodeEvoked = true;
    }

    private void VigenereDecode(String key, String base) {
        String elongatedKey = new String(new char[base.length()]).replace("\0", key);

        Log.i("Debug", elongatedKey);

        String decodedText = "";

        for(int counter = 0; counter < base.length(); counter++) {
                int baseasciiValue = base.charAt(counter);
                int keyasciiValue = elongatedKey.charAt(counter);
                decodedText += (char) ((baseasciiValue + keyasciiValue) - '@');
        }

        inputText.setText(decodedText);
        isDecodeEvoked = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vigenere);

         inputText = (EditText) findViewById(R.id.VigenereStringInput);
         keyText = (EditText) findViewById(R.id.VigenereKey);
         vigenereText = (EditText) findViewById(R.id.ViginereStringOutput);

        TextWatcher InputToVigenereListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    keyString = keyText.toString();
                    if(/*keyString.length() > s.toString().length() || */keyString.isEmpty())return;
                    if(!isEncodeEvoked) {
                        isDecodeEvoked = true;
                        VigenereEncode(keyString, inputText.toString());
                    }
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
            public void afterTextChanged(Editable s) {
                keyString = keyText.toString();
                if(/*keyString.length() > s.toString().length() || */keyString.isEmpty())return;
                if(!isDecodeEvoked) {
                        isEncodeEvoked = true;
                        VigenereDecode(keyString, vigenereText.toString());
                    }
                    isDecodeEvoked = false;
            }
        };

        inputText.addTextChangedListener(InputToVigenereListener); //adds the listener TO the object (not the other way around)
        vigenereText.addTextChangedListener(VigenereToInputListener);
    }
}

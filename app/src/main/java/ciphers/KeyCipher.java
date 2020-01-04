package ciphers;

import android.util.Log;
import android.widget.EditText;

public abstract class KeyCipher extends Cipher {

    private EditText keyText;

    public void setKeyText(EditText keyText) {this.keyText = keyText;}
    public EditText getKeyText() {return keyText;}

    String keyString;
    String keyTemplate;
    String currentkeyTemplate;

    int keyEncodeIndexCounter;
    int keyDecodeIndexCounter;

    public void setKeyString(String key) {keyString = key;}
    public void setTemplate(String template) {keyTemplate = template;}
    public void setCurrentKeyTemplate(String currTemplate) {currentkeyTemplate = currTemplate;}

    public String getKeyTemplate() {return keyTemplate;}
    public String getCurrentKeyTemplate() {return currentkeyTemplate;}
    public String getKeyString() {return keyString;}

    public void resetEncodeIndexCounter() {keyEncodeIndexCounter = 0;}
    public void resetDecodeIndexCounter() {keyDecodeIndexCounter = 0;}

    public void enlargeEncodeKey(String s) {

        if(s.equals("")) return;

        char editTextCharacter = s.charAt(keyEncodeIndexCounter);

        if(!characterValidator.isSpecialCharacter((int) editTextCharacter) && !characterValidator.isInvalidCharacter((int) editTextCharacter)) keyString += keyTemplate.charAt(keyEncodeIndexCounter);
            // separate invalid check and special char check due to different ending outputs
        else {
            keyEncodeIndexCounter--;
            keyString += editTextCharacter;
        }

        // need to add ability to shorten key if Editable is shortened (!) -> implemented

        if(keyExceedsMessage(s)) {
            trimKeyString(s);
            resetEncodeIndexCounter();
        }
    }


    public void enlargeDecodeKey(String s) {

        if(s.equals("")) return;

        char editTextCharacter = s.charAt(keyDecodeIndexCounter);

        if(!characterValidator.isSpecialCharacter((int) editTextCharacter) && !characterValidator.isInvalidCharacter((int) editTextCharacter)) keyString += keyTemplate.charAt(keyDecodeIndexCounter);
        else {
            keyDecodeIndexCounter--;
            keyString += editTextCharacter;
        }

        //need to add ability to shorten key if Editable is shortened (!) -> implemented

        if(keyExceedsMessage(s)) {
            trimKeyString(s); //crashes if there is no substring or key is smaller than editable
            resetDecodeIndexCounter();
        }
    }


    public void updateEncodingKeyByBase(String inputString) { // method to update the key multiple times in a given range (length of a string, usually encoded/decoded text)
        for(int i = 0; i < inputString.length(); i++) {
            updateEncodingKey(inputString);
        }
    }

    public void updateDecodingKeyByBase(String inputString) {
        for(int i = 0; i < inputString.length(); i++) {
            updateDecodingKey(inputString);
        }
    }

    public boolean isKeyChanged(String keyString, String previousKeyString) {
        return !previousKeyString.equals(keyString);
    }

    public void updateEncodingKey(String s) {
        Log.d("Template", keyTemplate);
        Log.d("Encode Index", Integer.toString(keyEncodeIndexCounter));
        if(keyEncodeIndexCounter == keyTemplate.length()) resetEncodeIndexCounter();
        enlargeEncodeKey(s);
        Log.d("KeyString", keyString);
        keyEncodeIndexCounter++;
    }

    public void updateDecodingKey(String s) {
        if(keyDecodeIndexCounter == keyTemplate.length()) resetDecodeIndexCounter();
        enlargeDecodeKey(s); // goes out of bounds when changed
        keyDecodeIndexCounter++;
    }

    public void trimKeyString(String s) { // method to trim a string from the beginning to either the length of the input or the key
        keyString = keyString.substring(0, Math.min(keyString.length(), s.length()));
    }

    public boolean keyExceedsMessage(String s) {
        return getKeyString().length() > s.length();
    }
}

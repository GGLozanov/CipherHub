package ciphers;

import android.widget.EditText;

public abstract class KeyCipher extends Cipher {

    private EditText keyText;

    public void setKeyText(EditText keyText) {this.keyText = keyText;}
    public EditText getKeyText() {return keyText;}

    String key;
    String keyTemplate;
    String currentkeyTemplate;

    int keyEncodeIndexCounter;
    int keyDecodeIndexCounter;

    public void setKey(String key) {
        this.key = key;
    }

    public void setTemplate(String template) {keyTemplate = template;}
    public void setCurrentKeyTemplate(String currTemplate) {currentkeyTemplate = currTemplate;}

    public String getKeyTemplate() {return keyTemplate;}
    public String getCurrentKeyTemplate() {return currentkeyTemplate;}
    public String getKey() {return key;}

    public void resetEncodeIndexCounter() {keyEncodeIndexCounter = 0;}
    public void resetDecodeIndexCounter() {keyDecodeIndexCounter = 0;}

    public void enlargeEncodeKey(String s) {

        if(s.equals("")) return;

        key += keyTemplate.charAt(keyEncodeIndexCounter++);

        if(keyExceedsMessage(s)) {
            trimKeyString(s);
            resetEncodeIndexCounter();
        }
    }


    public void enlargeDecodeKey(String s) {

        if(s.equals("")) return;

        key += keyTemplate.charAt(keyDecodeIndexCounter++);

        // need to add ability to shorten key if Editable is shortened (!) -> implemented

        if(keyExceedsMessage(s)) {
            trimKeyString(s);
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
        if(keyEncodeIndexCounter == keyTemplate.length()) resetEncodeIndexCounter();
        enlargeEncodeKey(s);
    }

    public void updateDecodingKey(String s) {
        if(keyDecodeIndexCounter == keyTemplate.length()) resetDecodeIndexCounter();
        enlargeDecodeKey(s); // goes out of bounds when changed
    }

    public void trimKeyString(String s) { // method to trim a string from the beginning to either the length of the input or the key
        key = key.substring(0, Math.min(key.length(), s.length()));
    }

    public boolean keyExceedsMessage(String s) {
        return key.length() > s.length();
    }
}

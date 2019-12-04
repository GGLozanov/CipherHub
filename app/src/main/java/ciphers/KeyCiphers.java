package ciphers;

import android.widget.EditText;

class KeyCiphers extends Ciphers {

    EditText keyText;

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


    public void EnlargeKey(String template, String s, int characterIndex, boolean mode) {

        char editTextCharacter = s.charAt(characterIndex);

        System.out.print(keyString);

        if(/*!characterValidator.isInvalidCharacter((int) editTextCharacter) ||*/
                !characterValidator.isSpecialCharacter((int) editTextCharacter)) keyString += template.charAt(characterIndex);
            //separate invalid check and special char check due to different ending outputs
        else {
            if(mode) keyEncodeIndexCounter--;
            else keyDecodeIndexCounter--;
            keyString += editTextCharacter;
        }

        //need to add ability to shorten key if Editable is shortened (!) -> implemented

        if(keyExceedsMessage(s)) {
            trimKeyString(s); //crashes if there is no substring or key is smaller than editable
            if(mode) keyEncodeIndexCounter = 0;
            else keyDecodeIndexCounter = 0;
        }
    }

    public boolean isKeyChanged(String keyString, String previousKeyString) {
        return !previousKeyString.equals(keyString);
    }

    public void updateEncodingKey(String s) {
        if(keyEncodeIndexCounter == keyTemplate.length()) keyEncodeIndexCounter = 0;
        EnlargeKey(keyTemplate, s, keyEncodeIndexCounter++, true);
    }

    public void updateDecodingKey(String s) {
        if(keyDecodeIndexCounter == keyTemplate.length()) keyDecodeIndexCounter = 0;
        EnlargeKey(keyTemplate, s, keyDecodeIndexCounter++, false); //goes out of bounds when changed
    }

    public void trimKeyString(String s) {
        keyString = keyString.substring(0, Math.min(keyString.length(), s.length()));
    }

    public boolean keyExceedsMessage(String s) {
        return getKeyString().length() > s.length();
    }
}

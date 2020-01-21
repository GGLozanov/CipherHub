package ciphers;

import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

public abstract class Cipher { //superclass for all ciphers

    ASCIIUtils characterValidator = new ASCIIUtils();

    private EditText inputText;
    private EditText outputText;

    String encodedText = "";
    String decodedText = "";

    int currentCharacter;

    public void setIOTexts(EditText inputText, EditText outputText) {this.inputText = inputText; this.outputText = outputText;}
    public EditText getInputText() {return inputText;}
    public EditText getOutputText() {return outputText;}

    boolean isEncodeEvoked = false;
    boolean isDecodeEvoked = false;

    public boolean getEncodeState() {return isEncodeEvoked;}
    public boolean getDecodeState() {return isDecodeEvoked;}

    public void setEncodeEvoked(boolean encodeEvoke) {isEncodeEvoked = encodeEvoke;}
    public void setDecodeEvoked(boolean decodeEvoke) {isDecodeEvoked = decodeEvoke;}

    public ASCIIUtils getCharacterValidator() {
        return characterValidator;
    }

    protected boolean isSpecialCase(char character) {
        if(characterValidator.isSpecialCharacter(character)) {
            // sharedprefs whether to ignore or include characters not in key
            encodedText += character;
            return true;
        }

        if(characterValidator.isInvalidCharacter(character)) {
            return true;
        }

        return false;
    }

    protected boolean isSpecialCase(String key) {
        for(int i = 0; i < key.length(); i++) {
            char character = key.charAt(i);
            if(characterValidator.isSpecialCharacter(character)) {
                // sharedprefs whether to ignore or include characters not in key
                encodedText += character;
                return true;
            }

            if(characterValidator.isInvalidCharacter(character)) {
                return true;
            }
        }

        return false;
    }
}

// Polybius for Cyrillic...?

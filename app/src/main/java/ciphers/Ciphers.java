package ciphers;

import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

public class Ciphers {

    ASCIIUtils characterValidator = new ASCIIUtils();

    EditText inputText;
    EditText outputText;

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

    public boolean updateEncodeEvoked(boolean encodeEvoke) {return isEncodeEvoked = encodeEvoke;}
    public boolean updateDecodeEvoked(boolean decodeEvoke) {return isDecodeEvoked = decodeEvoke;}

    public ASCIIUtils getCharacterValidator() {
        return characterValidator;
    }
}

//Polybius for Cyrillic...?

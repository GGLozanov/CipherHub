package com.example.cipherhub;

import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

class Ciphers {

    ASCIIUtils characterValidator = new ASCIIUtils();

    EditText inputText;
    EditText outputText;

    String encodedText = "";
    String decodedText = "";

    int currentCharacter;

    void setIOTexts(EditText inputText, EditText outputText) {this.inputText = inputText; this.outputText = outputText;}
    EditText getInputText() {return inputText;}
    EditText getOutputText() {return outputText;}

    boolean isEncodeEvoked = false;
    boolean isDecodeEvoked = false;

    boolean getEncodeState() {return isEncodeEvoked;}
    boolean getDecodeState() {return isDecodeEvoked;}

    void setEncodeEvoked(boolean encodeEvoke) {isEncodeEvoked = encodeEvoke;}
    void setDecodeEvoked(boolean decodeEvoke) {isDecodeEvoked = decodeEvoke;}

    boolean updateEncodeEvoked(boolean encodeEvoke) {return isEncodeEvoked = encodeEvoke;}
    boolean updateDecodeEvoked(boolean decodeEvoke) {return isDecodeEvoked = decodeEvoke;}

}

class KeyCiphers extends Ciphers {

    EditText keyText;

    void setKeyText(EditText keyText) {this.keyText = keyText;}
    EditText getKeyText() {return keyText;}

    String keyString;
    String keyTemplate;
    String currentkeyTemplate;

    int keyEncodeIndexCounter;
    int keyDecodeIndexCounter;

    void setKeyString(String key) {keyString = key;}
    void setTemplate(String template) {keyTemplate = template;}
    void setCurrentKeyTemplate(String currTemplate) {currentkeyTemplate = currTemplate;}

    String getKeyTemplate() {return keyTemplate;}
    String getCurrentKeyTemplate() {return currentkeyTemplate;}
    String getKeyString() {return keyString;}

    void resetEncodeIndexCounter() {keyEncodeIndexCounter = 0;}
    void resetDecodeIndexCounter() {keyDecodeIndexCounter = 0;}


    void EnlargeKey(String template, Editable s, int characterIndex, boolean mode) {

        char editTextCharacter = s.toString().charAt(characterIndex);

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

    boolean isKeyChanged(String keyString, String previousKeyString) {
        return !previousKeyString.equals(keyString);
    }

    void updateEncodingKey(Editable s) {
        if(keyEncodeIndexCounter == keyTemplate.length()) keyEncodeIndexCounter = 0;
        EnlargeKey(keyTemplate, s, keyEncodeIndexCounter++, true);
    }

    void updateDecodingKey(Editable s) {
        if(keyDecodeIndexCounter == keyTemplate.length()) keyDecodeIndexCounter = 0;
        EnlargeKey(keyTemplate, s, keyDecodeIndexCounter++, false); //goes out of bounds when changed
    }

    void trimKeyString(Editable s) {
        keyString = keyString.substring(0, Math.min(keyString.length(), s.length()));
    }

    boolean keyExceedsMessage(Editable s) {
        return getKeyString().length() > s.length();
    }
}

class CaesarCipher extends Ciphers {

    private int key;

    CaesarCipher(int key) {this.key = key;}

    String CaesarDecoder(Editable base){
        decodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++) {

            int currentCharValue = (int) defaultInput.charAt(i);

            if(characterValidator.isSpecialCharacter(currentCharValue)) {
                decodedText += (char)currentCharValue;
                continue;
            }

            if(characterValidator.isInvalidCharacter((currentCharValue))) continue;

            int asciiValue = currentCharValue + key;

            if(characterValidator.isCaesarDecodeSpecialCase(currentCharValue, currentCharValue > 'Z' ? ('z' - key) : ('Z' - key))) asciiValue -= 26;

            //if(((asciiValue != ' ' && (asciiValue < 'A')) || (asciiValue > 'z'))continue;

            decodedText += (char)asciiValue;
        }

        isDecodeEvoked = true;
        return decodedText;
    }

    String CaesarEncoder(Editable base){
        encodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++) {

            int currentCharValue = (int) defaultInput.charAt(i);

            if(characterValidator.isSpecialCharacter(currentCharValue)) {
                encodedText += (char)currentCharValue;
                continue;
            }

            if(characterValidator.isInvalidCharacter((currentCharValue))) continue;

            int asciiValue = currentCharValue - key;

            if(characterValidator.isCaesarEncodeSpecialCase(currentCharValue, characterValidator.isLowercaseLetter(currentCharValue) ? (key + 'a') : (key + 'A'))) asciiValue += 26;

            encodedText += (char)asciiValue;
        }

        isEncodeEvoked = true;
        return encodedText;
    }
}

class VigenereCipher extends KeyCiphers {

    VigenereCipher() {keyString = ""; keyTemplate =  ""; currentkeyTemplate = ""; keyEncodeIndexCounter = 0; keyDecodeIndexCounter = 0;}

    String VigenereEncode(String key, String base) {

        encodedText = "";
        int sumASCIIValues;
        //Vigenere encode formula: (base + key) - 'A'

        for(int counter = 0; counter < key.length(); counter++) {

            int baseASCIIValue = base.charAt(counter);
            int keyASCIIValue = key.charAt(counter);

            if(characterValidator.isSpecialCharacter(baseASCIIValue)) {
                encodedText += (char) (baseASCIIValue);
                continue;
            }

            if(characterValidator.isSpecialCharacter(keyASCIIValue)) {
                encodedText += (char) (keyASCIIValue);
                continue;
            }

            if(characterValidator.isInvalidCharacter(baseASCIIValue) || characterValidator.isInvalidCharacter(keyASCIIValue)) continue;

            if(Character.isUpperCase(baseASCIIValue) && Character.isUpperCase(keyASCIIValue)) {

                sumASCIIValues = characterValidator.CalculateVigenereEncodeUppercaseValue(baseASCIIValue, keyASCIIValue);

                if(characterValidator.isCaesarDecodeSpecialCase(sumASCIIValues, 'Z')) { //vigenere always uses decode caesar (3 forward)
                    encodedText += (char) (sumASCIIValues - 26);
                }
                else encodedText += (char) (sumASCIIValues);
            }
            else if(Character.isLowerCase(baseASCIIValue) && Character.isLowerCase(keyASCIIValue)) {

                sumASCIIValues = characterValidator.CalculateVigenereEncodeLowercaseValue(baseASCIIValue, keyASCIIValue);

                if(characterValidator.isCaesarDecodeSpecialCase(sumASCIIValues, 'z')) {
                    encodedText += (char) (sumASCIIValues  - 26);
                }
                else encodedText += (char) (sumASCIIValues);
            }
            else {
                sumASCIIValues = characterValidator.CalculateVigenereDecodeLowercaseValue(baseASCIIValue, keyASCIIValue); //lowercase for default
                encodedText += (char) (sumASCIIValues);
            }
        }

        isEncodeEvoked = true;
        return encodedText;
    }

    String VigenereDecode(String key, String base) {
        //Vigenere doesn't actually decode, only encodes further
        //should implement decoding
        //One letter is omitted for some reason
        //Implement constant function for Caesar cipher that can work for Vigenere as well in ASCIIUtils

        decodedText = "";
        int sumASCIIValues;
        //Vigenere decode formula: (base + 'A') - key
        //use Character class and delete redundant methods from ASCIIUtils

        for(int counter = 0; counter < key.length(); counter++) {

            int baseASCIIValue = base.charAt(counter);
            int keyASCIIValue = key.charAt(counter);

            if(characterValidator.isSpecialCharacter(baseASCIIValue)) {
                decodedText += (char) (baseASCIIValue);
                continue;
            }
            if(characterValidator.isSpecialCharacter(keyASCIIValue)) {
                decodedText += (char) (keyASCIIValue);
                continue;
            }
            if(characterValidator.isInvalidCharacter(baseASCIIValue) || characterValidator.isInvalidCharacter(keyASCIIValue)) continue;

            if(Character.isUpperCase(baseASCIIValue) || Character.isUpperCase(keyASCIIValue)) {

                sumASCIIValues = characterValidator.CalculateVigenereDecodeUppercaseValue(baseASCIIValue, keyASCIIValue);
                if(characterValidator.isCaesarEncodeSpecialCase(sumASCIIValues, 'A')) { //decoding for Vigenere is encoding for Caesar
                    decodedText += (char) ((sumASCIIValues + 26)); //
                }
                else decodedText += (char) (sumASCIIValues);
            }

            else if(Character.isLowerCase(baseASCIIValue) || Character.isLowerCase(keyASCIIValue)) {

                sumASCIIValues = characterValidator.CalculateVigenereDecodeLowercaseValue(baseASCIIValue, keyASCIIValue);
                if(characterValidator.isCaesarEncodeSpecialCase(sumASCIIValues, 'a')) {
                    decodedText += (char) (sumASCIIValues + 26);
                }
                else decodedText += (char) (sumASCIIValues);
            }
            else {
                sumASCIIValues = characterValidator.CalculateVigenereDecodeLowercaseValue(baseASCIIValue, keyASCIIValue); //lowercase for default
                decodedText += (char) (sumASCIIValues);
            }
        }

        isDecodeEvoked = true;
        return decodedText;
    }
}

class AtbashCipher extends KeyCiphers {

    private String reverseLowerKeyString = "";
    private String reverseUpperKeyString = "";

    //keyString and keyTemplate are both keys, just one is lowercase and the other is uppercase

    private void reverseLowerKeyString() {
        for(int counter = keyString.length() - 1; counter >= 0; counter--) { //equal to gives one more iteration (includes 'a')
            reverseLowerKeyString += keyString.charAt(counter);
        }
    }

    private void reverseUpperKeyString() {
        for(int counter = keyTemplate.length() - 1; counter >= 0; counter--) {
            reverseUpperKeyString += keyTemplate.charAt(counter);
        }
    }

    //default key for now is alphabet; will add support for more in the future
    AtbashCipher(String key) {
        keyString = key;
        keyTemplate = keyString.toUpperCase();
        reverseUpperKeyString();
        reverseLowerKeyString();
    }

    String AtbashEncode(String base) {
        encodedText = "";

        for(int i = 0; i < base.length(); i++) {
            char currentCharacter = base.charAt(i);

            if(characterValidator.isInvalidCharacter(currentCharacter)) continue;
            if(characterValidator.isSpecialCharacter(currentCharacter)) encodedText += currentCharacter;

            if(characterValidator.isLowercaseLetter(currentCharacter)) {
                encodedText += reverseLowerKeyString.charAt(keyString.indexOf(currentCharacter));
            }
            else if (characterValidator.isCapitalLetter(currentCharacter)) {
                encodedText += reverseUpperKeyString.charAt(keyTemplate.indexOf(currentCharacter));
            }
        }

        isEncodeEvoked = true;
        return encodedText;
    }

    String AtbashDecode(String base) {
        decodedText = "";

        for(int i = 0; i < base.length(); i++) {
            char currentCharacter = base.charAt(i);

            if(characterValidator.isInvalidCharacter(currentCharacter)) continue;
            if(characterValidator.isSpecialCharacter(currentCharacter)) decodedText += currentCharacter;

            if(characterValidator.isLowercaseLetter(currentCharacter)) {
                decodedText += keyString.charAt(reverseLowerKeyString.indexOf(currentCharacter));
            }
            else if (characterValidator.isCapitalLetter(base.charAt(i))) {
                decodedText += keyTemplate.charAt(reverseUpperKeyString.indexOf(currentCharacter));
            }
        }

        isDecodeEvoked = true;
        return decodedText;
    }
}

class PolybiusCipher extends Ciphers {

    static final Character[][] PolybiusSquare = {
            {'A', 'B', 'C', 'D', 'E', 'F'},
            {'G', 'H', 'I', 'J', 'K', 'L'},
            {'M', 'N', 'O', 'P', 'Q', 'R'},
            {'S', 'T', 'U', 'V', 'W', 'X'},
            {'Y', 'Z', '0', '1', '2', '3'},
            {'4', '5', '6', '7', '8', '9'}
    };


    String PolybiusEncode(String base) {
        encodedText = "";

        //TO-DO: Put these variables in superclass Ciphers
        //add functionality for user to choose lowercase or uppercase

        for(int i = 0; i < base.length(); i++) {

            currentCharacter = base.charAt(i);

            if(characterValidator.isInvalidCharacter(currentCharacter)) continue;
            if(characterValidator.isSpecialCharacter(currentCharacter)) encodedText += (char)currentCharacter;
            else {
                if(characterValidator.isLowercaseLetter(currentCharacter)) currentCharacter = characterValidator.convertToUppercase(currentCharacter);
                encodedText += (char) characterValidator.convertToASCIINumber(characterValidator.findElementRow(PolybiusSquare, (char)currentCharacter));
                encodedText += (char) characterValidator.convertToASCIINumber(characterValidator.findElementColumn(PolybiusSquare, (char)currentCharacter));
            }
        }

        return encodedText;
    }

    String PolybiusDecode(String base, String inputText) {
        decodedText = "";
        Log.d("Input; ", inputText);

        //evaluate exception in activity file -> if(base.length() % 2 != 0)

        for(int i = 0, counter = 0; i < base.length(); i += 2, counter++) {
            currentCharacter = base.charAt(i);
            int nextCharacter = base.charAt(i + 1);

            if(!characterValidator.isNumber(currentCharacter) || !characterValidator.isNumber(nextCharacter) ||
                    characterValidator.isInvalidCharacter(currentCharacter) || characterValidator.isInvalidCharacter(nextCharacter)) continue;
            else if(characterValidator.isSpecialCharacter(currentCharacter)) decodedText += (char)currentCharacter;
            else if(characterValidator.isSpecialCharacter(nextCharacter)) decodedText += (char)nextCharacter;

            int x = characterValidator.convertToNumber(currentCharacter), y = characterValidator.convertToNumber(nextCharacter);
            if(x > 6 || y > 6) continue;
            //Fix uppercase and lowercase bug
            if(characterValidator.isLowercaseLetter(inputText.charAt(i))) decodedText += (char)characterValidator.convertToLowercase(PolybiusSquare[x - 1][y - 1]);
            else decodedText += PolybiusSquare[x - 1][y - 1];
        }

        return decodedText;
    }

}

//Polybius for Cyrillic

package com.example.cipherhub;

import android.text.Editable;

class Ciphers {

    ASCIIUtils characterValidator = new ASCIIUtils();

    boolean isEncodeEvoked = false;
    boolean isDecodeEvoked = false;

    boolean getEncodeState() {return isEncodeEvoked;}
    protected boolean getDecodeState() {return isDecodeEvoked;}

    Ciphers setEncodeEvoked(boolean encodeEvoke) {isEncodeEvoked = encodeEvoke; return this;}
    Ciphers setDecodeEvoked(boolean decodeEvoke) {isDecodeEvoked = decodeEvoke; return this;}

    boolean updateEncodeEvoked(boolean encodeEvoke) {return isEncodeEvoked = encodeEvoke;}
    boolean updateDecodeEvoked(boolean decodeEvoke) {return isDecodeEvoked = decodeEvoke;}

}

class CaesarCipher extends Ciphers {

    private int key;

    CaesarCipher(int key) {this.key = key;}

    String CaesarDecoder(Editable base){
        String decodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++) {

            int currentCharValue = (int) defaultInput.charAt(i);

            if(characterValidator.isSpecialCharacter(currentCharValue)) {
                decodedText += (char)currentCharValue;
                continue;
            }

            if(characterValidator.isInvalidCharacter((currentCharValue))) continue;

            int asciiValue = currentCharValue + key;

            if(characterValidator.isCaesarDecodeSpecialCase(currentCharValue, currentCharValue > 'Z' ? (key + 'a') - 1 : (key + 'A') - 1)) asciiValue -= 26;

            //if(((asciiValue != ' ' && (asciiValue < 'A')) || (asciiValue > 'z'))continue;

            decodedText += (char)asciiValue;
        }

        isDecodeEvoked = true;
        return decodedText;
    }

    String CaesarEncoder(Editable base){
        String encodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++) {

            int currentCharValue = (int) defaultInput.charAt(i);

            if(characterValidator.isSpecialCharacter(currentCharValue)) {
                encodedText += (char)currentCharValue;
                continue;
            }

            if(characterValidator.isInvalidCharacter((currentCharValue))) continue;

            int asciiValue = currentCharValue - key;

            if(characterValidator.isCaesarEncodeSpecialCase(currentCharValue, currentCharValue > 'Z' ? (key + 'a') - 1 : (key + 'A') - 1)) asciiValue += 26;

            encodedText += (char)asciiValue;
        }

        isEncodeEvoked = true;
        return encodedText;
    }
}

class VigenereCipher extends Ciphers {

    String keyString;
    String keyTemplate;
    String currentkeyTemplate;

    int keyEncodeIndexCounter;
    int keyDecodeIndexCounter;

    VigenereCipher() {keyString = ""; keyTemplate =  ""; currentkeyTemplate = ""; keyEncodeIndexCounter = 0; keyDecodeIndexCounter = 0;}

    String getKeyTemplate() {return keyTemplate;}
    String getCurrentKeyTemplate() {return currentkeyTemplate;}
    String getKeyString() {return keyString;}

    int getKeyEncodeIndexCounter() {return keyEncodeIndexCounter;}
    int getKeyDecodeIndexCounter() {return keyDecodeIndexCounter;}

    void setKeyString(String key) {keyString = key;}
    void setTemplate(String template) {keyTemplate = template;}
    void setCurrentKeyTemplate(String currTemplate) {currentkeyTemplate = currTemplate;}

    String updateKeyTemplate(String template) {return keyTemplate = template;}
    String updateKeyString(String key) {return keyString = key;}
    String updateCurrentKeyTemplate(String currTemplate) {return currentkeyTemplate = currTemplate;}

    String VigenereEncode(String key, String base) {
        //String elongatedKey = "ababa";
        //creates a string instance with allocated memory equal to that of an allocated char array of size base string's length
        //Uses the newly constructed String's replace() method with arguments oldCharacter and newCharacter, the former meaning characters up to the terminating null character, and the latter, the repeated string.
        // The new char array's characters are replaced from NULL to base (the ones you wish)


        //ABCDEFGHIJKLEM -> base
        //LEMONLEMONLEMO -> elongated_key

        String encodedText = "";
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

            if(characterValidator.isCapitalLetter(baseASCIIValue) && characterValidator.isCapitalLetter(keyASCIIValue)) {

                sumASCIIValues = characterValidator.CalculateVigenereEncodeUppercaseValue(baseASCIIValue, keyASCIIValue);

                if(sumASCIIValues > 'Z') {
                    encodedText += (char) (sumASCIIValues - 26);
                }
                else encodedText += (char) (sumASCIIValues);
            }
            else if((characterValidator.isLowercaseLetter(baseASCIIValue) && characterValidator.isLowercaseLetter(keyASCIIValue))) {

                sumASCIIValues = characterValidator.CalculateVigenereEncodeLowercaseValue(baseASCIIValue, keyASCIIValue);

                if(sumASCIIValues > 'z') {
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

        String decodedText = "";
        int sumASCIIValues;
        //Vigenere decode formula: (base + 'A') - key

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

            if(characterValidator.isCapitalLetter(baseASCIIValue) || characterValidator.isCapitalLetter(keyASCIIValue)) {

                sumASCIIValues = characterValidator.CalculateVigenereDecodeUppercaseValue(baseASCIIValue, keyASCIIValue);
                if(sumASCIIValues < 'A') {
                    decodedText += (char) ((sumASCIIValues + 25)); //
                }
                else decodedText += (char) (sumASCIIValues);
            }

            else if((characterValidator.isLowercaseLetter(baseASCIIValue) || characterValidator.isLowercaseLetter(keyASCIIValue))) {

                sumASCIIValues = characterValidator.CalculateVigenereDecodeLowercaseValue(baseASCIIValue, keyASCIIValue);
                if(sumASCIIValues < 'a') {
                    decodedText += (char) (sumASCIIValues + 25);
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

    private String EnlargeKey(String template, Editable s, int characterIndex, boolean mode) {

        char editTextCharacter = s.toString().charAt(characterIndex);

        System.out.print(keyString);

        if(/*!characterValidator.isInvalidCharacter((int) editTextCharacter) ||*/
                !characterValidator.isSpecialCharacter((int) editTextCharacter)) keyString += template.charAt(characterIndex);
            //separate invalid check and special char check due to different ending outputs
        else keyString += editTextCharacter;

        //need to add ability to shorten key if Editable is shortened (!) -> implemented

        if(keyString.length() > s.length()) {
            keyString = keyString.substring(0, Math.min(keyString.length(), s.length())); //crashes if there is no substring or key is smaller than editable
            if(mode) keyEncodeIndexCounter = 0;
            else keyDecodeIndexCounter = 0;
        }

        return keyString;
    }

    boolean isKeyChanged(String keyString, String previousKeyString) {
        return !previousKeyString.equals(keyString);
    }

    String updateEncodingKey(Editable s) {
        if(keyEncodeIndexCounter == keyTemplate.length()) keyEncodeIndexCounter = 0;
        return EnlargeKey(keyTemplate, s, keyEncodeIndexCounter++, true);
    }

    String updateDecodingKey(Editable s) {
        if(keyDecodeIndexCounter == keyTemplate.length()) keyDecodeIndexCounter = 0;
        return EnlargeKey(keyTemplate, s, keyDecodeIndexCounter++, false); //goes out of bounds when changed
    }
}

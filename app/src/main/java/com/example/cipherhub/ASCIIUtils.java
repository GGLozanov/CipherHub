package com.example.cipherhub;

class ASCIIUtils {

    boolean isSpecialCharacter(int currentCharValue) { //attempt to encapsulate method as one from CaesarActivity or use it from global import class containing it
        return currentCharValue == '.' || currentCharValue == '!' || currentCharValue == '?' ||
                currentCharValue == ',' || currentCharValue == ':' || currentCharValue == ';' ||
                currentCharValue == '-' || currentCharValue == ' ';
    }

    boolean isCapitalLetter(int currentCharValue) {
        return currentCharValue <= 'Z' && currentCharValue >= 'A';
    }

    boolean isLowercaseLetter(int currentCharValue) {
        return currentCharValue <= 'z' && currentCharValue >= 'a';
    }

    boolean isInvalidCharacter(int currentCharValue) {
        return (currentCharValue > 'Z' && currentCharValue < 'a') || (currentCharValue < ' ')  || currentCharValue > 'z';
    }

    boolean isCaesarEncodeSpecialCase(int currentCharValue, int key) {
        return currentCharValue <= key;
    }

    boolean isCaesarDecodeSpecialCase(int currentCharValue, int key) {
        return currentCharValue >= key;
    }

    int CalculateVigenereEncodeUppercaseValue(int baseASCIIValue, int keyASCIIValue) {
        return (baseASCIIValue + keyASCIIValue) - 'A';
    }

    int CalculateVigenereEncodeLowercaseValue(int baseASCIIValue, int keyASCIIValue) {
        return (baseASCIIValue + keyASCIIValue) - 'a';
    }

    int CalculateVigenereDecodeUppercaseValue(int baseASCIIValue, int keyASCIIValue) {
        return (baseASCIIValue + 'A') - keyASCIIValue;
    }

    int CalculateVigenereDecodeLowercaseValue(int baseASCIIValue, int keyASCIIValue) {
        return (baseASCIIValue + 'a') - keyASCIIValue;
    }


    /*int convertToCaesar(int base, int key) {
        int caesarAmount;
        //Caesar enoding: (base - (key + 'A')) = result
        //if result < 0 => 'Z' + result
        //Vigenere encoding: (base + key) - 'A'
        //if the answer to (base + key) - 'A' > key
        return caesarAmount;
    }*/
}

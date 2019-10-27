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

    boolean isCaesarEncodeSpecialCase(int currentCharValue) {
        return (currentCharValue >= 'A' && currentCharValue <= 'C') || (currentCharValue >= 'a' && currentCharValue <= 'c');
    }

    boolean isCaesarDecodeSpecialCase(int currentCharValue) {
        return (currentCharValue >= 'x' && currentCharValue <= 'z') || (currentCharValue >= 'X' && currentCharValue <= 'Z');
    }
}

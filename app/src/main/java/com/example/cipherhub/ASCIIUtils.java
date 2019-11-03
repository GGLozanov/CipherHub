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
        return currentCharValue < key;
    }

    boolean isCaesarDecodeSpecialCase(int currentCharValue, int key) {
        return currentCharValue > key;
    }

    boolean isNumber(int currentCharValue) {
        return currentCharValue >='0' && currentCharValue <= '9';
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

    int convertToLowercase(int currentCharValue) {
        return currentCharValue + ' ';
    }

    int convertToUppercase(int currentCharValue) {
        return currentCharValue - ' ';
    }

    int convertToASCIINumber(int currentCharValue) {
        return currentCharValue + '0';
    }

    int convertToNumber(int currentCharValue) {
        return currentCharValue - '0';
    }

    @SuppressWarnings("SameParameterValue")
    int findElementRow(final Character[][] array, final Character element) { //generics for char and int, for example
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                if(array[i][j].equals(element)) return i + 1;
            }
        }
        return 0;
    }

    @SuppressWarnings("SameParameterValue")
    int findElementColumn(final Character[][] array, final Character element) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                if(array[i][j].equals(element)) return j + 1;
            }
        }
        return 0;
    }
}

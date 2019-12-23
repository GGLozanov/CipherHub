package ciphers;

import android.util.Log;

public class VigenereCipher extends KeyCiphers {

    public VigenereCipher() {
        keyString = "";
        keyTemplate =  "";
        currentkeyTemplate = "";
        keyEncodeIndexCounter = 0;
        keyDecodeIndexCounter = 0;
    }

    public String VigenereEncode(String key, String base) {

        Log.d("Elongated key", key);
        encodedText = "";
        int sumASCIIValues;
        //Vigenere encode formula: (base + key) - 'A'

        if(key.length() == 0) return base;
        if(base.length() == 0) return key;

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

            if(characterValidator.isInvalidCharacter(baseASCIIValue) || characterValidator.isInvalidCharacter(keyASCIIValue) ||
                    characterValidator.isNumber(keyASCIIValue) || characterValidator.isNumber(baseASCIIValue)) continue;

            if(Character.isUpperCase(baseASCIIValue) && Character.isUpperCase(keyASCIIValue)) {

                sumASCIIValues = characterValidator.CalculateVigenereEncodeUppercaseValue(baseASCIIValue, keyASCIIValue);

                if(characterValidator.isCaesarDecodeSpecialCase(sumASCIIValues, 'Z')) { // vigenere always uses decode caesar (3 forward)
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
                if(characterValidator.isCapitalLetter(baseASCIIValue)) baseASCIIValue = characterValidator.convertToLowercase(baseASCIIValue);
                if(characterValidator.isCapitalLetter(keyASCIIValue)) keyASCIIValue = characterValidator.convertToLowercase(keyASCIIValue);
                sumASCIIValues = characterValidator.CalculateVigenereEncodeLowercaseValue(baseASCIIValue, keyASCIIValue); //lowercase for default
                encodedText += (char) (sumASCIIValues);
            }
        }

        isEncodeEvoked = true;
        // if(encodedText.equals("")) return base;
        return encodedText;
    }

    public String VigenereDecode(String key, String base) {
        // Vigenere doesn't actually decode, only encodes further
        // should implement decoding
        // One letter is omitted for some reason
        // Implement constant function for Caesar cipher that can work for Vigenere as well in ASCIIUtils

        decodedText = "";
        int sumASCIIValues;

        // Vigenere decode formula: (base + 'A') - key
        // use Character class and delete redundant methods from ASCIIUtils

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
            if(characterValidator.isInvalidCharacter(baseASCIIValue) || characterValidator.isInvalidCharacter(keyASCIIValue) ||
                    characterValidator.isNumber(keyASCIIValue) || characterValidator.isNumber(baseASCIIValue)) continue;

            if(Character.isUpperCase(baseASCIIValue) && Character.isUpperCase(keyASCIIValue)) {
                //make both key and base value capital
                sumASCIIValues = characterValidator.CalculateVigenereDecodeUppercaseValue(baseASCIIValue, keyASCIIValue);
                if(characterValidator.isCaesarEncodeSpecialCase(sumASCIIValues, 'A')) { // decoding for Vigenere is encoding for Caesar
                    decodedText += (char) (sumASCIIValues + 26); //
                }
                else decodedText += (char) (sumASCIIValues);
            }

            else if(Character.isLowerCase(baseASCIIValue) && Character.isLowerCase(keyASCIIValue)) {
                //make both key and base value lower
                sumASCIIValues = characterValidator.CalculateVigenereDecodeLowercaseValue(baseASCIIValue, keyASCIIValue);
                if(characterValidator.isCaesarEncodeSpecialCase(sumASCIIValues, 'a')) {
                    decodedText += (char) (sumASCIIValues + 26);
                }
                else decodedText += (char) (sumASCIIValues);
            }
            else {
                if(characterValidator.isCapitalLetter(baseASCIIValue)) baseASCIIValue = characterValidator.convertToLowercase(baseASCIIValue);
                if(characterValidator.isCapitalLetter(keyASCIIValue)) keyASCIIValue = characterValidator.convertToLowercase(keyASCIIValue);
                sumASCIIValues = characterValidator.CalculateVigenereDecodeLowercaseValue(baseASCIIValue, keyASCIIValue); // lowercase for default
                decodedText += (char) (sumASCIIValues);
            }
        }

        isDecodeEvoked = true;
        // if(decodedText.equals("")) return base;
        return decodedText;
    }
}

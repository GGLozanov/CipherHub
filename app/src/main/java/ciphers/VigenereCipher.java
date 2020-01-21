package ciphers;

import android.util.Log;

public class VigenereCipher extends KeyCipher {

    private boolean sourceMethod = true; // default source method (either decode or encode for key cipher) -> true is encode, false is decode

    private boolean isInputFirst = true; // do you input the input first or the key

    public boolean getIsInputFirst() {return isInputFirst;}

    public void setIsInputFirst(boolean isInputFirst) {this.isInputFirst = isInputFirst;}

    public boolean getSourceMethod() {return sourceMethod;}

    public void setSourceMethod(boolean sourceMethod) {this.sourceMethod = sourceMethod;}

    public VigenereCipher() {
        keyString = "";
        keyTemplate =  "";
        currentkeyTemplate = "";
        keyEncodeIndexCounter = 0;
        keyDecodeIndexCounter = 0;
    }

    private char getUppercaseEncodeVigenereResult(int sumASCIIValues) {
        return characterValidator.isCaesarDecodeSpecialCase(sumASCIIValues, 'Z') ? (char) (sumASCIIValues - 26) : (char) (sumASCIIValues);
    }

    private char getLowercaseEncodeVigenereResult(int sumASCIIValues) {
        return characterValidator.isCaesarDecodeSpecialCase(sumASCIIValues, 'z') ? (char) (sumASCIIValues - 26) : (char) (sumASCIIValues);
    }

    private char getUppercaseDecodeVigenereResult(int sumASCIIValues) {
        return characterValidator.isCaesarEncodeSpecialCase(sumASCIIValues, 'A') ? (char) (sumASCIIValues + 26) : (char) (sumASCIIValues);
    }

    private char getLowercaseDecodeVigenereResult(int sumASCIIValues) {
        return characterValidator.isCaesarEncodeSpecialCase(sumASCIIValues, 'a') ? (char) (sumASCIIValues + 26) : (char) (sumASCIIValues);
    }

    public String VigenereEncode(String key, String base) {

        encodedText = "";
        int sumASCIIValues;

        // Vigenere encode formula: (base + key) - 'A'

        if(key.length() == 0) return base;
        if(base.length() == 0) return key;

        boolean isBaseCapital, isKeyCapital;

        for(int baseCounter = 0, keyCounter = 0; baseCounter < key.length() && keyCounter < key.length();) {

            int baseASCIIValue = base.charAt(baseCounter);
            int keyASCIIValue = key.charAt(keyCounter);

            if(characterValidator.isSpecialCharacter(baseASCIIValue)) {
                encodedText += (char) (baseASCIIValue);
                baseCounter++;
                continue;
            }

            if(characterValidator.isSpecialCharacter(keyASCIIValue)) {
                encodedText += (char) (keyASCIIValue);
                keyCounter++;
                continue;
            }

            if(characterValidator.isInvalidCharacter(baseASCIIValue) || characterValidator.isInvalidCharacter(keyASCIIValue) ||
                    characterValidator.isNumber(keyASCIIValue) || characterValidator.isNumber(baseASCIIValue)) continue;

            isBaseCapital = Character.isUpperCase(baseASCIIValue);
            isKeyCapital = Character.isUpperCase(keyASCIIValue);

            if(isBaseCapital && isKeyCapital) {

                sumASCIIValues = characterValidator.CalculateVigenereEncodeUppercaseValue(baseASCIIValue, keyASCIIValue);

                encodedText += getUppercaseEncodeVigenereResult(sumASCIIValues);
            }
            else if(!isBaseCapital && !isKeyCapital) {

                sumASCIIValues = characterValidator.CalculateVigenereEncodeLowercaseValue(baseASCIIValue, keyASCIIValue);

                encodedText += getLowercaseEncodeVigenereResult(sumASCIIValues);
            }
            else {
                if(characterValidator.isCapitalLetter(baseASCIIValue)) baseASCIIValue = characterValidator.convertToLowercase(baseASCIIValue);
                if(characterValidator.isCapitalLetter(keyASCIIValue)) keyASCIIValue = characterValidator.convertToLowercase(keyASCIIValue);
                sumASCIIValues = characterValidator.CalculateVigenereEncodeLowercaseValue(baseASCIIValue, keyASCIIValue); // lowercase for default

                encodedText += getLowercaseEncodeVigenereResult(sumASCIIValues);
            }

            baseCounter++;
            keyCounter++;
        }

        isEncodeEvoked = true;
        if(encodedText.equals("")) return base;
        return encodedText;
    }

    public String VigenereDecode(String key, String base) {
        // Vigenere doesn't actually decode, only encodes further

        decodedText = "";
        int sumASCIIValues;

        // Vigenere decode formula: (base + 'A') - key
        // use Character class and delete redundant methods from ASCIIUtils -> to-do

        boolean isBaseCapital, isKeyCapital;

        for(int baseCounter = 0, keyCounter = 0; baseCounter < key.length() && keyCounter < key.length();) {

            int baseASCIIValue = base.charAt(baseCounter);
            int keyASCIIValue = key.charAt(keyCounter);

            if(characterValidator.isSpecialCharacter(baseASCIIValue)) {
                decodedText += (char) (baseASCIIValue);
                baseCounter++;
                continue;
            }
            if(characterValidator.isSpecialCharacter(keyASCIIValue)) {
                decodedText += (char) (keyASCIIValue);
                keyCounter++;
                continue;
            }
            if(characterValidator.isInvalidCharacter(baseASCIIValue) || characterValidator.isInvalidCharacter(keyASCIIValue) ||
                    characterValidator.isNumber(keyASCIIValue) || characterValidator.isNumber(baseASCIIValue)) continue;

            isBaseCapital = Character.isUpperCase(baseASCIIValue);
            isKeyCapital = Character.isUpperCase(keyASCIIValue);

            if(isBaseCapital && isKeyCapital) {
                // make both key and base value capital
                sumASCIIValues = characterValidator.CalculateVigenereDecodeUppercaseValue(baseASCIIValue, keyASCIIValue);

                decodedText += getUppercaseDecodeVigenereResult(sumASCIIValues);
            } else if(!isBaseCapital && !isKeyCapital) {
                // make both key and base value lower
                sumASCIIValues = characterValidator.CalculateVigenereDecodeLowercaseValue(baseASCIIValue, keyASCIIValue);

                decodedText += getLowercaseDecodeVigenereResult(sumASCIIValues);
            } else {
                if(characterValidator.isCapitalLetter(baseASCIIValue)) baseASCIIValue = characterValidator.convertToLowercase(baseASCIIValue);
                if(characterValidator.isCapitalLetter(keyASCIIValue)) keyASCIIValue = characterValidator.convertToLowercase(keyASCIIValue);
                sumASCIIValues = characterValidator.CalculateVigenereDecodeLowercaseValue(baseASCIIValue, keyASCIIValue); // lowercase for default

                decodedText += getLowercaseDecodeVigenereResult(sumASCIIValues);
            }

            baseCounter++;
            keyCounter++;
        }

        isDecodeEvoked = true;
        if(decodedText.equals("")) return base;
        return decodedText;
    }
}

package ciphers;

public class AtbashCipher extends KeyCiphers {

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
    public AtbashCipher(String key) {
        keyString = key;
        keyTemplate = keyString.toUpperCase();
        reverseUpperKeyString();
        reverseLowerKeyString();
    }

    public String AtbashEncode(String base) {
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

    public String AtbashDecode(String base) {
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

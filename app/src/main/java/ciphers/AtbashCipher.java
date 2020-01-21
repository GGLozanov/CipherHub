package ciphers;

public class AtbashCipher extends KeyCipher {

    private String reverseLowerKeyString = "";
    private String reverseUpperKeyString = "";

    // key and keyTemplate are both keys, just one is lowercase and the other is uppercase

    private void reverseLowerKeyString() {
        for(int counter = key.length() - 1; counter >= 0; counter--) { // equal to gives one more iteration (includes 'a')
            reverseLowerKeyString += key.charAt(counter);
        }
    }

    private void reverseUpperKeyString() {
        for(int counter = keyTemplate.length() - 1; counter >= 0; counter--) {
            reverseUpperKeyString += keyTemplate.charAt(counter);
        }
    }

    // default key for now is alphabet; will add support for more in the future -> added
    public AtbashCipher(String key) {
        this.key = key;
        keyTemplate = this.key.toUpperCase(); // keyTemplate acts as holder for uppercase key in this case
        reverseUpperKeyString();
        reverseLowerKeyString();
    }

    public String AtbashEncode(String base) {
        encodedText = "";

        for(int i = 0; i < base.length(); i++) {
            char baseCharacter = base.charAt(i);

            if(isSpecialCase(baseCharacter)) {
                if(key.indexOf(baseCharacter) == -1 && keyTemplate.indexOf(baseCharacter) == -1) {
                    encodedText += baseCharacter;
                }
                continue;
            }

            if(characterValidator.isLowercaseLetter(baseCharacter)) {
                encodedText += reverseLowerKeyString.charAt(key.indexOf(baseCharacter));
            }
            else if (characterValidator.isCapitalLetter(baseCharacter)) {
                encodedText += reverseUpperKeyString.charAt(keyTemplate.indexOf(baseCharacter));
            }
        }

        isEncodeEvoked = true;
        return encodedText;
    }

    public String AtbashDecode(String base) {
        decodedText = "";

        for(int i = 0; i < base.length(); i++) {
            char baseCharacter = base.charAt(i);

            if(isSpecialCase(baseCharacter)) {
                if(reverseLowerKeyString.indexOf(baseCharacter) == -1 && reverseUpperKeyString.indexOf(baseCharacter) == -1) {
                    decodedText += baseCharacter;
                }
                continue;
            }

            if(characterValidator.isLowercaseLetter(baseCharacter)) {
                decodedText += key.charAt(reverseLowerKeyString.indexOf(baseCharacter));
            }
            else if (characterValidator.isCapitalLetter(base.charAt(i))) {
                decodedText += keyTemplate.charAt(reverseUpperKeyString.indexOf(baseCharacter));
            }
        }

        isDecodeEvoked = true;
        return decodedText;
    }
}

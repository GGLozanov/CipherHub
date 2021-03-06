package ciphers;

public class CaesarCipher extends Cipher {

    private Integer key;

    public CaesarCipher(int key) {this.key = key;}

    public String CaesarDecode(String base) {
        decodedText = "";
        for(int i = 0; i < base.length(); i++) {

            int currentCharValue = (int) base.charAt(i);

            if(characterValidator.isSpecialCharacter(currentCharValue)) {
                decodedText += (char)currentCharValue;
                continue;
            }

            if(characterValidator.isInvalidCharacter((currentCharValue))) continue;

            int asciiValue = currentCharValue + key;

            if(characterValidator.isCaesarDecodeSpecialCase(currentCharValue, currentCharValue > 'Z' ? ('z' - key) : ('Z' - key))) asciiValue -= 26;

            decodedText += (char)asciiValue;
        }

        isDecodeEvoked = true;
        return decodedText;
    }

    public String CaesarEncode(String base) {
        encodedText = "";
        for(int i = 0; i < base.length(); i++) {

            int currentCharValue = (int) base.charAt(i);

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

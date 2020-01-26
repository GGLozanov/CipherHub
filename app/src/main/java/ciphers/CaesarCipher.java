package ciphers;

public class CaesarCipher extends Cipher {

    private Integer key;

    public CaesarCipher(int key) {this.key = key;}

    public String CaesarDecode(String base) {
        decodedText = "";

        for(int i = 0; i < base.length(); i++) {

            char baseCharacter = base.charAt(i);

            if(isSpecialCase(baseCharacter)) {
                continue;
            }

            int asciiValue = (int) baseCharacter + key;

            if(characterValidator.isCaesarDecodeSpecialCase(baseCharacter, baseCharacter > 'Z' ? ('z' - key) : ('Z' - key))) asciiValue -= 26;

            decodedText += (char)asciiValue;
        }

        isDecodeEvoked = true;
        return decodedText;
    }

    public String CaesarEncode(String base) {
        encodedText = "";

        for(int i = 0; i < base.length(); i++) {

            char baseCharacter = base.charAt(i);

            if(isSpecialCase(baseCharacter)) {
                continue;
            }

            int asciiValue = (int) baseCharacter - key;

            if(characterValidator.isCaesarEncodeSpecialCase(baseCharacter, characterValidator.isLowercaseLetter(baseCharacter) ? (key + 'a') : (key + 'A'))) asciiValue += 26;

            encodedText += (char)asciiValue;
        }

        isEncodeEvoked = true;
        return encodedText;
    }
}

package ciphers;

import android.text.Editable;
import android.util.Log;

public class CaesarCipher extends Ciphers {

    private Integer key;

    public CaesarCipher(int key) {this.key = key;}

    public String CaesarDecoder(Editable base) {
        decodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++) {

            int currentCharValue = (int) defaultInput.charAt(i);

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

    public String CaesarEncoder(Editable base) {
        encodedText = "";
        String defaultInput = base.toString();
        for(int i = 0; i < defaultInput.length(); i++) {

            int currentCharValue = (int) defaultInput.charAt(i);

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

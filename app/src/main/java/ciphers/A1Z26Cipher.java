package ciphers;

import android.os.Debug;
import android.util.Log;

public class A1Z26Cipher extends KeyCipher {
    String delim = "-";

    public A1Z26Cipher(String key, String delim) {
        this.key = key;
        this.delim = delim;
    }

    public String A1Z26Encode(String base) {
        encodedText = "";
        char baseCharacter;
        int keyPosition;

        int len = base.length();
        for(int i = 0; i < len; i++) {
            baseCharacter = base.charAt(i);
            keyPosition = key.indexOf(Character.toLowerCase(baseCharacter));

            System.out.println(baseCharacter);
            if (isSpecialCase(baseCharacter)) {
                encodedText = encodedText.substring(0, encodedText.length() - 1);
                System.out.println("CONTINUE");
                continue;
            }

            System.out.println("APPEND");
            encodedText += Integer.toString(++keyPosition); // position in the alphabet (+1 character)
            if (i != len - 1) {
                if (!isSpecialCase(base.charAt(i + 1))) {
                    System.out.println("DELIM");
                    encodedText += delim; // custom delim
                }
            }
        }

        isEncodeEvoked = true;
        return encodedText;
    }

    public String A1Z26Decode(String base) {
        decodedText = "";

        String[] parsedBase = base.split(delim); // add option for delim -> done

        for(String pair : parsedBase) {
            handleDecodingPair(pair);
        }

        isDecodeEvoked = true;
        return decodedText;
    }

    private void handleDecodingPair(String pair) {
        int keyPosition;
        char keyCharacter;

        keyCharacter = isSpecialCase(pair);
        if(keyCharacter != '\0') {
            // if(!characterValidator.isDeliminatingCharacter(keyCharacter)) {
                String[] internalPairs = pair.split(Character.toString(keyCharacter));
                for (int i = 0; i < internalPairs.length; i++) {
                    handleDecodingPair(internalPairs[i]); // recursive call for translating pairs within pairs
                    if (i != internalPairs.length - 1) decodedText += keyCharacter;
                }
                return;
            // } else decodedText += keyCharacter;
        }

        if(pair.matches("^[0-9]*$")) {
            keyPosition = Integer.parseInt(pair);
            if (keyPosition > key.length()) return;
            keyCharacter = key.charAt(keyPosition - 1);

            decodedText += keyCharacter; // position in the alphabet (+1 character)
        }
    }
}

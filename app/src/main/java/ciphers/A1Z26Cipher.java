package ciphers;

public class A1Z26Cipher extends KeyCipher {
    public A1Z26Cipher(String key) {
        this.key = key;
    }

    public String A1Z26Encode(String base) {

        int len = base.length();
        for(int i = 0; i < len; i++) {
            char baseCharacter = base.charAt(i);
            int keyPosition = key.indexOf(Character.toLowerCase(baseCharacter));

            if (isSpecialCase(baseCharacter)) {
                if(keyPosition == -1) {
                    encodedText += baseCharacter;
                }

                continue;
            }

            encodedText += Integer.toString(++keyPosition); // position in the alphabet (+1 character)
            if(i != len - 1) encodedText += '-'; // add option whether to hyphenate
        }

        return encodedText;
    }

    public String A1Z26Decode(String base) {

        String[] parsedBase = base.split("-");

        for(String pair : parsedBase) {
            if(isSpecialCase(pair)) {
                continue;
            }

            int keyPosition = Integer.parseInt(pair);
            char keyCharacter = key.charAt(keyPosition - 1);

            encodedText += keyCharacter; // position in the alphabet (+1 character)
        }

        return  decodedText;
    }
}

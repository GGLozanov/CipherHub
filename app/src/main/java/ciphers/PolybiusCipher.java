package ciphers;

import android.util.Log;

public class PolybiusCipher extends Ciphers {

    private static final Character[][] PolybiusSquare = {
            {'A', 'B', 'C', 'D', 'E', 'F'},
            {'G', 'H', 'I', 'J', 'K', 'L'},
            {'M', 'N', 'O', 'P', 'Q', 'R'},
            {'S', 'T', 'U', 'V', 'W', 'X'},
            {'Y', 'Z', '0', '1', '2', '3'},
            {'4', '5', '6', '7', '8', '9'}
    };

    public String PolybiusEncode(String base) {
        encodedText = "";

        //TO-DO: Put these variables in superclass Ciphers
        //add functionality for user to choose lowercase or uppercase

        for(int i = 0; i < base.length(); i++) {

            currentCharacter = base.charAt(i);

            if(characterValidator.isInvalidCharacter(currentCharacter)) continue;
            if(characterValidator.isSpecialCharacter(currentCharacter)) encodedText += (char)currentCharacter;
            else {
                if(characterValidator.isLowercaseLetter(currentCharacter)) currentCharacter = characterValidator.convertToUppercase(currentCharacter);
                encodedText += (char) characterValidator.convertToASCIINumber(characterValidator.findElementRow(PolybiusSquare, (char)currentCharacter));
                encodedText += (char) characterValidator.convertToASCIINumber(characterValidator.findElementColumn(PolybiusSquare, (char)currentCharacter));
            }
        }

        return encodedText;
    }

    public String PolybiusDecode(String base, String inputText) {
        decodedText = "";
        Log.d("Input; ", inputText);
        //evaluate exception in activity file -> if(base.length() % 2 != 0) -> done

        for(int i = 0, counter = 0; i + 1 < base.length(); i += 2, counter++) {
            currentCharacter = base.charAt(i);
            //fix seg fault when trying to decrement i for ignoring space

            if(characterValidator.isSpecialCharacter(currentCharacter)) {
                decodedText += (char) currentCharacter;
                i--;
                continue;
            }

            int nextCharacter = base.charAt(i + 1);

            if(characterValidator.isSpecialCharacter(nextCharacter)) {
                decodedText += (char) nextCharacter;
                continue;
            }

            if(!characterValidator.isNumber(currentCharacter) || !characterValidator.isNumber(nextCharacter) ||
                    characterValidator.isInvalidCharacter(currentCharacter) || characterValidator.isInvalidCharacter(nextCharacter)) continue;


            int x = characterValidator.convertToNumber(currentCharacter), y = characterValidator.convertToNumber(nextCharacter);
            if(x > 6 || y > 6) continue;
            //Fix uppercase and lowercase bug
            if(characterValidator.isCapitalLetter(inputText.charAt(counter)) ||
                    characterValidator.isNumber(PolybiusSquare[x - 1][y - 1])) decodedText += PolybiusSquare[x - 1][y - 1];
            else decodedText += (char)characterValidator.convertToLowercase(PolybiusSquare[x - 1][y - 1]);
        }

        return decodedText;
    }

}

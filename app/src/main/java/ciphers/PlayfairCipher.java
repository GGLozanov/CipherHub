package ciphers;

public class PlayfairCipher extends KeyCipher {

    String digram = "xx"; // current digram (xx by default)

    // append x if odd number of characters
    // omitted letters are duplicate letters in key (O(n^2) algo?) -> use Set probably

    public PlayfairCipher(String key) {
        super(key); // remember the damn super()... this isn't C++
    }

    public void handleKey() {

    }

    public boolean isDigramRow() {

        return false;
    }

    public boolean isDigramRectangle() {

        return false;
    }

    public boolean isDigramColumn() {

        return false;
    }


    public String PlayfairEncode(String base) {
        encodedText = "";


        return encodedText;
    }

    public String PlayfairDecode(String base) {
        decodedText = "";


        return decodedText;
    }
}

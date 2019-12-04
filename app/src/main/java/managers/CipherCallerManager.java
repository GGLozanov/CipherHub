package managers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import ciphers.AtbashCipher;
import ciphers.CaesarCipher;
import ciphers.PolybiusCipher;

public class CipherCallerManager {

    EditText decodedInput;
    EditText encodedOutput;

    CaesarCipher caesarCipher;
    PolybiusCipher polybiusCipher;
    AtbashCipher atbashCipher;

    public void setDecodedInput(EditText decodedInput) {this.decodedInput = decodedInput;}
    public void setEncodedOutput(EditText encodedOutput) {this.encodedOutput = encodedOutput;}


    public void CaesarCipher() {
        caesarCipher = new CaesarCipher(3);

        TextWatcher normaltoCaesarListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!caesarCipher.getEncodeState()) {
                    caesarCipher.setDecodeEvoked(true);
                    encodedOutput.setText(caesarCipher.CaesarEncoder(s));
                }
                caesarCipher.setEncodeEvoked(false);
            }
        };

        TextWatcher CaesartoNormalListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //normalInput.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!caesarCipher.getDecodeState()) {
                    caesarCipher.setEncodeEvoked(true);
                    decodedInput.setText(caesarCipher.CaesarDecoder(s));
                    //isDecodeEvoked = false;
                }
                caesarCipher.setDecodeEvoked(false);
            }
        };

        caesarCipher.setIOTexts(decodedInput, encodedOutput);

        decodedInput = caesarCipher.getInputText();
        encodedOutput = caesarCipher.getOutputText();

        decodedInput.addTextChangedListener(normaltoCaesarListener);
        encodedOutput.addTextChangedListener(CaesartoNormalListener);

    }

    public void AtbashCipher() {
        atbashCipher = new AtbashCipher("abcdefghijklmnopqrstuvwxyz");

        TextWatcher InputtoAtbash = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!atbashCipher.getEncodeState()) {
                    atbashCipher.setDecodeEvoked(true);
                    encodedOutput.setText(atbashCipher.AtbashEncode(s.toString()));
                }
                atbashCipher.setEncodeEvoked(false);
            }
        };

        TextWatcher AtbashtoInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!atbashCipher.getDecodeState()) {
                    atbashCipher.setEncodeEvoked(true);
                    decodedInput.setText(atbashCipher.AtbashDecode(s.toString()));
                }
                atbashCipher.setDecodeEvoked(false);
            }
        };


        atbashCipher.setIOTexts(decodedInput, encodedOutput);

        decodedInput = atbashCipher.getInputText();
        encodedOutput = atbashCipher.getOutputText();

        decodedInput.addTextChangedListener(InputtoAtbash);
        encodedOutput.addTextChangedListener(AtbashtoInput);
    }

    public void PolybiusCipher() {
        polybiusCipher = new PolybiusCipher();

        final TextWatcher InputToPolybius = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!polybiusCipher.getEncodeState()) {
                    polybiusCipher.setDecodeEvoked(true);
                    encodedOutput.setText(polybiusCipher.PolybiusEncode(s.toString()));
                }
                polybiusCipher.setDecodeEvoked(false);
            }
        };


        final TextWatcher PolybiusToInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!polybiusCipher.getDecodeState()) {
                    polybiusCipher.setEncodeEvoked(true);
                    if(polybiusCipher.getCharacterValidator().pureCharacterLength(s.toString()) % 2 == 0) {
                        decodedInput.setText(polybiusCipher.PolybiusDecode(s.toString(), decodedInput.toString()));
                    }
                }

                polybiusCipher.setEncodeEvoked(false);
            }
        };

        polybiusCipher.setIOTexts(decodedInput, encodedOutput);

        decodedInput = polybiusCipher.getInputText();
        encodedOutput = polybiusCipher.getOutputText();

        decodedInput.addTextChangedListener(InputToPolybius);
        encodedOutput.addTextChangedListener(PolybiusToInput);
    }

}

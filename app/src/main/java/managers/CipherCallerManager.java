package managers;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import ciphers.AtbashCipher;
import ciphers.CaesarCipher;
import ciphers.PolybiusCipher;
import ui.CustomCaesarDialogFragment;
import ui.CustomCaesarFragment;

public class CipherCallerManager {

    EditText decodedInput;
    EditText encodedOutput;

    static CaesarCipher caesarCipher;
    PolybiusCipher polybiusCipher;
    AtbashCipher atbashCipher;

    public void setDecodedInput(EditText decodedInput) {this.decodedInput = decodedInput;}
    public void setEncodedOutput(EditText encodedOutput) {this.encodedOutput = encodedOutput;}

    public static void instantiateCaesarCipher() {
        caesarCipher = new CaesarCipher(Integer.parseInt(CustomCaesarFragment.getCaesarKey()));
    } //static method for reinstantiating the caesarcipher after input has been received CustomCaesarFragment (or class is instantiated by fragment)

    public void CaesarCipher() {

        instantiateCaesarCipher();

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
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!caesarCipher.getDecodeState()) {
                    caesarCipher.setEncodeEvoked(true);
                    decodedInput.setText(caesarCipher.CaesarDecoder(s));
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

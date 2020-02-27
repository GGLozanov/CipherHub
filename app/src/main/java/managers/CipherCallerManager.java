package managers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import ciphers.A1Z26Cipher;
import ciphers.AtbashCipher;
import ciphers.CaesarCipher;
import ciphers.PolybiusCipher;
import ui.ui_custom.ui_single_key_ciphers.CustomA1Z26Fragment;
import ui.ui_custom.ui_single_key_ciphers.CustomAtbashFragment;
import ui.ui_custom.ui_single_key_ciphers.CustomCaesarFragment;
import ui.ui_custom.ui_table_key_ciphers.CustomPolybiusFragment;

public class CipherCallerManager {

    EditText decodedInput;
    EditText encodedOutput;

    static CaesarCipher caesarCipher;
    static PolybiusCipher polybiusCipher;
    static AtbashCipher atbashCipher;
    static A1Z26Cipher a1Z26Cipher;

    public void setDecodedInput(EditText decodedInput) {this.decodedInput = decodedInput;}
    public void setEncodedOutput(EditText encodedOutput) {this.encodedOutput = encodedOutput;}

    public static void instantiateCaesarCipher() {
        caesarCipher = new CaesarCipher(Integer.parseInt(CustomCaesarFragment.getCaesarKey()));
    } // static method for reinstantiating the caesarcipher after input has been received CustomCipherFragment (or class is instantiated by fragment)

    public static void instantiateAtbashCipher() {
        atbashCipher = new AtbashCipher(CustomAtbashFragment.getAtbashKey());
    }
    public static void instantiatePolybiusCipher() {
        polybiusCipher = new PolybiusCipher(CustomPolybiusFragment.getPolybiusKey());
    }

    public static void instantiateA1Z26Cipher() {
        a1Z26Cipher = new A1Z26Cipher(CustomA1Z26Fragment.getA1Z26Key(), CustomA1Z26Fragment.getDelim());
    }

    public void CaesarCipher() {

        instantiateCaesarCipher();

        TextWatcher InputToCaesarListener = new TextWatcher() {
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
                    encodedOutput.setText(caesarCipher.CaesarEncode(s.toString()));
                }
                caesarCipher.setEncodeEvoked(false);
            }
        };

        TextWatcher CaesarToInputListener = new TextWatcher() {
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
                    decodedInput.setText(caesarCipher.CaesarDecode(s.toString()));
                }
                caesarCipher.setDecodeEvoked(false);
            }
        };

        caesarCipher.setIOTexts(decodedInput, encodedOutput);

        decodedInput = caesarCipher.getInputText();
        encodedOutput = caesarCipher.getOutputText();

        decodedInput.addTextChangedListener(InputToCaesarListener);
        encodedOutput.addTextChangedListener(CaesarToInputListener);

    }

    public void AtbashCipher() {

        instantiateAtbashCipher();

        TextWatcher InputToAtbash = new TextWatcher() {
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

        TextWatcher AtbashToInput = new TextWatcher() {
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

        decodedInput.addTextChangedListener(InputToAtbash);
        encodedOutput.addTextChangedListener(AtbashToInput);
    }

    public void PolybiusCipher() {

        instantiatePolybiusCipher();

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

    public void A1Z26Cipher() {

        instantiateA1Z26Cipher();

        final TextWatcher InputToA1Z26 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!a1Z26Cipher.getEncodeState()) {
                    a1Z26Cipher.setDecodeEvoked(true);
                    encodedOutput.setText(a1Z26Cipher.A1Z26Encode(s.toString()));
                }

                a1Z26Cipher.setDecodeEvoked(false);
            }
        };


        final TextWatcher A1Z26toInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!a1Z26Cipher.getDecodeState()) {
                    a1Z26Cipher.setEncodeEvoked(true);
                    decodedInput.setText(a1Z26Cipher.A1Z26Decode(s.toString()));
                }

                a1Z26Cipher.setEncodeEvoked(false);
            }
        };

        a1Z26Cipher.setIOTexts(decodedInput, encodedOutput);

        decodedInput = a1Z26Cipher.getInputText();
        encodedOutput = a1Z26Cipher.getOutputText();

        decodedInput.addTextChangedListener(InputToA1Z26);
        encodedOutput.addTextChangedListener(A1Z26toInput);
    }

}

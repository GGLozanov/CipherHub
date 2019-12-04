package managers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import ciphers.VigenereCipher;
import managers.CipherCallerManager;

public class KeyCipherCallerManager extends CipherCallerManager {

    EditText keyText; /*key properties*/
    String keyTemplate;
    String currentkeyTemplate;
    String keyString;
    boolean isEncodeEvoked;
    boolean isDecodeEvoked;

    VigenereCipher vigenereCipher;


    public void setKeyText(EditText keyText) {this.keyText = keyText;}

    private void updateVariables() {
        keyTemplate = vigenereCipher.getKeyTemplate();
        currentkeyTemplate = vigenereCipher.getCurrentKeyTemplate();
        keyString = vigenereCipher.getKeyString();
        isEncodeEvoked = vigenereCipher.getEncodeState();
        isDecodeEvoked = vigenereCipher.getDecodeState();
    }

    public void VigenereCipher() {

        vigenereCipher = new VigenereCipher();

        final TextWatcher InputToVigenereListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateVariables();

                if(keyTemplate.length() > s.length() || keyTemplate.isEmpty()) {
                    vigenereCipher.setKeyString(keyTemplate);
                    vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                    return; //< or >?
                }

                String inputString = s.toString();
                if(!isEncodeEvoked) {

                    if(vigenereCipher.isKeyChanged(keyTemplate, currentkeyTemplate)) { //replace key if key is changed
                        //should use compareEquals
                        //need to keep track of templates
                        //crashes
                        vigenereCipher.setKeyString(keyString.replaceAll(currentkeyTemplate, keyTemplate)); //crashes if key is entirely changed
                        if(vigenereCipher.keyExceedsMessage(inputString)) vigenereCipher.trimKeyString(inputString); //trims the key if the new one is too long
                        vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                        vigenereCipher.resetEncodeIndexCounter();
                    }
                    else vigenereCipher.updateEncodingKey(inputString);
                    updateVariables();
                    vigenereCipher.setDecodeEvoked(true);
                    encodedOutput.setText(vigenereCipher.VigenereEncode(keyString, inputString));
                }

                vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                vigenereCipher.setEncodeEvoked(false); //might change

            }
        };

        final TextWatcher VigenereToInputListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) { //ALWAYS USE THE VARIABLE "s" AND NOT YOUR BULLSHIT EDITTEXTs

                updateVariables();

                if(keyTemplate.length() > s.length() || keyTemplate.isEmpty()) {
                    vigenereCipher.setKeyString(keyTemplate);
                    vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                    //newKeyView.setText(currentkeyTemplate);
                    //keyView.setText(keyTemplate);
                    return;
                }

                //PHANTOM LETTERS WHEN DELETING PARTS OF KEY AND WHEN WRITING

                String outputString = s.toString();

                if(!isDecodeEvoked) {

                    if(vigenereCipher.isKeyChanged(keyTemplate, currentkeyTemplate)) { //reset everything if key has changed
                        //should use compareEquals
                        //need to keep track of template
                        vigenereCipher.setKeyString(keyString.replaceAll(currentkeyTemplate, keyTemplate)); //crashes if key is changed entirely
                        if(vigenereCipher.keyExceedsMessage(outputString)) vigenereCipher.trimKeyString(outputString);
                        vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                        vigenereCipher.resetDecodeIndexCounter();
                    }
                    else vigenereCipher.updateDecodingKey(outputString);
                    updateVariables();
                    vigenereCipher.setEncodeEvoked(true);
                    decodedInput.setText(vigenereCipher.VigenereDecode(keyString, outputString));
                }

                vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                vigenereCipher.setDecodeEvoked(false);
            }
        };

        TextWatcher KeyListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vigenereCipher.setTemplate(s.toString());
                keyTemplate = vigenereCipher.getKeyTemplate();
                //need to add functionality if user decided to actually replace key after it's been converted
                //crashes currently at that
                Editable inputTextEditable = decodedInput.getText();
                Editable vigenereTextEditable = encodedOutput.getText();
                if(keyTemplate.length() <= decodedInput.length()) InputToVigenereListener.afterTextChanged(inputTextEditable);
                else if(keyTemplate.length() <= encodedOutput.length()) VigenereToInputListener.afterTextChanged(vigenereTextEditable);
            }
        };

        vigenereCipher.setKeyText(keyText);
        vigenereCipher.setIOTexts(decodedInput, encodedOutput);

        decodedInput = vigenereCipher.getInputText();
        keyText = vigenereCipher.getKeyText();
        encodedOutput = vigenereCipher.getOutputText();

        decodedInput.addTextChangedListener(InputToVigenereListener); //adds the listener TO the object (not the other way around)
        encodedOutput.addTextChangedListener(VigenereToInputListener);
        keyText.addTextChangedListener(KeyListener);
    }

}

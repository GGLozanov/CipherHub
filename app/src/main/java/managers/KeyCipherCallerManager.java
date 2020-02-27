package managers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import ciphers.PlayfairCipher;
import ciphers.VigenereCipher;
import ui.ui_custom.ui_table_key_ciphers.AdditionalVigenereFragment;

public class KeyCipherCallerManager extends CipherCallerManager {

    // Vigenere key properties
    static EditText keyText;
    String keyTemplate; // template to replicate key by
    String currentkeyTemplate; // current template by which to compare newer ones
    String keyString; // Full key (completely parsed)
    String extraKey;
    boolean isEncodeEvoked;
    boolean isDecodeEvoked;

    private static VigenereCipher vigenereCipher;
    private static PlayfairCipher playfairCipher;

    public static VigenereCipher getVigenereCipher() {return vigenereCipher;}
    public static PlayfairCipher getPlayfairCipher() {return playfairCipher;}

    public static EditText getKeyText() {return keyText;}
    public void setKeyText(EditText keyText) {KeyCipherCallerManager.keyText = keyText;}

    private void updateVariables() {
        keyTemplate = vigenereCipher.getKeyTemplate();
        currentkeyTemplate = vigenereCipher.getCurrentKeyTemplate();
        keyString = vigenereCipher.getKey();
        isEncodeEvoked = vigenereCipher.getEncodeState();
        isDecodeEvoked = vigenereCipher.getDecodeState();

        extraKey = AdditionalVigenereFragment.getExtraKey(); // get parsed long key
    }

    public void handleVigenereEncodingKey(String keyString, String keyTemplate, String currentkeyTemplate, String inputString) {
        if(vigenereCipher.isKeyChanged(keyTemplate, currentkeyTemplate) && inputString.length() < currentkeyTemplate.length()) { // replace key if key is changed

            vigenereCipher.setKey(keyString.replaceAll(currentkeyTemplate, keyTemplate)); // crashes if key is entirely changed
            if(vigenereCipher.keyExceedsMessage(inputString)) vigenereCipher.trimKeyString(inputString); // trims the key if the new one is too long

            vigenereCipher.setCurrentKeyTemplate(keyTemplate);
            vigenereCipher.resetEncodeIndexCounter();
        } else if(inputString.length() > currentkeyTemplate.length() && !vigenereCipher.getIsInputFirst()) {
            vigenereCipher.updateEncodingKeyByBase(inputString);
        } else vigenereCipher.updateEncodingKey(inputString);
    }

    public void handleVigenereDecodingKey(String keyString, String keyTemplate, String currentkeyTemplate, String outputString) {
        if(vigenereCipher.isKeyChanged(keyTemplate, currentkeyTemplate) && outputString.length() < currentkeyTemplate.length()) { // reset everything if key has changed

            vigenereCipher.setKey(keyString.replaceAll(currentkeyTemplate, keyTemplate)); // crashes if key is changed entirely
            if(vigenereCipher.keyExceedsMessage(outputString)) vigenereCipher.trimKeyString(outputString);

            vigenereCipher.setCurrentKeyTemplate(keyTemplate);
            vigenereCipher.resetDecodeIndexCounter();
        } else if(outputString.length() > currentkeyTemplate.length() && !vigenereCipher.getIsInputFirst()) { // second case where we have text first and key second inputted
            vigenereCipher.updateDecodingKeyByBase(outputString);
        } else vigenereCipher.updateDecodingKey(outputString);
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
                // call vigenere twice here to simulate extra key
                updateVariables();
                // Vigenere encode here for both keys to produce third key (do vigenere on both elongated keys)

                if(keyTemplate.length() > s.length() || keyTemplate.isEmpty()) {
                    vigenereCipher.setKey(keyTemplate);
                    vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                    vigenereCipher.setSourceMethod(false);
                    vigenereCipher.setIsInputFirst(true);
                    return;
                }

                String inputString = s.toString();
                if(!isEncodeEvoked) {
                    handleVigenereEncodingKey(keyString, keyTemplate, currentkeyTemplate , inputString);

                    // Handle key here and directly use elongated extra key
                    // When you call vigenere here, the key is the vigenere of both (second is the key and the first is base)
                    // then you set the key as the keyString of the class (or variable) and pass it to VigenereEncode

                    updateVariables();
                    vigenereCipher.setDecodeEvoked(true);
                    encodedOutput.setText(vigenereCipher.VigenereEncode(keyString, inputString));

                }

                vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                vigenereCipher.setEncodeEvoked(false); // might change
                vigenereCipher.setSourceMethod(false);
                vigenereCipher.setIsInputFirst(true);
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
            public void afterTextChanged(Editable s) { // ALWAYS use the variable "s" and not your own EditTexts

                updateVariables();

                if(keyTemplate.length() > s.length() || keyTemplate.isEmpty()) {
                    vigenereCipher.setKey(keyTemplate);
                    vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                    vigenereCipher.setSourceMethod(true);
                    vigenereCipher.setIsInputFirst(true);
                    return;
                }

                String outputString = s.toString();

                if(!isDecodeEvoked) {

                    handleVigenereDecodingKey(keyString, keyTemplate, currentkeyTemplate, outputString);

                    updateVariables();
                    vigenereCipher.setEncodeEvoked(true);
                    decodedInput.setText(vigenereCipher.VigenereDecode(keyString, outputString));

                }

                vigenereCipher.setCurrentKeyTemplate(keyTemplate);
                vigenereCipher.setDecodeEvoked(false);
                vigenereCipher.setIsInputFirst(true);
                vigenereCipher.setSourceMethod(true);

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
                vigenereCipher.setKey(s.toString());
                keyTemplate = vigenereCipher.getKeyTemplate();
                keyString = vigenereCipher.getKey();

                Editable inputTextEditable = decodedInput.getText();
                Editable vigenereTextEditable = encodedOutput.getText();

                vigenereCipher.setIsInputFirst(false);

                if(!vigenereCipher.getSourceMethod()) InputToVigenereListener.afterTextChanged(inputTextEditable);
                else VigenereToInputListener.afterTextChanged(vigenereTextEditable);
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

    public void PlayfairCipher() {

    }

}

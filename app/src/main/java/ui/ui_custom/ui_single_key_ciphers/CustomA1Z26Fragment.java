package ui.ui_custom.ui_single_key_ciphers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cipherhub.R;

import managers.CipherCallerManager;

public class CustomA1Z26Fragment extends CustomCipherFragment implements CustomCipherDialogFragment.OnInputSelected {

    @Override
    public void sendInput(String input) {
        A1Z26Key = input;
        CipherCallerManager.instantiateA1Z26Cipher();
    }

    @Override
    public void sendInput(String input, String delim) {
        A1Z26Key = input;
        CustomA1Z26Fragment.delim = delim;
        CipherCallerManager.instantiateA1Z26Cipher();
    }

    static private String A1Z26Key = "abcdefghijklmnopqrstuvwxyz";
    static private String delim = "-";

    static public String getA1Z26Key() {return A1Z26Key;}
    static public String getDelim() {return delim;}

    public CustomA1Z26Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);

        dialogButton.setOnClickListener((View v) -> {
                CustomA1Z26DialogFragment customA1Z26DialogFragment = new CustomA1Z26DialogFragment(
                        resources.getString(R.string.custom_a1z26_dialog_message),
                        resources.getString(R.string.custom_dialog_hint),
                        resources.getString(R.string.custom_dialog_positive_button_message),
                        resources.getString(R.string.custom_dialog_negative_button_message));

                customA1Z26DialogFragment.setTargetFragment(CustomA1Z26Fragment.this, 7);
                customA1Z26DialogFragment.show(getFragmentManager(), "Custom A1Z26 Dialog");
            }
        );

        resetButton.setOnClickListener((View v) -> {
            sendInput("abcdefghijklmnopqrstuvwxyz", "-");
            Toast.makeText(getActivity().getApplicationContext(), "A1Z26 Cipher reset to default alphabetic configuration!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}

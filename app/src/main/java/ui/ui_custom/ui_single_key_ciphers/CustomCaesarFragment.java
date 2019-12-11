package ui.ui_custom.ui_single_key_ciphers;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cipherhub.R;

import managers.CipherCallerManager;

public class CustomCaesarFragment extends CustomCipherFragment implements CustomCipherDialogFragment.OnInputSelected {

    public CustomCaesarFragment() {
    }

    static private String caesarKey = "3";

    static public String getCaesarKey() {return caesarKey;}

    @Override
    public void sendInput(String input) {
        caesarKey = input;
        CipherCallerManager.instantiateCaesarCipher();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState); //instantiate parent view to use here

        final Resources resources = getResources();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomCaesarDialogFragment customCaesarDialogFragment = new CustomCaesarDialogFragment(resources.getString(R.string.custom_caesar_dialog_message),
                        resources.getString(R.string.custom_dialog_hint), resources.getString(R.string.custom_dialog_positive_button_message),
                        resources.getString(R.string.custom_dialog_negative_button_message));
                customCaesarDialogFragment.setTargetFragment(CustomCaesarFragment.this, 1); //request code for accessing the target fragment outside
                customCaesarDialogFragment.show(getFragmentManager(), "Custom Caesar Dialog");
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInput("3"); //send the input with the constant default value
                Toast.makeText(getActivity().getApplicationContext(), "Caesar Cipher successfully reset!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}

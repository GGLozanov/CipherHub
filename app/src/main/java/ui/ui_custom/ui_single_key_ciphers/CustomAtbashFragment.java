package ui.ui_custom.ui_single_key_ciphers;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import managers.CipherCallerManager;

public class CustomAtbashFragment extends CustomCipherFragment implements CustomCipherDialogFragment.OnInputSelected {

    @Override
    public void sendInput(String input) {
        atbashKey = input;
        CipherCallerManager.instantiateAtbashCipher();
    }

    @Override
    public void sendInput(String input, String delim) {

    }

    public CustomAtbashFragment() {

    }

    private static String atbashKey = "abcdefghijklmnopqrstuvwxyz";

    public static String getAtbashKey() {return atbashKey;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        dialogButton.setOnClickListener((View v) -> {
            CustomCipherDialogFragment customCipherDialogFragment = new CustomAtbashDialogFragment(
                    resources.getString(R.string.custom_atbash_dialog_message),
                resources.getString(R.string.custom_dialog_hint), resources.getString(R.string.custom_dialog_positive_button_message),
                resources.getString(R.string.custom_dialog_negative_button_message));

            customCipherDialogFragment.setTargetFragment(CustomAtbashFragment.this, 2);
            customCipherDialogFragment.show(getFragmentManager(), "Custom Atbash Dialog");});

        resetButton.setOnClickListener((View v) -> {
            sendInput("abcdefghijklmnopqrstuvwxyz");
            Toast.makeText(getActivity().getApplicationContext(), "Atbash cipher successfully reset!", Toast.LENGTH_LONG).show();
        });

        return view;
    }

}

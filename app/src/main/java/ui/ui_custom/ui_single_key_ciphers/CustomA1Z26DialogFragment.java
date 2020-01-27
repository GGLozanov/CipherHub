package ui.ui_custom.ui_single_key_ciphers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomA1Z26DialogFragment extends CustomCipherDialogFragment {

    public CustomA1Z26DialogFragment() {
        super("default", "defaultext", "yes", "no");
    }

    public CustomA1Z26DialogFragment(String title, String editText, String positiveBtnText, String negativeBtnText) {
        super(title, editText, positiveBtnText, negativeBtnText);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        submit.setOnClickListener((View v) -> {
            String a1z26Key = input.getText().toString();
            if(!a1z26Key.equals("")) {
                onInputSelected.sendInput(a1z26Key, "-"); // add option for delim
                getDialog().dismiss();
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Invalid Input! Key must not be empty!", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener((View v) -> getDialog().dismiss());

        return view;
    }
}

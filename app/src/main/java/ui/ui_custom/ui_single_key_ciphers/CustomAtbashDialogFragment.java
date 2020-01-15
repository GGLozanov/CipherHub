package ui.ui_custom.ui_single_key_ciphers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAtbashDialogFragment extends CustomCipherDialogFragment {

    public CustomAtbashDialogFragment(String title, String editText, String positiveBtnText, String negativeBtnText) {
        super(title, editText, positiveBtnText, negativeBtnText);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        submit.setOnClickListener((View v) -> {
                String atbashKey = input.getText().toString();
                if(!atbashKey.equals("")) {
                    onInputSelected.sendInput(atbashKey);
                    getDialog().dismiss();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid input! Key must not be empty!", Toast.LENGTH_SHORT).show();
                }
        });

        cancel.setOnClickListener((View v) -> getDialog().dismiss()); // lambda func statement

        return view;
    }
}

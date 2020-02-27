package ui.ui_custom.ui_table_key_ciphers;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cipherhub.R;

import java.util.Vector;

import managers.CipherCallerManager;
import ui.ui_custom.ui_single_key_ciphers.CustomCipherFragment;
import ui.ui_custom.ui_table_key_ciphers.CustomTableDialogFragment;

public class CustomPolybiusFragment extends CustomCipherFragment implements CustomTableDialogFragment.OnInputSelected {

    private static Character[][] PolybiusSquare = {
        {'A', 'B', 'C', 'D', 'E', 'F'},
        {'G', 'H', 'I', 'J', 'K', 'L'},
        {'M', 'N', 'O', 'P', 'Q', 'R'},
        {'S', 'T', 'U', 'V', 'W', 'X'},
        {'Y', 'Z', '0', '1', '2', '3'},
        {'4', '5', '6', '7', '8', '9'}
    }; // firsthand initialisation

    @Override
    public void sendInput(Character[][] input) {
        PolybiusSquare = input;
        CipherCallerManager.instantiatePolybiusCipher();
    }

    public CustomPolybiusFragment() {
    }

    static public Character[][] getPolybiusKey() {return PolybiusSquare;}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        final Resources resources = getResources();

        dialogButton.setOnClickListener((View v) -> {
                CustomTableDialogFragment customTableDialogFragment = new CustomTableDialogFragment(resources.getString(R.string.custom_polybius_dialog_message),
                        resources.getString(R.string.custom_polybius_ref_message_one), resources.getString(R.string.custom_polybius_ref_message_two),
                        resources.getString(R.string.custom_polybius_ref_message_three));

                customTableDialogFragment.setTargetFragment(CustomPolybiusFragment.this, 3);
                customTableDialogFragment.show(getFragmentManager(), "Custom Polybius Dialog");
        });

        resetButton.setOnClickListener((View v) -> {
                sendInput(new Character[][]{
                        {'A', 'B', 'C', 'D', 'E', 'F'},
                        {'G', 'H', 'I', 'J', 'K', 'L'},
                        {'M', 'N', 'O', 'P', 'Q', 'R'},
                        {'S', 'T', 'U', 'V', 'W', 'X'},
                        {'Y', 'Z', '0', '1', '2', '3'},
                        {'4', '5', '6', '7', '8', '9'}
                }); // reset square back to default layout

                Toast.makeText(getActivity().getApplicationContext(), "Polybius Square reset to default 6x6", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}

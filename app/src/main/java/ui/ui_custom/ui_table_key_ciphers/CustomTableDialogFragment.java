package ui.ui_custom.ui_table_key_ciphers;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.*;
import com.example.cipherhub.R;

/**
 * A simple {@link Fragment} subclass.
 */

// Personal Protip: Classes extending only DialogFragment should have their own call to inflater.inflate() and not rely on the default DialogFragment layout!

public class CustomTableDialogFragment extends DialogFragment { // base class for dialogs which have a table

    protected String titleText;
    protected String refOneText;
    protected String refTwoText;
    protected String refThreeText;


    protected TextView title;
    protected TextView refOne;
    protected TextView refTwo;
    protected TextView refThree;
    protected TextView cancel;
    protected TextView submit;

    public interface OnInputSelected {
        void sendInput(Character[][] input);
    }

    OnInputSelected onInputSelected;

    public CustomTableDialogFragment(String title, String refOne, String refTwo, String refThree) { //init specific dialog layout elements
        titleText = title;
        refOneText = refOne;
        refTwoText = refTwo;
        refThreeText = refThree;
    }

    private void setParameters(View view) { //maybe make this method in an interface to be implemented by all dialog fragment classes?
        title = view.findViewById(R.id.CustomTableCipherHeading);
        refOne = view.findViewById(R.id.ReferralOne);
        refTwo = view.findViewById(R.id.ReferralTwo);
        refThree = view.findViewById(R.id.ReferralThree);
        submit = view.findViewById(R.id.CustomCipherOk);
        cancel = view.findViewById(R.id.CustomCipherCancel);

        title.setText(titleText);
        refOne.setText(refOneText);
        refTwo.setText(refTwoText);
        refThree.setText(refThreeText);
    }

    private void initDialog(DialogFragment dialogFragment, int reqCode, String tag) {
        // method to initialise DialogFragments
        // implement as interface for all fragments (?) -> future code readability idea
        dialogFragment.setTargetFragment(CustomTableDialogFragment.this, reqCode);
        dialogFragment.show(getFragmentManager(), tag);
    }

    private void setListeners() { //start other dialog; instanceof to differentiate?
        refOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPolybiusTableFragment tableOneFrag = new CustomPolybiusTableFragment(1);
                initDialog(tableOneFrag, 4, "Referral One");
            }
        });

        refTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPolybiusTableFragment tableTwoFrag = new CustomPolybiusTableFragment(2);
                initDialog(tableTwoFrag, 5, "Referral Two");
            }
        });

        refThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPolybiusTableFragment tableThreeFrag = new CustomPolybiusTableFragment(3);
                initDialog(tableThreeFrag, 6, "Referral Three");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInputSelected.sendInput(CustomPolybiusTableFragment.Square); // trigger sendInput to Polybius and instantiate the Cipher w/given key
                getDialog().dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_table_cipher_dialog, container, false);

        setParameters(view);
        setListeners();

        return view;
    }

    @Override
    public void onAttach(Context context) { // override first method of Fragment lifecycle (onAttach -> attaches to a given activity)
        super.onAttach(context);
        try {
            onInputSelected = (OnInputSelected) getTargetFragment(); // typecast to interface to accept type (interface becomes of type Fragment)
        } catch(ClassCastException e) {
            Log.e("Failed on class cast: ", e.getMessage());
        }
    }
}


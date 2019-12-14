package ui.ui_custom.ui_table_key_ciphers;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.example.cipherhub.R;

import managers.KeyCipherCallerManager;

public class AdditionalVigenereFragment extends Fragment {

    TextView title, description;
    static EditText keyInput;
    Switch keyEnableSwitch;

    public AdditionalVigenereFragment() {
        // required public empty constructor
    }

    public void initParameters(View view) {
        title = view.findViewById(R.id.customCipherTitle);
        description = view.findViewById(R.id.customCipherDescription);
        keyInput = view.findViewById(R.id.keyString);
        keyEnableSwitch = view.findViewById(R.id.keyEnableSwitch);

        keyInput.setVisibility(View.INVISIBLE);
    }

    static public String getExtraKey() {
        if(keyInput == null) return "";
        return keyInput.getText().toString();
    }

    private void clearKeyConstraints(ConstraintSet constraintSet) {
        constraintSet.clear(R.id.keyString, ConstraintSet.TOP); // clear() method removes a constraint with argument id of widget and anchor (which constraint)
        constraintSet.clear(R.id.keyString, ConstraintSet.BOTTOM);
        constraintSet.clear(R.id.keyString, ConstraintSet.END);
        constraintSet.clear(R.id.keyString, ConstraintSet.START); // constraints are written in xml file (use that as a guide)
    }

    private void setKeyConstraints(ConstraintSet constraintSet) {
        constraintSet.connect(R.id.keyString, ConstraintSet.TOP, R.id.keyEnableSwitch, ConstraintSet.BOTTOM); // from -> to widgets
        constraintSet.connect(R.id.keyString, ConstraintSet.BOTTOM, R.id.customCipherDescription, ConstraintSet.TOP);
        constraintSet.connect(R.id.keyString, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END); // PARENT_ID references parent view element
        constraintSet.connect(R.id.keyString, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
    }

    private void hideInput(ConstraintLayout constraintLayout, ConstraintSet constraintSet) {
        keyInput.setVisibility(View.INVISIBLE); // ternary cries here when using it
        clearKeyConstraints(constraintSet);
        constraintSet.connect(R.id.keyEnableSwitch, ConstraintSet.BOTTOM, R.id.customCipherDescription, ConstraintSet.TOP, 20); // optional argument -> margin

        // connect sets two elements' constraints with anchors
        // 4 arguments -> startID (id of widget to be constrained), startSide (anchor for constraint),
        // endID (id of widget to constraint to), endSide (anchor for constraint).

        constraintSet.applyTo(constraintLayout); // apply the changes made by the constraintSet to the ConstraintLayout
    }

    private void showInput(ConstraintLayout constraintLayout, ConstraintSet constraintSet) {
        setKeyConstraints(constraintSet);
        constraintSet.clear(R.id.keyEnableSwitch, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.keyEnableSwitch, ConstraintSet.BOTTOM, R.id.keyString, ConstraintSet.TOP);
        constraintSet.applyTo(constraintLayout);
        keyInput.setVisibility(View.VISIBLE);
    }

    public void setKeySwitchListener(final ConstraintLayout constraintLayout) {

        final ConstraintSet constraintSet = new ConstraintSet(); // ConstraintSet -> class to manage Constraints during run-time
        constraintSet.clone(constraintLayout); // clone a given constraint layout for usage

        // 1) remove EditText constraints -> default
        // 2) Set bottom Switch constraint to description -> default
        // 3) remove bottom Switch constraint -> pressed
        // 4) Set EditText constraints -> pressed

        hideInput(constraintLayout, constraintSet);

        keyEnableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    showInput(constraintLayout, constraintSet);
                } else {
                    hideInput(constraintLayout, constraintSet);
                    keyInput.setText("");
                }
            }
        });
    }

    public void setInputListener() {

        TextWatcher textChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                KeyCipherCallerManager.instantiateVigenereCipher();
            }
        };

        keyInput.addTextChangedListener(textChangedListener);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additional_vigenere, container, false);

        ConstraintLayout constraintLayout = view.findViewById(R.id.AdditionalVigenereLayout);

        initParameters(view);
        setKeySwitchListener(constraintLayout);
        setInputListener();

        return view;
    }
}

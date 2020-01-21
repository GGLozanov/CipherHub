package ui.ui_custom.ui_table_key_ciphers;

import android.content.SharedPreferences;
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
import androidx.core.content.ContextCompat;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import ciphers.VigenereCipher;
import managers.KeyCipherCallerManager;
import ui.ui_core.VisibilityFragment;

public class AdditionalVigenereFragment extends VisibilityFragment implements SetVisibilityModes, VisibilityFragment.Setup {

    TextView title, description;
    private static EditText keyInput;
    Switch keyEnableSwitch;
    static VigenereCipher keyParserCipher;

    private TextView[] infos;

    private SharedPreferences.Editor editor = Activity.getEditor(); // superclass Fragment soon^TM

    public AdditionalVigenereFragment() {
        keyParserCipher = new VigenereCipher();
        // required public empty constructor
    }

    public static String getExtraKey() {
        if(keyInput == null) return "";
        return keyParserCipher.getKeyString();
    }

    public static EditText getKeyInput() {
        return keyInput;
    }

    public static VigenereCipher getKeyParserCipher() {return keyParserCipher;}

    private void clearKeyConstraints(ConstraintSet constraintSet) {
        constraintSet.clear(R.id.additionalKey, ConstraintSet.TOP); // clear() method removes a constraint with argument id of widget and anchor (which constraint)
        constraintSet.clear(R.id.additionalKey, ConstraintSet.BOTTOM);
        constraintSet.clear(R.id.additionalKey, ConstraintSet.END);
        constraintSet.clear(R.id.additionalKey, ConstraintSet.START); // constraints are written in xml file (use that as a guide)
    }

    private void setKeyConstraints(ConstraintSet constraintSet) {
        constraintSet.connect(R.id.additionalKey, ConstraintSet.TOP, R.id.keyEnableSwitch, ConstraintSet.BOTTOM); // from -> to widgets
        constraintSet.connect(R.id.additionalKey, ConstraintSet.BOTTOM, R.id.customCipherDescription, ConstraintSet.TOP);
        constraintSet.connect(R.id.additionalKey, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END); // PARENT_ID references parent view element
        constraintSet.connect(R.id.additionalKey, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
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
        constraintSet.connect(R.id.keyEnableSwitch, ConstraintSet.BOTTOM, R.id.additionalKey, ConstraintSet.TOP);
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

        keyEnableSwitch.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
                if(isChecked) {
                    showInput(constraintLayout, constraintSet);
                } else {
                    hideInput(constraintLayout, constraintSet);
                    keyInput.setText("");
                }
        });
    }

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.AdditionalVigenereLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundLightColor));
        LayoutAdapter.setTextColors(infos, ContextCompat.getColor(context, R.color.lightTextColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.AdditionalVigenereLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundDarkColor));
        LayoutAdapter.setTextColors(infos, ContextCompat.getColor(context, R.color.darkTextColor));

        editor.apply();
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
                if(s.toString().equals("")) return;

                keyParserCipher = new VigenereCipher();
                keyParserCipher.setTemplate(s.toString());

                keyParserCipher.updateEncodingKeyByBase(KeyCipherCallerManager.getVigenereCipher().getKeyTemplate()); // enlarge additional key by the original key (use it as margin)

                KeyCipherCallerManager.getKeyText().setText(keyParserCipher.VigenereEncode(keyParserCipher.getKeyString(), KeyCipherCallerManager.getVigenereCipher().getKeyTemplate()));
            }
        };

        keyInput.addTextChangedListener(textChangedListener);
    }

    @Override
    public void setParameters() {
        title = view.findViewById(R.id.customCipherTitle);
        description = view.findViewById(R.id.customCipherDescription);
        keyInput = view.findViewById(R.id.additionalKey);
        keyEnableSwitch = view.findViewById(R.id.keyEnableSwitch);

        infos = new TextView[]{title, description, keyInput, keyEnableSwitch};

        setContext(getActivity());

        keyInput.setVisibility(View.INVISIBLE);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_additional_vigenere, container, false);

        ConstraintLayout constraintLayout = view.findViewById(R.id.AdditionalVigenereLayout);

        setParameters();
        setKeySwitchListener(constraintLayout);
        setInputListener();

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }
}

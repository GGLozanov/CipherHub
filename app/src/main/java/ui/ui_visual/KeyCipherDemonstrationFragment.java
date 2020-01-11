package ui.ui_visual;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import ui.ui_core.FragmentPageAdapter;

public class KeyCipherDemonstrationFragment extends DemonstrationFragment implements SetVisibilityModes {

    private TextView keyDescription;
    private EditText keyDemonstration;

    public TextView getKeyDescription() {return keyDescription;}
    public EditText getKeyImage() {return keyDemonstration;}

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.keyCipherDemonstrationLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.keyCipherDemonstrationLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));

        editor.apply();
    }

    public void setKeyDemonstrations(String key) { // Really, really banal code; rework later >:(
        Resources resources = getResources();
        switch(key) { // switch for easier access when more ciphers are added later on
            case "VigenereDemonstration":
                setDemonstrationText(resources.getString(R.string.vigenere_input_example), resources.getString(R.string.vigenere_output_example));
                keyDemonstration.setText(R.string.vigenere_key_example);
                break;
        }
    }

    public KeyCipherDemonstrationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_key_cipher_demonstration, container, false);
        Bundle bundle = getArguments();

        setParameters(view);

        keyDescription = view.findViewById(R.id.keyDescription);
        keyDemonstration = view.findViewById(R.id.keyDemonstration);

        keyDescription.setText(bundle.getString(FragmentPageAdapter.getCipherkeyDescriptionKey()));

        setKeyDemonstrations(bundle.getString(FragmentPageAdapter.getCipherDemonstrationKey()));

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }
}
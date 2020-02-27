package ui.ui_visual;

import android.os.Bundle;

import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.Enumerations;
import adapters.Handler;
import adapters.LayoutAdapter;
import ui.ui_core.VisibilityFragment;

public class KeyCipherDemonstrationFragment extends DemonstrationFragment implements SetVisibilityModes, VisibilityFragment.Setup {
    private TextView keyDescription;
    private EditText keyDemonstration;

    private TextView[] keyInfos;

    public TextView getKeyDescription() {return keyDescription;}
    public EditText getKeyImage() {return keyDemonstration;}

    @Override
    public void setLightTheme() {
        super.setLightTheme();
        LayoutAdapter.setTextColors(keyInfos, ContextCompat.getColor(context, R.color.lightTextColor));
    }

    @Override
    public void setDarkTheme() {
        super.setDarkTheme();
        LayoutAdapter.setTextColors(keyInfos, ContextCompat.getColor(context, R.color.darkTextColor));
    }

    public void setKeyDemonstrations(Enumerations.Demonstration key) { // Code is now better because of enums :D
        setDemonstrations(key);
        switch(key) { // switch for easier access when more ciphers are added later on
            case VigenereDemonstration:
                keyDemonstration.setText(R.string.vigenere_key_example);
                break;
            case PlayfairDemonstration:
                keyDemonstration.setText(resources.getString(R.string.playfair_key_example));
                break;
        }
    }

    public KeyCipherDemonstrationFragment() {

    }

    @Override
    public void setParameters() {
        keyDescription = view.findViewById(R.id.keyDescription);
        keyDemonstration = view.findViewById(R.id.keyDemonstration);

        keyInfos = new TextView[]{keyDemonstration, keyDescription};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_key_cipher_demonstration, container, false);
        Bundle bundle = getArguments();

        super.setParameters();
        setParameters();

        keyDescription.setText(bundle.getString(Handler.getCipherkeyDescriptionKey()));

        setKeyDemonstrations(Enumerations.Demonstration.valueOf(bundle.getString(Handler.getCipherDemonstrationKey())));

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }
}
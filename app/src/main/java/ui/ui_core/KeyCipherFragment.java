package ui.ui_core;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import managers.KeyCipherCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeyCipherFragment extends CipherFragment implements SetVisibilityModes, VisibilityFragment.Setup {

    private KeyCipherCallerManager keyCipherCallerManager = new KeyCipherCallerManager();

    protected EditText keyInput;

    public KeyCipherFragment() {
        // Required empty public constructor
    }

    @Override
    public void setLightTheme() {
        super.setLightTheme();
        keyInput.setTextColor(ContextCompat.getColor(context, R.color.lightTextColor));
    }

    @Override
    public void setDarkTheme() {
        super.setDarkTheme();
        keyInput.setTextColor(ContextCompat.getColor(context, R.color.darkTextColor));
    }

    public void setParameters() {
        keyInput = view.findViewById(R.id.keyInput);
        keyCipherCallerManager.setKeyText(keyInput);
        setCipherIOs(keyCipherCallerManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_key_cipher, container, false);

        super.setParameters();
        setParameters();

        keyCipherCallerManager.VigenereCipher(); // switch() for future key ciphers

        setTheme();

        return view;
    }

}

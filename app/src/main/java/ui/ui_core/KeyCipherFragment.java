package ui.ui_core;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import managers.KeyCipherCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeyCipherFragment extends VisibilityFragment implements SetVisibilityModes {

    private KeyCipherCallerManager keyCipherCallerManager = new KeyCipherCallerManager();
    private SharedPreferences.Editor editor = Activity.getEditor();

    public KeyCipherFragment() {
        // Required empty public constructor
    }

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.keyCipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.keyCipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));

        editor.apply();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_key_cipher, container, false);

        keyCipherCallerManager.setKeyText(view.findViewById(R.id.KeyString));
        keyCipherCallerManager.setDecodedInput(view.findViewById(R.id.decodedInput));
        keyCipherCallerManager.setEncodedOutput(view.findViewById(R.id.encodedOutput));

        keyCipherCallerManager.VigenereCipher(); // switch() for future key ciphers

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }

}

package ui.ui_core;


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
import managers.CipherCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class CipherFragment extends VisibilityFragment implements SetVisibilityModes { // FUTURE: Make superclass Fragment to improve code

    CipherCallerManager cipherManager = new CipherCallerManager();

    String cipherKey;

    public CipherFragment() {
        // Required empty public constructor
    }

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        // view = getLayoutInflater().inflate(R.layout.fragment_cipher, container, false);

        editor.putBoolean(Activity.getModeKey(), true);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));

        editor.apply();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cipher, container, false);
        cipherKey = getArguments().getString(FragmentPageAdapter.getCipherKey());

        cipherManager.setDecodedInput(view.findViewById(R.id.DecodedInputString)); //findViewById is a method from the View type class
        cipherManager.setEncodedOutput(view.findViewById(R.id.EncodedOutputString));

        callCipher(cipherKey);

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }

    public void callCipher(String cipherKey) {
        switch (cipherKey) {
            case "Caesar":
                cipherManager.CaesarCipher();
                break;

            case "Atbash":
                cipherManager.AtbashCipher();
                break;

            case "Polybius":
                cipherManager.PolybiusCipher();
                break;

            default: break;
        }
    }

}
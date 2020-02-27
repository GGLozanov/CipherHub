package ui.ui_core;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.Enumerations;
import adapters.Handler;
import adapters.LayoutAdapter;
import managers.CipherCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class CipherFragment extends VisibilityFragment implements SetVisibilityModes, VisibilityFragment.Setup { // FUTURE: Make superclass Fragment to improve code

    protected EditText decodedInput;
    protected EditText encodedOutput;

    protected EditText[] IOs;

    CipherCallerManager cipherManager = new CipherCallerManager();

    Enumerations.Cipher cipher;

    public CipherFragment() {
        // Required empty public constructor
    }

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundLightColor));
        LayoutAdapter.setTextColors(IOs, ContextCompat.getColor(context, R.color.lightTextColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundDarkColor));
        LayoutAdapter.setTextColors(IOs, ContextCompat.getColor(context, R.color.darkTextColor));

        editor.apply();
    }

    @Override
    public void setParameters() {
        cipher = Enumerations.Cipher.valueOf(getArguments().getString(Handler.getCipherKey()));

        decodedInput = view.findViewById(R.id.decodedInput);
        encodedOutput = view.findViewById(R.id.encodedOutput);

        IOs = new EditText[]{decodedInput, encodedOutput};

        setContext(getActivity());
        setCipherIOs(cipherManager);
    }

    public void setCipherIOs(CipherCallerManager cipherManager) {
        cipherManager.setDecodedInput(decodedInput); //findViewById is a method from the View type class
        cipherManager.setEncodedOutput(encodedOutput);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cipher, container, false);

        setParameters();

        callCipher(cipher);

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }

    public void callCipher(Enumerations.Cipher cipher) {
        switch (cipher) {
            case CaesarCipher:
                cipherManager.CaesarCipher();
                break;

            case AtbashCipher:
                cipherManager.AtbashCipher();
                break;

            case PolybiusCipher:
                cipherManager.PolybiusCipher();
                break;

            case A1Z26Cipher:
                cipherManager.A1Z26Cipher();
                break;

            default: break;
        }
    }

}

package ui.ui_core;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.FragmentPageAdapter;
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

    String cipherKey;

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
        Log.d("Called default cipher", "True");
        cipherKey = getArguments().getString(FragmentPageAdapter.getCipherKey());

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

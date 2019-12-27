package ui;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import managers.KeyCipherCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeyCipherFragment extends Fragment implements SetVisibilityModes {

    private KeyCipherCallerManager keyCipherCallerManager = new KeyCipherCallerManager();
    private SharedPreferences.Editor editor = Activity.getEditor();

    public KeyCipherFragment() {
        // Required empty public constructor
    }

    @Override
    public void setLightMode(View view) {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.keyCipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));

        editor.apply();
    }

    @Override
    public void setDarkMode(View view) {
        editor.putBoolean(Activity.getModeKey(), true);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.keyCipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));

        editor.apply();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_key_cipher, container, false);

        keyCipherCallerManager.setKeyText((EditText) view.findViewById(R.id.KeyString));
        keyCipherCallerManager.setDecodedInput((EditText) view.findViewById(R.id.DecodedInputString));
        keyCipherCallerManager.setEncodedOutput((EditText) view.findViewById(R.id.EncodedOutputString));

        keyCipherCallerManager.VigenereCipher();

        if(Activity.getMode()) setDarkMode(view);
        else setLightMode(view);

        return view;
    }

}

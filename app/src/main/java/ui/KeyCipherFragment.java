package ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cipherhub.R;

import managers.KeyCipherCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeyCipherFragment extends Fragment {

    KeyCipherCallerManager keycipherManager = new KeyCipherCallerManager();

    public KeyCipherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_key_cipher, container, false);

        keycipherManager.setKeyText((EditText) view.findViewById(R.id.KeyString));
        keycipherManager.setDecodedInput((EditText) view.findViewById(R.id.DecodedInputString));
        keycipherManager.setEncodedOutput((EditText) view.findViewById(R.id.EncodedOutputString));

        keycipherManager.VigenereCipher();

        return view;
    }

}

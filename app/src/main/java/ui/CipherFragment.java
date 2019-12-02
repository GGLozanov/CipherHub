package ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cipherhub.R;

import ciphers.CaesarCipher;


/**
 * A simple {@link Fragment} subclass.
 */
public class CipherFragment extends Fragment {

    CipherCallerManager cipherManager = new CipherCallerManager();

    public CipherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cipher, container, false);
        final String cipherKey = getArguments().getString("Cipher");

        cipherManager.setDecodedInput((EditText) view.findViewById(R.id.DecodedInputString)); //findViewById is a method from the View type class
        cipherManager.setEncodedOutput((EditText) view.findViewById(R.id.EncodedOutputString));

        switch (cipherKey) {
            case "Caesar":
                cipherManager.CaesarCipher();
                break;

            case "Vigenere":
                break;

            case "Atbash":
                cipherManager.AtbashCipher();
                break;

            case "Polybius":
                cipherManager.PolybiusCipher();
                break;

            default: break;
        }

        return view;
    }


}

package ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cipherhub.R;

import managers.CipherCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class CipherFragment extends Fragment {

    CipherCallerManager cipherManager = new CipherCallerManager();

    String cipherKey;

    public CipherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cipher, container, false);
        cipherKey = getArguments().getString("Cipher");

        cipherManager.setDecodedInput((EditText) view.findViewById(R.id.DecodedInputString)); //findViewById is a method from the View type class
        cipherManager.setEncodedOutput((EditText) view.findViewById(R.id.EncodedOutputString));

        callCipher(cipherKey);

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

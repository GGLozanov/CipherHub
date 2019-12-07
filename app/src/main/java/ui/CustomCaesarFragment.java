package ui;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cipherhub.R;

import javax.crypto.Cipher;

import managers.CipherCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomCaesarFragment extends Fragment implements CustomCaesarDialogFragment.OnInputSelected {

    TextView title;
    TextView description;
    Button dialogButton;

    static private String caesarKey = "3";

    static public String getCaesarKey() {return caesarKey;}

    @Override
    public void sendInput(String input) {
        caesarKey = input;
        CipherCallerManager.instantiateCaesarCipher();
    }

    public CustomCaesarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_caesar, container, false);

        title = view.findViewById(R.id.CustomCipherTitle);
        description = view.findViewById(R.id.CustomCipherDescription);

        dialogButton = view.findViewById(R.id.DialogButton);

        title.setText(getArguments().getString("Title"));
        description.setText(getArguments().getString("Description"));
        dialogButton.setText(getArguments().getString("ButtonOne"));

        // String customCipherKey = getArguments().getString("Custom");

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomCaesarDialogFragment customCaesarDialogFragment = new CustomCaesarDialogFragment();
                customCaesarDialogFragment.setTargetFragment(CustomCaesarFragment.this, 1); //request code for accessing the target fragment outside
                customCaesarDialogFragment.show(getFragmentManager(), "Custom Caesar Dialog");
            }
        });

        return view;
    }
}

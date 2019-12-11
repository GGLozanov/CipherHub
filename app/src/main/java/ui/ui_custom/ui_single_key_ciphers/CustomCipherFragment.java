package ui.ui_custom.ui_single_key_ciphers;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cipherhub.R;

import managers.CipherCallerManager;
import ui.FragmentPageAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomCipherFragment extends Fragment {

    protected TextView title;
    protected TextView description;
    protected Button dialogButton;
    protected  Button resetButton;

    public CustomCipherFragment() {
        // Required empty public constructor
    }

    protected void setParameters(View view) {

        title = view.findViewById(R.id.CustomCipherTitle);
        description = view.findViewById(R.id.CustomCipherDescription);

        dialogButton = view.findViewById(R.id.DialogButton);
        resetButton = view.findViewById(R.id.ResetButton);

        // static method calls

        title.setText(getArguments().getString(FragmentPageAdapter.getTitleKey()));
        description.setText(getArguments().getString(FragmentPageAdapter.getDescriptionKey()));
        dialogButton.setText(getArguments().getString(FragmentPageAdapter.getButtonOneKey()));
        resetButton.setText(getArguments().getString(FragmentPageAdapter.getResetKey()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_cipher, container, false);

        setParameters(view);

        // String customCipherKey = getArguments().getString("Custom");

        return view;
    }
}

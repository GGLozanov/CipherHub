package ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cipherhub.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomCaesarFragment extends Fragment {

    TextView title;
    TextView description;
    Button dialogButton;

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

            }
        });

        return view;
    }

}

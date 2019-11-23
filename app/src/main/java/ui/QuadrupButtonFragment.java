package ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cipherhub.AtbashActivity;
import com.example.cipherhub.CaesarActivity;
import com.example.cipherhub.PolybiusActivity;
import com.example.cipherhub.R;
import com.example.cipherhub.VigenereActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuadrupButtonFragment extends Fragment {

    //fragment containing 4 buttons -> for main activity navigation
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;

    public QuadrupButtonFragment() {
        // Required empty public constructor
    }

    private void initButtons(View view) {
        buttonOne = view.findViewById(R.id.buttonOne);
        buttonTwo = view.findViewById(R.id.buttonTwo);
        buttonThree = view.findViewById(R.id.buttonThree);
        buttonFour = view.findViewById(R.id.buttonFour);

        buttonOne.setText(getArguments().getString("ButtonOne"));
        buttonTwo.setText(getArguments().getString("ButtonTwo"));
        buttonThree.setText(getArguments().getString("ButtonThree"));
        buttonFour.setText(getArguments().getString("ButtonFour"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quadrup_button, container, false);

        initButtons(view);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPolybiusActivity();
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCaesarActivity();
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenVigenereActivity();
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAtbashActivity();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


    private void OpenCaesarActivity(){
        Intent CaesarIntent = new Intent(getActivity(), CaesarActivity.class);
        //first constructor argument takes the CONTEXT of the package (this package, the one we're currently  -> getActiviy() gets current context for fragment).
        //second constructor argument takes the class of the Activity we want to start; we can access the class through the property 'class' of the Activity.
        //Research ContentProviders (abstract data providers) and URI - Uniform Resource Identifier (string of characters that represent a resource).
        startActivity(CaesarIntent);
    }

    private void OpenVigenereActivity() {
        Intent VigenereIntent = new Intent(getActivity(), VigenereActivity.class);

        startActivity(VigenereIntent);
    }

    private void OpenAtbashActivity() {
        Intent AtbashIntent = new Intent(getActivity(), AtbashActivity.class);

        startActivity(AtbashIntent);
    }

    private void OpenPolybiusActivity() {
        Intent PolybiusIntent = new Intent(getActivity(), PolybiusActivity.class);

        startActivity(PolybiusIntent);
    }


}

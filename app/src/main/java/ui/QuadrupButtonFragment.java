package ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cipherhub.R;

import managers.ActivityCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuadrupButtonFragment extends Fragment {

    //fragment containing 4 buttons -> for main activity navigation
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;

    private ActivityCallerManager activityCallerManager = new ActivityCallerManager(this); //constructor takes current fragment as reference

    public QuadrupButtonFragment() {
        // Required empty public constructor
    }

    private void initButtons(View view) {
        buttonOne = view.findViewById(R.id.buttonOne);
        buttonTwo = view.findViewById(R.id.buttonTwo);
        buttonThree = view.findViewById(R.id.buttonThree);
        buttonFour = view.findViewById(R.id.buttonFour);

        buttonOne.setText(getArguments().getString(FragmentPageAdapter.getButtonOneKey()));
        buttonTwo.setText(getArguments().getString(FragmentPageAdapter.getButtonTwoKey()));
        buttonThree.setText(getArguments().getString(FragmentPageAdapter.getButtonThreeKey()));
        buttonFour.setText(getArguments().getString(FragmentPageAdapter.getButtonFourKey()));
    }

    private void setListeners() {
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCallerManager.OpenCaesarActivity();
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCallerManager.OpenVigenereActivity();
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCallerManager.OpenAtbashActivity();
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCallerManager.OpenPolybiusActivity();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quadrup_button, container, false);

        initButtons(view);
        setListeners();

        // Inflate the layout for this fragment
        return view;
    }


}

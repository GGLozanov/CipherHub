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

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import managers.ActivityCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuadrupButtonFragment extends Fragment implements SetVisibilityModes {

    //fragment containing 4 buttons -> for main activity navigation
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;

    private ActivityCallerManager activityCallerManager = new ActivityCallerManager(this); //constructor takes current fragment as reference

    SharedPreferences.Editor editor = Activity.getEditor();


    @Override
    public void setLightMode(View view) {

        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.quadrupButtonLayout));

        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));
        layoutAdapter.setButtonsLightMode(new Button[]{view.findViewById(R.id.buttonOne), view.findViewById(R.id.buttonTwo), view.findViewById(R.id.buttonThree), view.findViewById(R.id.buttonFour)});
        // layoutAdapter.setDialogLayoutBackgroundColor(ContextCompat.getColor(this, R.color.backgroundDarkColor));

        editor.apply();
    }

    @Override
    public void setDarkMode(View view) {
        editor.putBoolean(Activity.getModeKey(), true);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.quadrupButtonLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));
        layoutAdapter.setButtonsDarkMode(new Button[]{view.findViewById(R.id.buttonOne), view.findViewById(R.id.buttonTwo), view.findViewById(R.id.buttonThree), view.findViewById(R.id.buttonFour)});

        editor.apply();
    }

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

        if(Activity.getMode()) setDarkMode(view);
        else setLightMode(view);

        // Inflate the layout for this fragment
        return view;
    }


}

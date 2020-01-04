package ui.ui_core;


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
public class QuadrupButtonFragment extends VisibilityFragment implements SetVisibilityModes {

    //fragment containing 4 buttons -> for main activity navigation
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;

    private ActivityCallerManager activityCallerManager = new ActivityCallerManager(this); //constructor takes current fragment as reference

    SharedPreferences.Editor editor = Activity.getEditor();

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.quadrupButtonLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));
        layoutAdapter.setButtonsLightMode(new Button[]{view.findViewById(R.id.buttonOne), view.findViewById(R.id.buttonTwo), view.findViewById(R.id.buttonThree), view.findViewById(R.id.buttonFour)});

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
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

    // Warning: Won't work below Java 8 (lambdas)

    private void setListeners() {
        buttonOne.setOnClickListener((View v) -> activityCallerManager.OpenCaesarActivity());

        buttonTwo.setOnClickListener((View v) -> activityCallerManager.OpenVigenereActivity());

        buttonThree.setOnClickListener((View v) -> activityCallerManager.OpenAtbashActivity());

        buttonFour.setOnClickListener((View v) -> activityCallerManager.OpenPolybiusActivity()); // lambda onClick() function designated with '->' and argument list before that
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_quadrup_button, container, false);

        initButtons(view);
        setListeners();

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        // Inflate the layout for this fragment
        return view;
    }


}

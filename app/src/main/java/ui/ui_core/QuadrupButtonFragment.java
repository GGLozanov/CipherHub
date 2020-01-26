package ui.ui_core;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.FragmentPageAdapter;
import adapters.LayoutAdapter;
import managers.ActivityCallerManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuadrupButtonFragment extends VisibilityFragment implements SetVisibilityModes, VisibilityFragment.Setup {

    // fragment containing 4 buttons -> for main activity navigation
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;

    private ActivityCallerManager activityCallerManager = new ActivityCallerManager(this); // constructor takes current fragment as reference

    private Button[] buttons;

    SharedPreferences.Editor editor = Activity.getEditor();

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.quadrupButtonLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundLightColor));
        layoutAdapter.setButtonsLightMode(buttons, context);

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.quadrupButtonLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));
        layoutAdapter.setButtonsDarkMode(buttons, context);

        editor.apply();
    }

    public QuadrupButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public void setParameters() {
        buttonOne = view.findViewById(R.id.buttonOne);
        buttonTwo = view.findViewById(R.id.buttonTwo);
        buttonThree = view.findViewById(R.id.buttonThree);
        buttonFour = view.findViewById(R.id.buttonFour);

        buttons = new Button[]{buttonOne, buttonTwo, buttonThree, buttonFour};

        setContext(getActivity());

        Bundle bundle = getArguments();

        buttonOne.setText(bundle.getString(FragmentPageAdapter.getButtonOneKey()));
        buttonTwo.setText(bundle.getString(FragmentPageAdapter.getButtonTwoKey()));
        buttonThree.setText(bundle.getString(FragmentPageAdapter.getButtonThreeKey()));
        buttonFour.setText(bundle.getString(FragmentPageAdapter.getButtonFourKey()));
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        FragmentActivity activity = getActivity();
        if(activity instanceof Activity) {
            ((Activity) activity).configureToolbar();
        }
    }


    public interface IntentAction {
        void OpenActivity();
    }

    private IntentAction[] determinePage() {
        IntentAction[] intentActions = {};

        switch(getArguments().getString(FragmentPageAdapter.getPageKey())) {
            case "Page 1":
                 intentActions = new IntentAction[] {
                    new IntentAction() {public void OpenActivity() {activityCallerManager.OpenCaesarActivity();} },
                    new IntentAction() {public void OpenActivity() {activityCallerManager.OpenVigenereActivity();} },
                    new IntentAction() {public void OpenActivity() {activityCallerManager.OpenAtbashActivity();} },
                    new IntentAction() {public void OpenActivity() {activityCallerManager.OpenPolybiusActivity();} }
                 };
                 break;
            case "Page 2":
                intentActions = new IntentAction[] {
                        new IntentAction() {public void OpenActivity() {activityCallerManager.OpenA1Z26Activity();} },
                        new IntentAction() {public void OpenActivity() {activityCallerManager.OpenVigenereActivity();} },
                        new IntentAction() {public void OpenActivity() {activityCallerManager.OpenAtbashActivity();} },
                        new IntentAction() {public void OpenActivity() {activityCallerManager.OpenPolybiusActivity();} }
                };
                break;
        }
        return intentActions;
    }

    // Warning: Won't work below Java 8 (lambdas)

    private void setListeners(IntentAction[] intentActions) {
        buttonOne.setOnClickListener((View v) -> intentActions[0].OpenActivity());

        buttonTwo.setOnClickListener((View v) -> intentActions[1].OpenActivity());

        buttonThree.setOnClickListener((View v) -> intentActions[2].OpenActivity());

        buttonFour.setOnClickListener((View v) -> intentActions[3].OpenActivity()); // lambda onClick() function designated with '->' and argument list before that (no C++ [] for scope resolution anymore!)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_quadrup_button, container, false);

        setParameters();
        setListeners(determinePage());

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        // Inflate the layout for this fragment
        return view;
    }


}

package ui.ui_custom.ui_single_key_ciphers;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import ui.ui_core.FragmentPageAdapter;
import ui.ui_core.VisibilityFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomCipherFragment extends VisibilityFragment implements SetVisibilityModes {

    protected TextView title;
    protected TextView description;
    protected Button dialogButton;
    protected  Button resetButton;

    private SharedPreferences.Editor editor = Activity.getEditor();

    public CustomCipherFragment() {
        // Required empty public constructor
    }

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.customCipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));

        layoutAdapter.setButtonsLightMode(new Button[]{view.findViewById(R.id.DialogButton), view.findViewById(R.id.ResetButton)});

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.customCipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));

        layoutAdapter.setButtonsDarkMode(new Button[]{view.findViewById(R.id.DialogButton), view.findViewById(R.id.ResetButton)});

        editor.apply();
    }

    protected void setParameters(View view) {

        title = view.findViewById(R.id.CustomCipherTitle);
        description = view.findViewById(R.id.CustomCipherDescription);

        dialogButton = view.findViewById(R.id.DialogButton);
        resetButton = view.findViewById(R.id.ResetButton);

        // static method calls

        Bundle bundle = getArguments();

        title.setText(bundle.getString(FragmentPageAdapter.getTitleKey()));
        description.setText(bundle.getString(FragmentPageAdapter.getDescriptionKey()));
        dialogButton.setText(bundle.getString(FragmentPageAdapter.getButtonOneKey()));
        resetButton.setText(bundle.getString(FragmentPageAdapter.getResetKey()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_custom_cipher, container, false);

        setParameters(view);

        if(Activity.getMode()) setDarkTheme(); // affects all classes in the inheritance tree (Go inheritance!)
        else setLightTheme();

        return view;
    }
}

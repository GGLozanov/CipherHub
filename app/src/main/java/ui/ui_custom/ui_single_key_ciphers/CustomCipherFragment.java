package ui.ui_custom.ui_single_key_ciphers;


import android.content.SharedPreferences;
import android.content.res.Resources;
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

import adapters.Handler;
import adapters.LayoutAdapter;
import ui.ui_core.VisibilityFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomCipherFragment extends VisibilityFragment implements SetVisibilityModes, VisibilityFragment.Setup {

    protected TextView title;
    protected TextView description;
    protected Button dialogButton;
    protected Button resetButton;

    protected Button[] buttons;
    protected TextView[] infos;

    protected Resources resources;

    private SharedPreferences.Editor editor = Activity.getEditor();

    public CustomCipherFragment() {
        // Required empty public constructor
    }

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.customCipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundLightColor));
        layoutAdapter.setButtonsLightMode(buttons, context);
        LayoutAdapter.setTextColors(infos, ContextCompat.getColor(context, R.color.lightTextColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.customCipherLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundDarkColor));
        layoutAdapter.setButtonsDarkMode(buttons, context);
        LayoutAdapter.setTextColors(infos, ContextCompat.getColor(context, R.color.darkTextColor));

        editor.apply();
    }

    @Override
    public void setParameters() {

        title = view.findViewById(R.id.CustomCipherTitle);
        description = view.findViewById(R.id.CustomCipherDescription);

        dialogButton = view.findViewById(R.id.DialogButton);
        resetButton = view.findViewById(R.id.ResetButton);

        buttons = new Button[]{dialogButton, resetButton};
        infos = new TextView[]{title, description};

        setContext(getActivity());

        // static method calls for keys

        Bundle bundle = getArguments();

        title.setText(bundle.getString(Handler.getTitleKey()));
        description.setText(bundle.getString(Handler.getDescriptionKey()));
        dialogButton.setText(bundle.getString(Handler.getButtonOneKey()));
        resetButton.setText(bundle.getString(Handler.getResetKey()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_custom_cipher, container, false);
        resources = getResources();

        setParameters();

        if(Activity.getMode()) setDarkTheme(); // affects all classes in the inheritance tree (Go inheritance!)
        else setLightTheme();

        return view;
    }
}

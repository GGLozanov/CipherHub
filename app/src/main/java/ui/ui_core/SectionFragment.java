package ui.ui_core;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.FragmentPageAdapter;
import adapters.LayoutAdapter;

public class SectionFragment extends VisibilityFragment implements SetVisibilityModes, VisibilityFragment.Setup {

    TextView title;
    TextView description;

    private TextView[] infos;

    public SectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.sectionLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundLightColor));
        LayoutAdapter.setTextColors(infos, ContextCompat.getColor(context, R.color.lightTextColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.sectionLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundDarkColor));
        LayoutAdapter.setTextColors(infos, ContextCompat.getColor(context, R.color.darkTextColor));

        editor.apply();
    }

    @Override
    public void setParameters() {
        title = view.findViewById(R.id.sectionTitle);
        description = view.findViewById(R.id.sectionDescription);

        title.setText(getArguments().getString(FragmentPageAdapter.getTitleKey()));

        // Bundle -> a map of key strings to various Parcelable values (strings, most likely)
        // getArguments() method selects the current Bundle (savedInstanceState) and returns the bundled arguments.

        description.setText(getArguments().getString(FragmentPageAdapter.getDescriptionKey()));

        // getActivity() method returns the context of the current activity the fragment is in
        infos = new TextView[]{title, description};

        setContext(getActivity());
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_section, container, false);

        setParameters();

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }

}

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
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends Fragment implements SetVisibilityModes {

    TextView title;
    TextView description;

    public SectionFragment() {
        // Required empty public constructor
    }

    SharedPreferences.Editor editor = Activity.getEditor();

    @Override
    public void setLightMode(View view) {

        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.sectionLayout));

        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));

        editor.apply();
    }

    @Override
    public void setDarkMode(View view) {
        editor.putBoolean(Activity.getModeKey(), true);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.sectionLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));

        editor.apply();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section, container, false);
        title = view.findViewById(R.id.sectionTitle);
        description = view.findViewById(R.id.sectionDescription);

        title.setText(getArguments().getString(FragmentPageAdapter.getTitleKey()));
        //Bundle -> a map of key strings to various Parcelable values (strings, most likely)
        //getArguments() method selects the current Bundle (savedInstanceState) and returns the bundled arguments.
        description.setText(getArguments().getString(FragmentPageAdapter.getDescriptionKey()));
        //getActivity() method returns the context of the current activity the fragment is in

        if(Activity.getMode()) setDarkMode(view);
        else setLightMode(view);

        return view;
    }

}

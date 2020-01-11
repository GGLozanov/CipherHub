package ui.ui_visual;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import ui.ui_core.FragmentPageAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CipherDemonstrationFragment extends DemonstrationFragment implements SetVisibilityModes {

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherDemonstrationLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true); // put key "Mode" and 'true' for indicating Dark mode

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherDemonstrationLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor)); // add method to change images

        editor.apply();
    }


    public CipherDemonstrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = super.onCreateView(inflater, container, savedInstanceState);

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }

}

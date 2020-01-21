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
import ui.ui_core.VisibilityFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CipherVisualisationFragment extends VisibilityFragment implements SetVisibilityModes {


    @Override
    public void setDarkTheme() {
        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherVisualisationLayout));

        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));
    }

    @Override
    public void setLightTheme() {
        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherVisualisationLayout));

        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));
    }

    public CipherVisualisationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cipher_visualisation, container, false);

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }

}

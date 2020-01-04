package ui.ui_visual;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import ui.ui_core.VisibilityFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CipherVisualisationFragment extends VisibilityFragment implements SetVisibilityModes {


    @Override
    public void setDarkTheme() {

    }

    @Override
    public void setLightTheme() {

    }

    public CipherVisualisationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cipher_visualisation, container, false);

        if(Activity.getMode()) setLightTheme();
        else setDarkTheme();

        return view;
    }

}

package ui.ui_visual;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import ui.ui_core.VisibilityFragment;

public class KeyCipherDemonstrationFragment extends DemonstrationFragment implements SetVisibilityModes {

    @Override
    public void setDarkTheme() {

    }

    @Override
    public void setLightTheme() {

    }

    public KeyCipherDemonstrationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cipher_demonstration, container, false);

        if(Activity.getMode()) setLightTheme();
        else setDarkTheme();


        return view;
    }
}
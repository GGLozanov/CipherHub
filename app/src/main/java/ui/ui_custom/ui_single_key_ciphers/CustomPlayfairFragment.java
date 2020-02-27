package ui.ui_custom.ui_single_key_ciphers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ui.ui_core.VisibilityFragment;

public class CustomPlayfairFragment extends CustomCipherFragment {
    public CustomPlayfairFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);

        setParameters();

        return view;
    }
}

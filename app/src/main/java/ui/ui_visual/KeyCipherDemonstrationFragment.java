package ui.ui_visual;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import ui.ui_core.FragmentPageAdapter;

public class KeyCipherDemonstrationFragment extends DemonstrationFragment implements SetVisibilityModes {

    private TextView keyDescription;
    private ImageView keyImage;
    private Bundle bundle;

    public TextView getKeyDescription() {return keyDescription;}
    public ImageView getKeyImage() {return keyImage;}
    public Bundle getBundle() {return bundle;}

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.keyCipherDemonstrationLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundLightColor));

        String key = getArguments().getString(FragmentPageAdapter.getCipherImageKey());

        setImages(key, false); // test drawables, again
        setKeyImage(key, false);

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        LayoutAdapter layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.keyCipherDemonstrationLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(getActivity(), R.color.backgroundDarkColor));

        String key = getArguments().getString(FragmentPageAdapter.getCipherImageKey());

        setImages(key, true); // test drawables, again
        setKeyImage(key, true);

        editor.apply();
    }

    private void setKeyImageResource(int resource) {
        keyImage.setImageResource(resource);
    }

    public void setKeyImage(String key, boolean mode) { // Really, really banal code; rework later >:(
        switch(key) { // switch for easier access when more ciphers are added later on
            case "VigenereDemonstrationImages":
                if(mode) setKeyImageResource(R.drawable.button_dark_background); // test drawable
                else setKeyImageResource(R.drawable.button_light_background);
                break;
        }
    }

    public KeyCipherDemonstrationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_key_cipher_demonstration, container, false);
        bundle = getArguments();

        setParameters(view);

        keyDescription = view.findViewById(R.id.keyDescription);
        keyImage = view.findViewById(R.id.keyImage);

        keyDescription.setText(bundle.getString(FragmentPageAdapter.getCipherkeyDescriptionKey()));

        setKeyImage(bundle.getString(FragmentPageAdapter.getCipherImageKey()), Activity.getMode());

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }
}
package ui.ui_visual;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cipherhub.R;

import ui.ui_core.FragmentPageAdapter;
import ui.ui_core.VisibilityFragment;

public class DemonstrationFragment extends VisibilityFragment {

    protected TextView inputDescription;
    protected TextView outputDescription;
    protected ImageView inputImage;
    protected ImageView outputImage;

    public DemonstrationFragment() {

    }

    public void setParameters(View view) {
        Bundle bundle = getArguments();

        inputDescription = view.findViewById(R.id.inputDescription);
        outputDescription = view.findViewById(R.id.outputDescription);

        inputImage = view.findViewById(R.id.inputImage);
        outputImage = view.findViewById(R.id.outputImage);

        inputDescription.setText(bundle.getString(FragmentPageAdapter.getInputDescriptionKey()));
        outputDescription.setText(bundle.getString(FragmentPageAdapter.getOutputDescriptionKey()));
        setImages(bundle.getString(FragmentPageAdapter.getCipherImageKey()));
    }

    public void setImages(String value) {
        switch(value) {
            case "CaesarDemonstrationImages":
                break;
            case "VigenereDemonstrationImages":
                break;
            case "AtbashDemonstrationImages":
                break;
            case "PolybiusDemonstrationImages":
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cipher_demonstration, container, false);
        setParameters(view);

        return view;
    }
}

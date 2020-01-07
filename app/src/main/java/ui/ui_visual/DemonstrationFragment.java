package ui.ui_visual;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;

import ui.ui_core.FragmentPageAdapter;
import ui.ui_core.VisibilityFragment;

public class DemonstrationFragment extends VisibilityFragment {

    protected TextView inputDescription;
    protected TextView outputDescription;
    protected ImageView inputImage;
    protected ImageView outputImage;

    public TextView getInputDescription() {return inputDescription;}
    public TextView getOutputDescription() {return outputDescription;}
    public ImageView getInputImage() {return inputImage;}
    public ImageView getOutputImage() {return outputImage;}

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
        setImages(bundle.getString(FragmentPageAdapter.getCipherImageKey()), Activity.getMode());
    }

    public void setIOImageResources(int[] resources) {
        inputImage.setImageResource(resources[0]);
        outputImage.setImageResource(resources[1]);
    }

    public void setImages(String key, boolean mode) { // default is false
        switch(key) { // fix code later; looks unkempt
            case "CaesarDemonstrationImages":
                if(mode) setIOImageResources(new int[]{R.drawable.caesar_demonstration_input_dark, R.drawable.caesar_demonstration_output_dark});
                else setIOImageResources(new int[]{R.drawable.caesar_demonstration_input_light, R.drawable.caesar_demonstration_output_light});
                break;
            case "VigenereDemonstrationImages":
                if(mode) setIOImageResources(new int[]{R.drawable.button_dark_background, R.drawable.dialog_dark_background}); // test drawables
                else setIOImageResources(new int[]{R.drawable.dialog_light_button_background, R.drawable.dialog_light_button_background});
                break;
            case "AtbashDemonstrationImages":
                if(mode) setIOImageResources(new int[]{R.drawable.atbash_demonstration_input_dark, R.drawable.atbash_demonstration_output_dark});
                else setIOImageResources(new int[]{R.drawable.atbash_demonstration_input_light, R.drawable.atbash_demonstration_output_light});
                break;
            case "PolybiusDemonstrationImages":
                if(mode) setIOImageResources(new int[]{R.drawable.button_dark_background, R.drawable.dialog_dark_background}); // test drawables
                else setIOImageResources(new int[]{R.drawable.dialog_light_button_background, R.drawable.dialog_light_button_background});
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

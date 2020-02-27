package ui.ui_visual;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.Enumerations;
import adapters.Handler;
import adapters.LayoutAdapter;
import ui.ui_core.VisibilityFragment;

public class DemonstrationFragment extends VisibilityFragment implements SetVisibilityModes, VisibilityFragment.Setup {

    protected TextView inputDescription;
    protected TextView outputDescription;
    protected EditText inputDemonstration;
    protected EditText outputDemonstration;

    protected TextView[] infos;
    protected String[] keys;
    Resources resources;

    public TextView getInputDescription() {return inputDescription;}
    public TextView getOutputDescription() {return outputDescription;}
    public EditText getInputEditText() {return inputDemonstration;}
    public EditText getOutputEditText() {return outputDemonstration;}

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false); // put key "Mode" and 'false' for indicating Light mode

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherDemonstrationLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundLightColor));
        LayoutAdapter.setTextColors(infos, ContextCompat.getColor(context, R.color.lightTextColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true); // put key "Mode" and 'true' for indicating Dark mode

        layoutAdapter = new LayoutAdapter((ConstraintLayout) view.findViewById(R.id.cipherDemonstrationLayout));
        layoutAdapter.setFrameLayoutBackgroundColor(ContextCompat.getColor(context, R.color.backgroundDarkColor)); // add method to change images
        LayoutAdapter.setTextColors(infos, ContextCompat.getColor(context, R.color.darkTextColor));

        editor.apply();
    }


    public DemonstrationFragment() {

    }

    @Override
    public void setParameters() {
        Bundle bundle = getArguments();

        inputDescription = view.findViewById(R.id.inputDescription);
        outputDescription = view.findViewById(R.id.outputDescription);

        inputDemonstration = view.findViewById(R.id.inputDemonstration);
        outputDemonstration = view.findViewById(R.id.outputDemonstration);

        infos = new TextView[]{inputDescription, outputDescription, inputDemonstration, outputDemonstration};

        setContext(getActivity());
        setDescriptions(bundle);
        setDemonstrations(Enumerations.Demonstration.valueOf(bundle.getString(Handler.getCipherDemonstrationKey()))); // using enums w/string to get designated match. Go enums!
    }

    public void setDescriptions(Bundle bundle) {
        inputDescription.setText(bundle.getString(Handler.getInputDescriptionKey()));
        outputDescription.setText(bundle.getString(Handler.getOutputDescriptionKey()));
    }

    protected void setDemonstrationText(String inputText, String outputText) {
        inputDemonstration.setText(inputText);
        outputDemonstration.setText(outputText);
    }

    public void setDemonstrations(Enumerations.Demonstration key) {
        resources = getResources();
        switch(key) {
            case CaesarDemonstration:
                setDemonstrationText(resources.getString(R.string.caesar_input_example), resources.getString(R.string.caesar_output_example));
                break;
            case VigenereDemonstration:
                setDemonstrationText(resources.getString(R.string.vigenere_input_example), resources.getString(R.string.vigenere_output_example));
                break;
            case AtbashDemonstration:
                setDemonstrationText(resources.getString(R.string.atbash_input_example), resources.getString(R.string.atbash_output_example));
                break;
            case PolybiusDemonstration:
                setDemonstrationText(resources.getString(R.string.polybius_input_example), resources.getString(R.string.polybius_output_example));
                break;
            case A1Z26Demonstration:
                setDemonstrationText(resources.getString(R.string.a1z26_input_example), resources.getString(R.string.a1z26_output_example));
                break;
            case PlayfairDemonstration:
                setDemonstrationText(resources.getString(R.string.playfair_input_example), resources.getString(R.string.playfair_output_example));
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cipher_demonstration, container, false);
        setParameters();

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }
}

package ui.ui_visual;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;

import ui.ui_core.FragmentPageAdapter;
import ui.ui_core.VisibilityFragment;

public class DemonstrationFragment extends VisibilityFragment {

    protected TextView inputDescription;
    protected TextView outputDescription;
    protected EditText inputDemonstration;
    protected EditText outputDemonstration;

    public TextView getInputDescription() {return inputDescription;}
    public TextView getOutputDescription() {return outputDescription;}
    public EditText getInputEditText() {return inputDemonstration;}
    public EditText getOutputEditText() {return outputDemonstration;}

    public DemonstrationFragment() {

    }

    public void setParameters(View view) {
        Bundle bundle = getArguments();

        inputDescription = view.findViewById(R.id.inputDescription);
        outputDescription = view.findViewById(R.id.outputDescription);

        inputDemonstration = view.findViewById(R.id.inputDemonstration);
        outputDemonstration = view.findViewById(R.id.outputDemonstration);

        setDescriptions(bundle);
        setDemonstrations(bundle.getString(FragmentPageAdapter.getCipherDemonstrationKey()));
    }

    public void setDescriptions(Bundle bundle) {
        inputDescription.setText(bundle.getString(FragmentPageAdapter.getInputDescriptionKey()));
        outputDescription.setText(bundle.getString(FragmentPageAdapter.getOutputDescriptionKey()));
    }

    protected void setDemonstrationText(String inputText, String outputText) {
        inputDemonstration.setText(inputText);
        outputDemonstration.setText(outputText);
    }

    public void setDemonstrations(String key) { // default is false
        Resources resources = getResources();
        switch(key) {
            case "CaesarDemonstration":
                setDemonstrationText(resources.getString(R.string.caesar_input_example), resources.getString(R.string.caesar_output_example));
                break;
            case "VigenereDemonstration":
                setDemonstrationText(resources.getString(R.string.vigenere_input_example), resources.getString(R.string.vigenere_output_example));
                break;
            case "AtbashDemonstration":
                setDemonstrationText(resources.getString(R.string.atbash_input_example), resources.getString(R.string.atbash_output_example));
                break;
            case "PolybiusDemonstration":
                setDemonstrationText(resources.getString(R.string.polybius_input_example), resources.getString(R.string.polybius_output_example));
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

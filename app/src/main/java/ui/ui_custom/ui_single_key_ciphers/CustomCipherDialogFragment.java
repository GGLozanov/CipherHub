package ui.ui_custom.ui_single_key_ciphers;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cipherhub.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class CustomCipherDialogFragment extends DialogFragment {

    public interface OnInputSelected {
        void sendInput(String input);
    }

    OnInputSelected onInputSelected;

    private String titleText, editText, positiveBtnText, negativeBtnText; //class properties holders for texts for elements

    protected EditText input;
    protected TextView title;
    protected TextView submit, cancel;


    public CustomCipherDialogFragment(String title, String editText, String positiveBtnText, String negativeBtnText) {
        titleText = title;
        this.editText = editText;
        this.positiveBtnText = positiveBtnText;
        this.negativeBtnText = negativeBtnText;
    }

    protected void setParameters(View view) {
        title = view.findViewById(R.id.CustomCipherHeading);
        submit = view.findViewById(R.id.CustomCipherOk);
        cancel = view.findViewById(R.id.CustomCipherCancel);
        input = view.findViewById(R.id.CustomCipherInput);

        title.setText(titleText);
        input.setHint(editText);
        submit.setText(positiveBtnText);
        cancel.setText(negativeBtnText);

        input.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    @Override @NonNull
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // Resources resources = getActivity().getResources();
        View view = inflater.inflate(R.layout.fragment_custom_cipher_dialog, container, false);
        //center EditText input by using setGravity method. Gravity of a text is its alignment.

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); // set the window to transparent
        view.findViewById(R.id.customCipherDialogLayout).setBackgroundResource(R.drawable.dialog_background);

        setParameters(view);

        return view;
    }


    @Override
    public void onAttach(Context context) {  //method to be executed when a fragment is connected to an activity (attached). First in the lifecycle.
        super.onAttach(context);
        try {
            onInputSelected = (OnInputSelected) getTargetFragment(); //getTargetFragment() returns the current fragment set by the dialog
        } catch(ClassCastException e) {
            Log.e("Tag", "Failed to cast class" + e.getMessage());
        }
    }

}

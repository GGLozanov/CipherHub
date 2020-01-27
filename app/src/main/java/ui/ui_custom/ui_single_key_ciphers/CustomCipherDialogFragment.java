package ui.ui_custom.ui_single_key_ciphers;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import ui.ui_core.VisibilityDialogFragment;
import ui.ui_core.VisibilityFragment;

/**
 * A simple {@link Fragment} subclass.
 */

public class CustomCipherDialogFragment extends VisibilityDialogFragment implements SetVisibilityModes, VisibilityDialogFragment.Setup {

    public interface OnInputSelected {
        void sendInput(String input);

        void sendInput(String input, String delim);
    }

    OnInputSelected onInputSelected;

    private String titleText, editText, positiveBtnText, negativeBtnText; //class properties holders for texts for elements

    protected EditText input;
    protected TextView title;
    protected TextView submit, cancel;

    private TextView[] IOs;

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false);

        layoutAdapter = new LayoutAdapter((LinearLayout) view.findViewById(R.id.customCipherDialogLayout));
        layoutAdapter.setDialogLayoutBackroundResource(R.drawable.dialog_light_background);
        LayoutAdapter.setTextColors(IOs, ContextCompat.getColor(context, R.color.lightTextColor));

        editor.apply();
    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), true);

        layoutAdapter = new LayoutAdapter((LinearLayout) view.findViewById(R.id.customCipherDialogLayout));
        layoutAdapter.setDialogLayoutBackroundResource(R.drawable.dialog_dark_background);
        LayoutAdapter.setTextColors(IOs, ContextCompat.getColor(context, R.color.darkTextColor));

        editor.apply();
    }

    public CustomCipherDialogFragment() {

    }

    public CustomCipherDialogFragment(String title, String editText, String positiveBtnText, String negativeBtnText) {
        titleText = title;
        this.editText = editText;
        this.positiveBtnText = positiveBtnText;
        this.negativeBtnText = negativeBtnText;
    }

    @Override
    public void setParameters() {
        title = view.findViewById(R.id.CustomCipherHeading);
        submit = view.findViewById(R.id.CustomCipherOk);
        cancel = view.findViewById(R.id.CustomCipherCancel);
        input = view.findViewById(R.id.CustomCipherInput);

        IOs = new TextView[]{input, title};

        setContext(getActivity());

        title.setText(titleText);
        input.setHint(editText);
        submit.setText(positiveBtnText);
        cancel.setText(negativeBtnText);

        input.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    @Override @NonNull
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_custom_cipher_dialog, container, false);
        //center EditText input by using setGravity method. Gravity of a text is its alignment.

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); // set the window to transparent as to not see the rest of the corner

        setParameters();

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }


    @Override
    public void onAttach(Context context) {  // method to be executed when a fragment is connected to an activity (attached). First in the lifecycle.
        super.onAttach(context);
        try {
            onInputSelected = (OnInputSelected) getTargetFragment(); // getTargetFragment() returns the current fragment set by the dialog
        } catch(ClassCastException e) {
            Log.e("Tag", "Failed to cast class" + e.getMessage());
        }
    }

}

package ui;

import android.content.Context;
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
import android.widget.Toast;

import com.example.cipherhub.R;

import ciphers.ASCIIUtils;

/**
 * A simple {@link Fragment} subclass.
 */

public class CustomCaesarDialogFragment extends DialogFragment {

    private String caesarShiftAmount;

    public interface OnInputSelected {
        void sendInput(String input);
    }

    OnInputSelected onInputSelected;

    private ASCIIUtils asciiUtils = new ASCIIUtils();


    private EditText input;

    public CustomCaesarDialogFragment() {
        // Required empty public constructor
    }

    @Override @NonNull
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView submit, cancel;

        // Resources resources = getActivity().getResources();
        View view = inflater.inflate(R.layout.fragment_custom_caesar_dialog , container, false);

        submit = view.findViewById(R.id.CustomCaesarOk);
        cancel = view.findViewById(R.id.CustomCaesarCancel);
        input = view.findViewById(R.id.CustomCaesarInput);

        input.setGravity(Gravity.CENTER_HORIZONTAL); //center EditText input by using setGravity method. Gravity of a text is its alignment.

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caesarShiftAmount = input.getText().toString();
                if(!caesarShiftAmount.equals("") && Integer.parseInt(caesarShiftAmount) <= 26) { //if the string isn't empty, we send the data to the caesar fragment
                    onInputSelected.sendInput(caesarShiftAmount); //send the input through the implementation of the method in CustomCaesarFragment
                    getDialog().dismiss(); //dismiss the dialog at the end

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid input! Key must not exceed 26!", Toast.LENGTH_SHORT).show();
                    //create a Toast (short little feedback message) to throw when the arguments are null; we use chaining and we show() it at the end
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getDialog().dismiss(); //dismiss our current dialog
           }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputSelected = (OnInputSelected) getTargetFragment(); //getTargetFragment() returns the current fragment set by the dialog
        } catch(ClassCastException e) {
            Log.e("Tag", "Failed to cast class" + e.getMessage());
        }
    }

}

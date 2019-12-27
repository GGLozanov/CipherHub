package ui.ui_custom.ui_single_key_ciphers;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cipherhub.R;

public class CustomCaesarDialogFragment extends CustomCipherDialogFragment {

    private String caesarShiftAmount;


    public CustomCaesarDialogFragment(String title, String editText, String positiveBtnText, String negativeBtnText) {
        super(title, editText, positiveBtnText, negativeBtnText);
    }

    private void configInput() { // method for configurating our input EditText with an InputFilter arr to limit the max length programmatically (as well as input type)
        input.setInputType(InputType.TYPE_CLASS_NUMBER); // set editText input type to numbers
        input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)}); // define unnamed InputFilter's LengthFilter subclass with a value of 3 max numbers
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState); //call parent implementation of onCreateView (sets up all the other params)

        configInput();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caesarShiftAmount = input.getText().toString();
                if(!caesarShiftAmount.equals("") && Integer.parseInt(caesarShiftAmount) <= 26) { //if the string isn't empty, we send the data to the caesar fragment
                    onInputSelected.sendInput(caesarShiftAmount); //send the input through the implementation of the method in CustomCipherFragment
                    getDialog().dismiss(); //dismiss the dialog at the end

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), caesarShiftAmount.equals("") ? "Invalid input! Key must not be empty!" : "Invalid input! Key must not exceed 26!", Toast.LENGTH_SHORT).show();
                    // create a Toast (short little feedback message) to throw when the arguments are null or exceed the limit;
                    // Toast.LENGTH_SHORT is a static integer property (macro) for the duration of the Toast obj (2 seconds).
                    // we use chaining and we show() it at the end.
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
}

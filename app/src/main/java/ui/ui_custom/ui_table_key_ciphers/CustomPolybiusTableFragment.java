package ui.ui_custom.ui_table_key_ciphers;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cipherhub.R;
import android.util.Log;

import java.util.*;

public class CustomPolybiusTableFragment extends DialogFragment {

    // 1st page -> 0 - 11th index
    // 2nd page -> 12th - 23rd index
    // 3rd page -> 24th - 36th index

    // each page -> either 0, +12, or +24

    final private String idFormat = "Field";
    static private int referralNum;

    final static Integer[] offsets = {0, 12, 24};

    static Character[][] Square; // initialise square by last square taken by custom polybius fragment

    public CustomPolybiusTableFragment(int referralNum) { // constructor takes integer key for which referral dialog has instantiated it
        Square = CustomPolybiusFragment.getPolybiusKey();
        CustomPolybiusTableFragment.referralNum = referralNum;
    }

    private void initTable(View view) {
        initEditTexts(view, offsets[referralNum - 1]); // 0 -> 24 offset
        setListeners(view, offsets[referralNum - 1]);
    }

    private void setListeners(final View view, final int offset) {
        TextView submit = view.findViewById(R.id.TableInputOk);
        TextView cancel = view.findViewById(R.id.TableInputCancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentEditTextValue; // variable to hold currentEditText's text
                int columns = 0, rows = (offset / 6);
                for(Integer count = 1; count < 13; count++) {
                    final EditText currentEditText = getEditTextByName(view, count);
                    currentEditTextValue = currentEditText.getText().toString();

                    if(columns == 6) {
                        rows++;
                        columns = 0;
                    }

                    if(!currentEditTextValue.equals("")) { // may need to implement more error checks (cyrillic for ex.)
                        Square[rows][columns++] = currentEditTextValue.charAt(0);
                        // CustomPolybiusFragment.setCharacterInPolybiusKey(currentEditTextValue.charAt(0), (count - 1) / 5, count);
                        // (count/6) - 1 -> round up after we divide to find row (and -1 to fit array indexing)
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Invalid table input! Fields must not be empty!", Toast.LENGTH_SHORT).show();
                    }
                }

                getDialog().dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private EditText getEditTextByName(View view, Integer count) { // method to get EditText by its position.
        final Resources resources = getResources();
        return view.findViewById(resources.getIdentifier(idFormat + count.toString(), "id", getActivity().getPackageName()));
    }

    private void initEditTexts(View view, Integer offset) { // method to initialise all editTexts in a layout programmatically
        EditText currentEditText;
        char currentEditCharacter;
        Integer position;
        String tempHolder;
        int rows = (offset / 6), columns = 0; // variable to keep track of columns and rows (WAY EASIER)
        for(Integer count = 1; count < 13; count++) {
            tempHolder = "";

            if(columns == 6) {
                rows++;
                columns = 0;
            }

            // id format: "Field" + <integer> (count) -> find id by name with such format and use to manipulate editText
            // ex: id name = "Field1". EditText1 ==> "Field" + 1.toString() = "Field1"
            currentEditText = getEditTextByName(view, count);
            position = (count + offset);
            currentEditText.setHint(position.toString());
            currentEditText.setHintTextColor(0xB3cccccc); // 0x -> designate hex
            currentEditCharacter = Square[rows][columns++]; // read from matrix and set character from square
            tempHolder += currentEditCharacter;
            // increment the current (index + offset) with 64 to generate the capital letter character for the editText
            // typecast to fit into Character class and setText() method
            currentEditText.setText(tempHolder);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_polybius_input_dialog, container, false);

        initTable(view);

        return view;
    }
}

package ui.ui_custom.ui_table_key_ciphers;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.cipherhub.Activity;
import com.example.cipherhub.R;
import com.example.cipherhub.SetVisibilityModes;

import adapters.LayoutAdapter;
import ui.ui_core.VisibilityDialogFragment;
import ui.ui_core.VisibilityFragment;

public class CustomPolybiusTableFragment extends VisibilityDialogFragment implements SetVisibilityModes, VisibilityFragment.Setup {

    // 1st page -> 0 - 11th index
    // 2nd page -> 12th - 23rd index
    // 3rd page -> 24th - 36th index

    // each page -> either 0, +12, or +24

    final private String idFormat = "Field";
    static private int referralNum;

    private final static Integer[] offsets = {0, 12, 24};

    Resources resources; // fix later

    static Character[][] Square; // initialise square by last square taken by custom polybius fragment

    @Override
    public void setLightTheme() {
        editor.putBoolean(Activity.getModeKey(), false);

        layoutAdapter = new LayoutAdapter((LinearLayout) view.findViewById(R.id.customPolybiusInputDialogLayout));
        layoutAdapter.setDialogLayoutBackroundResource(R.drawable.dialog_light_background);

        for(int i = 1; i < 13; i++) {
            LayoutAdapter.setTextColor(getEditTextByName(i), ContextCompat.getColor(context, R.color.lightTextColor));
        }

        editor.apply();

    }

    @Override
    public void setDarkTheme() {
        editor.putBoolean(Activity.getModeKey(), false);

        layoutAdapter = new LayoutAdapter((LinearLayout) view.findViewById(R.id.customPolybiusInputDialogLayout));
        layoutAdapter.setDialogLayoutBackroundResource(R.drawable.dialog_dark_background);

        for(int i = 1; i < 13; i++) {
            LayoutAdapter.setTextColor(getEditTextByName(i), ContextCompat.getColor(context, R.color.lightTextColor));
        }

        editor.apply();

    }

    public CustomPolybiusTableFragment(int referralNum) { // constructor takes integer key for which referral dialog has instantiated it
        Square = CustomPolybiusFragment.getPolybiusKey();
        CustomPolybiusTableFragment.referralNum = referralNum;
    }

    @Override
    public void setParameters() {
        resources = getResources();
        setContext(getActivity());
        initEditTexts(offsets[referralNum - 1]); // 0 -> 24 offset
        setListeners(offsets[referralNum - 1]);
    }

    private void setListeners(final int offset) {
        TextView submit = view.findViewById(R.id.TableInputOk);
        TextView cancel = view.findViewById(R.id.TableInputCancel);

        submit.setOnClickListener((View v) -> {
                String currentEditTextValue; // variable to hold currentEditText's text
                int columns = 0, rows = (offset / 6);
                for(Integer count = 1; count < 13; count++) {
                    final EditText currentEditText = getEditTextByName(count);
                    currentEditTextValue = currentEditText.getText().toString();

                    if(columns == 6) {
                        rows++;
                        columns = 0;
                    }

                    if(!currentEditTextValue.equals("")) { // may need to implement more error checks (cyrillic for ex.)
                        Square[rows][columns++] = currentEditTextValue.charAt(0);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Invalid table input! Fields must not be empty!", Toast.LENGTH_SHORT).show();
                    }
                }

                getDialog().dismiss();
        });

        cancel.setOnClickListener((View v) -> getDialog().dismiss());
    }

    private EditText getEditTextByName(Integer count) { // method to get EditText by its position.
        return view.findViewById(resources.getIdentifier(idFormat + count.toString(), "id", getActivity().getPackageName()));
    }

    private EditText[] getAllEditTextsByName(Integer count) {
        EditText[] editTexts = new EditText[count];

        for(Integer i = 1; i < count; i++) {
            editTexts[i] = getEditTextByName(i);
        }

        return editTexts;
    }

    private void initEditTexts(Integer offset) { // method to initialise all editTexts in a layout programmatically
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
            currentEditText = getEditTextByName(count);
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
        view = inflater.inflate(R.layout.fragment_custom_polybius_input_dialog, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); // set the window to transparent

        setParameters(); // inits table

        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();

        return view;
    }
}

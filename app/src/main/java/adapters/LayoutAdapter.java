package adapters;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.cipherhub.R;

import org.w3c.dom.Text;

public class LayoutAdapter {
    private ConstraintLayout frameLayout;
    private LinearLayout dialogLayout;

    public LayoutAdapter(ConstraintLayout frameLayout, LinearLayout dialogLayout) {
        this.frameLayout = frameLayout;
        this.dialogLayout = dialogLayout;
    }

    public LayoutAdapter(ConstraintLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    public LayoutAdapter(LinearLayout dialogLayout) {
        this.dialogLayout = dialogLayout;
    }

    public ConstraintLayout getFrameLayout() {return frameLayout;}
    public LinearLayout getDialogLayout() {return dialogLayout;}

    public void setFrameLayout(ConstraintLayout frameLayout) {this.frameLayout = frameLayout;}
    public void setDialogLayout(LinearLayout dialogLayout) {this.dialogLayout = dialogLayout;}

    public void setFrameLayoutBackgroundColor(int hexColor) {
        frameLayout.setBackgroundColor(hexColor); // static method to parse color
    }

    public void setDialogLayoutBackgroundColor(int hexColor) {
        dialogLayout.setBackgroundColor(hexColor);
    }

    static public<T extends TextView> void setTextColors(T[] visualElements, int hexColor) {
        for(T visualElement : visualElements) {
            visualElement.setTextColor(hexColor);
        }
    }

    static public<T extends TextView> void setTextColor(T visualElement, int hexColor) {
        visualElement.setTextColor(hexColor);
    }

    public void setButtonsLightMode(Button[] buttons) {
        for(Button button : buttons) { // for each button in buttons
            button.setBackgroundResource(R.drawable.button_light_background);
        }
    }

    public void setButtonsLightMode(Button[] buttons, @NonNull Context context) {
        LayoutAdapter.setTextColors(buttons, ContextCompat.getColor(context, R.color.lightTextColor));
        for(Button button : buttons) { // for each button in buttons
            button.setBackgroundResource(R.drawable.button_light_background);
        }
    }

    public void setButtonsDarkMode(Button[] buttons, @NonNull Context context) {
        LayoutAdapter.setTextColors(buttons, ContextCompat.getColor(context, R.color.darkTextColor));
        for(Button button : buttons) { // for each button in buttons
            button.setBackgroundResource(R.drawable.button_dark_background);
        }
    }

    public void setButtonsDarkMode(Button[] buttons) {
        for(Button button : buttons) { // for each button in buttons
            button.setBackgroundResource(R.drawable.button_dark_background);
        }
    }

    public static void setToolbarBackgroundColor(Toolbar toolbar, int hexColor) {
        toolbar.setBackgroundColor(hexColor);
    }

    public void setTextViewsLightModeResource(TextView[] textViews) {
        for(TextView textView : textViews) {
            textView.setBackgroundResource(R.drawable.dialog_light_button_background); // buttons act akin to TextViews here
        }
    }

    public void setTextViewsLightModeResource(TextView[] textViews, @NonNull Context context) {
        LayoutAdapter.setTextColors(textViews, ContextCompat.getColor(context, R.color.lightTextColor));
        for(TextView textView : textViews) {
            textView.setBackgroundResource(R.drawable.dialog_light_button_background); // buttons act akin to TextViews here
        }
    }


    public void setTextViewsDarkModeResource(TextView[] textViews) {
        for(TextView textView : textViews) {
            textView.setBackgroundResource(R.drawable.dialog_dark_button_background); // buttons act akin to TextViews here
        }
    }

    public void setTextViewsDarkModeResource(TextView[] textViews, @NonNull Context context) {
        LayoutAdapter.setTextColors(textViews, ContextCompat.getColor(context, R.color.darkTextColor));
        for(TextView textView : textViews) {
            textView.setBackgroundResource(R.drawable.dialog_dark_button_background); // buttons act akin to TextViews here
        }
    }


    public void setDialogLayoutBackroundResource(int resourceID) {
        dialogLayout.setBackgroundResource(resourceID);
    }

}

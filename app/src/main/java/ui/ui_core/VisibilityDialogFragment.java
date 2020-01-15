package ui.ui_core;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.example.cipherhub.Activity;

import adapters.LayoutAdapter;

public class VisibilityDialogFragment extends DialogFragment {
    protected SharedPreferences.Editor editor = Activity.getEditor();

    protected View view = getView(); // temp View variable to keep track of last fragment view

    protected Context context;

    protected LayoutAdapter layoutAdapter;

    public SharedPreferences.Editor getFragmentPrefEditor() {return editor;}

    public View getFragmentView() {return view;}

    public void setContext(Context context) {this.context = context;}

    public interface Setup {
        void setParameters();
    }
}

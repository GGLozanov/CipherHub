package ui.ui_core;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cipherhub.Activity;

import adapters.LayoutAdapter;
import managers.CipherCallerManager;

public class VisibilityFragment extends Fragment { // class for all fragments affected by visibility modes (light/dark) => all of them; may overload onCreate() method
    protected SharedPreferences.Editor editor = Activity.getEditor();

    protected View view; // temp View variable to keep track of last fragment view

    protected Context context;

    protected LayoutAdapter layoutAdapter;

    public SharedPreferences.Editor getFragmentPrefEditor() {return editor;}

    public View getFragmentView() {return view;}

    public void setContext(Context context) {this.context = context;}

    public interface Setup {
        void setParameters();
    }
}

package com.example.cipherhub;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import adapters.LayoutAdapter;

public abstract class Activity extends AppCompatActivity { // abstract superclass for all activities

    protected static SharedPreferences sharedPreferences;
    protected static SharedPreferences.Editor editor; // reference static subclass

    protected static final String modeKey = "Mode";

    public static String getModeKey() {return modeKey;}

    @NonNull
    public static SharedPreferences getSharedPreferences() {return sharedPreferences;}

    @NonNull
    public static SharedPreferences.Editor getEditor() {return editor;}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu); // Menu inflater takes menu resource and Menu as an argument and adapts it to a view (i.e. inflates it)
        return true;
    }

    // Change layout background colours for:
    // 1) Additional vigenere fragment
    // 2) Cipher fragment
    // 3) Custom Cipher fragment -> button colours
    // 4) Custom Cipher dialog fragment -> dialog background (drawable) -> done
    // 5) Polybius Input dialog fragment
    // 6) Key Cipher fragment

    // 2 Layouts -> Constraint and Linear
    // Button -> change the resource drawable for background

    // SharedPreferences: Class that allows storage of information beyond the lifecycle of activities or fragments -- beyond the lifecycle of the running app itself
    // SharedPreferences is initialised through getSharedPreferences()
    // SharedPreferences uses a key-value style of storage for information and can be retrieved with get[Type]() methods (String, Integer, etc.)
    // Changes to SharedPreferences always, ALWAYS go through an Editor object first (getEditor()), which has put[Type]() methods
    // Changes, whatever they may be, must be committed by using the commit() method

    public static boolean getMode() { // get current mode from preferences
        return sharedPreferences.getBoolean(modeKey, true);
    }

    public void configureToolbar() { // all activities have toolbars => parent class method

        Toolbar toolbar = findViewById(R.id.toolbar); // get Toolbar widget; can't be done in parent class because findViewById() uses view and Activity doesn't have one set
        setSupportActionBar(toolbar); // set it as supporting action bar

        if(Activity.getMode()) LayoutAdapter.setToolbarBackgroundColor(toolbar, ContextCompat.getColor(this, R.color.colorDarkPrimary)); // set color of toolbar here
        else LayoutAdapter.setToolbarBackgroundColor(toolbar, ContextCompat.getColor(this, R.color.colorLightPrimary));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call AppCompatActivity implementation of onCreate()

        sharedPreferences = getApplicationContext().getSharedPreferences("CustomPrefs", Context.MODE_PRIVATE);
        // Initialisation through first getting the context of the application -> calling the getSharedPreferences() method with filename of preferences and mode
        // Context.MODE_PRIVATE macro means the file can only be accessed by the calling application
        editor = sharedPreferences.edit(); // initialise through edit() method of current sharedprefs
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(Activity.getMode()) setTheme(R.style.DarkAppTheme); // configure toolbar theme
        else setTheme(R.style.LightAppTheme);

        switch(item.getItemId()) {
            case R.id.actionChangeMode: // pressed change mode
                return true; // stop the function here if we have selected the id of a menu option
            case R.id.lightMode:
                editor.putBoolean(modeKey, false);
                editor.apply();
                return true;
            case R.id.darkMode:
                editor.putBoolean(modeKey, true);
                editor.apply();
                return true;
            case R.id.actionUITemplate:
                return true;
            default: return super.onOptionsItemSelected(item); // otherwise call the parent implementation
        }
    }
}

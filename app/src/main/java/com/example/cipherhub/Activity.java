package com.example.cipherhub;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import adapters.LayoutAdapter;
import ui.ui_core.CipherFragment;
import ui.ui_core.KeyCipherFragment;
import ui.ui_core.QuadrupButtonFragment;
import ui.ui_core.SectionFragment;
import ui.ui_custom.ui_single_key_ciphers.CustomAtbashFragment;
import ui.ui_custom.ui_single_key_ciphers.CustomCaesarFragment;
import ui.ui_custom.ui_single_key_ciphers.CustomCipherFragment;
import ui.ui_custom.ui_table_key_ciphers.AdditionalVigenereFragment;
import ui.ui_custom.ui_table_key_ciphers.CustomPolybiusFragment;
import ui.ui_visual.CipherDemonstrationFragment;
import ui.ui_visual.CipherVisualisationFragment;
import ui.ui_visual.KeyCipherDemonstrationFragment;

public abstract class Activity extends AppCompatActivity { // abstract superclass for all activities

    List<LinkedHashMap<Fragment, String>> fragmentCollection = new ArrayList<>(); // linked HashMap keeps track of insertion order3

    private void addFragments(Fragment[] fragments, String[] keys) {
        if(fragments.length != keys.length) return;

        LinkedHashMap<Fragment, String> fragmentMap = new LinkedHashMap<>();

        for(int fragment = 0; fragment < fragments.length; fragment++) {
            fragmentMap.put(fragments[fragment], keys[fragment]);
        }

        fragmentCollection.add(fragmentMap); // maybe add optimisation with Arrays.aslist()?
    }

    private void initFragmentMap() {
        // create separate variable for insertion in hashmap due to the dangers of double-brace initialisation (memory leaks!)

        switch(sharedPreferences.getString(themeKey, "Default")) {
            case "Minimalist":
                // Main Activity

                addFragments(new Fragment[]{new SectionFragment(), new QuadrupButtonFragment()}, new String[]{"Main Screen", "Page 1"});

                // Caesar Cipher

                addFragments(new Fragment[]{new CipherFragment(), new CustomCaesarFragment()}, new String[]{"Caesar Cipher", "Custom Caesar"});

                // Vigenere Cipher

                addFragments(new Fragment[]{new KeyCipherFragment(), new AdditionalVigenereFragment()}, new String[]{"Vigenere Cipher", "Custom Vigenere"});

                // Atbash Cipher

                addFragments(new Fragment[]{new CipherFragment(), new CustomAtbashFragment()}, new String[]{"Atbash Cipher", "Custom Atbash"});

                // Polybius Cipher

                addFragments(new Fragment[]{new CipherFragment(), new CustomPolybiusFragment()}, new String[]{"Polybius Cipher", "Custom Polybius"});

                break;

            default: // for default layout
                // Main Activity

                addFragments(new Fragment[]{new SectionFragment(), new QuadrupButtonFragment()}, new String[]{"Main Screen", "Page 1"});

                // Caesar Cipher

                addFragments(new Fragment[]{new SectionFragment(), new CipherFragment(), new CustomCaesarFragment()}, new String[]{"Caesar Screen", "Caesar Cipher", "Custom Caesar"});

                // Vigenere Cipher

                addFragments(new Fragment[]{new SectionFragment(), new KeyCipherFragment(), new AdditionalVigenereFragment()}, new String[]{"Vigenere Screen", "Vigenere Cipher", "Custom Vigenere"});

                // Atbash Cipher

                addFragments(new Fragment[]{new SectionFragment(), new CipherFragment(), new CustomAtbashFragment()}, new String[]{"Atbash Screen", "Atbash Cipher", "Custom Atbash"});

                // Polybius Cipher

                addFragments(new Fragment[]{new SectionFragment(), new CipherFragment(), new CustomPolybiusFragment()}, new String[]{"Polybius Screen", "Polybius Cipher", "Custom Polybius"});

                break;
        }

    }

    protected static SharedPreferences sharedPreferences;
    protected static SharedPreferences.Editor editor; // reference static subclass

    protected static final String modeKey = "Mode";
    protected static final String themeKey = "UITheme";

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

    private void applyLightTheme() {
        for(LinkedHashMap<Fragment, String> map : fragmentCollection) {
            for(Fragment fragment : map.keySet()) {
                if(fragment instanceof CipherFragment && ((CipherFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((CipherFragment) fragment).setLightTheme();
                } else if(fragment instanceof KeyCipherFragment && ((KeyCipherFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((KeyCipherFragment) fragment).setLightTheme();
                } else if(fragment instanceof QuadrupButtonFragment && ((QuadrupButtonFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((QuadrupButtonFragment) fragment).setLightTheme();
                } else if(fragment instanceof SectionFragment && ((SectionFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((SectionFragment) fragment).setLightTheme();
                } else if(fragment instanceof CustomCipherFragment && ((CustomCipherFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((CustomCipherFragment) fragment).setLightTheme();
                } else if(fragment instanceof AdditionalVigenereFragment && ((AdditionalVigenereFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((AdditionalVigenereFragment) fragment).setLightTheme();
                } else if(fragment instanceof CipherDemonstrationFragment && ((CipherDemonstrationFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((CipherDemonstrationFragment) fragment).setLightTheme();
                } else if(fragment instanceof CipherVisualisationFragment && ((CipherVisualisationFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((CipherVisualisationFragment) fragment).setLightTheme();
                } else if(fragment instanceof KeyCipherDemonstrationFragment && ((KeyCipherDemonstrationFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((KeyCipherDemonstrationFragment) fragment).setLightTheme();
                }
            }
        }

        configureToolbar();
    }

    private void applyDarkTheme() {
        for(LinkedHashMap<Fragment, String> map : fragmentCollection) {
            for(Fragment fragment : map.keySet()) {
                if(fragment instanceof CipherFragment && ((CipherFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((CipherFragment) fragment).setDarkTheme();
                } else if(fragment instanceof KeyCipherFragment && ((KeyCipherFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((KeyCipherFragment) fragment).setDarkTheme();
                } else if(fragment instanceof QuadrupButtonFragment && ((QuadrupButtonFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((QuadrupButtonFragment) fragment).setDarkTheme();
                } else if(fragment instanceof SectionFragment && ((SectionFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((SectionFragment) fragment).setDarkTheme();
                } else if(fragment instanceof CustomCipherFragment && ((CustomCipherFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((CustomCipherFragment) fragment).setDarkTheme();
                } else if(fragment instanceof AdditionalVigenereFragment && ((AdditionalVigenereFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((AdditionalVigenereFragment) fragment).setDarkTheme();
                } else if(fragment instanceof CipherDemonstrationFragment && ((CipherDemonstrationFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((CipherDemonstrationFragment) fragment).setDarkTheme();
                } else if(fragment instanceof CipherVisualisationFragment && ((CipherVisualisationFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((CipherVisualisationFragment) fragment).setDarkTheme();
                } else if(fragment instanceof KeyCipherDemonstrationFragment && ((KeyCipherDemonstrationFragment) fragment).getFragmentView() != null && fragment.getActivity() != null) {
                    ((KeyCipherDemonstrationFragment) fragment).setDarkTheme();
                }
            }
        }

        configureToolbar();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call AppCompatActivity implementation of onCreate()

        sharedPreferences = getApplicationContext().getSharedPreferences("CustomPrefs", Context.MODE_PRIVATE);

        // Initialisation through first getting the context of the application -> calling the getSharedPreferences() method with filename of preferences and mode
        // Context.MODE_PRIVATE macro means the file can only be accessed by the calling application

        editor = sharedPreferences.edit(); // initialise through edit() method of current sharedprefs
        editor.apply();

        initFragmentMap();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(Activity.getMode()) setTheme(R.style.DarkAppTheme); // configure toolbar theme
        else setTheme(R.style.LightAppTheme);

        switch(item.getItemId()) {
            case R.id.actionChangeMode: // pressed change mode
                return true; // stop the function here if we have selected the id of a menu option
            case R.id.lightMode:
                editor.putBoolean(modeKey, false).apply();
                applyLightTheme();
                Toast.makeText(this, "Light theme applied!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.darkMode:
                editor.putBoolean(modeKey, true).apply();
                applyDarkTheme();
                Toast.makeText(this, "Dark theme applied!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.actionUITemplate:
                return true;
            case R.id.Default:
                editor.putString(themeKey, "Default").apply();
                Toast.makeText(this, "Default layout applied on restart!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Novice:
                editor.putString(themeKey, "Novice").apply();
                Toast.makeText(this, "Novice layout will be applied on restart!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Minimalist:
                editor.putString(themeKey, "Minimalist").apply();
                Toast.makeText(this, "Minimalist layout will be applied on restart!", Toast.LENGTH_SHORT).show();
                return true;
            default: return super.onOptionsItemSelected(item); // otherwise call the parent implementation
        }
    }
}

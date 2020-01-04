package ui.ui_core;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cipherhub.R;

import java.util.*;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>(); //list for both quadrup, cipher and section fragments (all fragments classes that extent Fragment)
    private List<String> fragmentTitles = new ArrayList<>(); //list for fragment titles by which to search in switch()

    static final String titleKey = "Title";
    static final String descriptionKey = "Description";

    static final String cipherKey = "Cipher";

    static final String resetKey = "Reset";

    static final String buttonOneKey = "ButtonOne";
    static final String buttonTwoKey = "ButtonTwo";
    static final String buttonThreeKey = "ButtonThree";
    static final String buttonFourKey = "ButtonFour";

    static final String inputDescriptionKey = "InputDescription";
    static final String outputDescriptionKey = "OutputDescription";

    static final String cipherkeyDescriptionKey = "KeyDescription";

    static final String cipherImageKey = "ImageKey"; // String key to designate which pair of images to use

    static final String visualisationKey = "Visualisation";

    public static String getTitleKey() {return titleKey;}
    public static String getDescriptionKey() {return descriptionKey;}

    public static String getCipherKey() {return cipherKey;}
    public static String getResetKey() {return resetKey;}

    public static String getButtonOneKey() {return buttonOneKey;}
    public static String getButtonTwoKey() {return buttonTwoKey;}
    public static String getButtonThreeKey() {return buttonThreeKey;}
    public static String getButtonFourKey() {return buttonFourKey;}

    public static String getInputDescriptionKey() {return inputDescriptionKey;}
    public static String getOutputDescriptionKey() {return outputDescriptionKey;}

    public static String getCipherkeyDescriptionKey() {return cipherkeyDescriptionKey;}

    public static String getCipherImageKey() {return cipherImageKey;}

    public static String getVisualisationKey() {return visualisationKey;}

    private Context context;

    public FragmentPageAdapter(FragmentManager fm, final Context context) {
        // call parent constructor for FragmentManager in order to override methods
        // also pass necessary context from instantiated FragmentPageAdapter to use for accessing the resources of the app
        super(fm);
        this.context = context;
    }

    public void addFragment(Fragment fragment, String title) { //add a given fragment method
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    public List<Fragment> getFragments() {return fragments;}

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        Resources appResources = context.getResources(); //variable for accessing the Resources of the app and to avoid multiple calls to the same method

        switch(fragmentTitles.get(position)) {
            case "Main Screen": //main activity
                bundle.putString(titleKey, appResources.getString(R.string.app_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.app_description));
                break;
            case "Caesar Screen": //caesar
                bundle.putString(titleKey, appResources.getString(R.string.caesar_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.caesar_description));
                break;
            case "Vigenere Screen": //vigenere
                bundle.putString(titleKey, appResources.getString(R.string.vigenere_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.vigenere_description));
                break;
            case "Atbash Screen": //atbash
                bundle.putString(titleKey, appResources.getString(R.string.atbash_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.atbash_description));
                break;
            case "Polybius Screen": //polybius
                bundle.putString(titleKey, appResources.getString(R.string.polybius_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.polybius_description));
                break;
            case "Page 1": //page 1 holds the 4 OG ciphers
                bundle.putString(buttonOneKey, appResources.getString(R.string.caesar));
                bundle.putString(buttonTwoKey, appResources.getString(R.string.vigenere));
                bundle.putString(buttonThreeKey, appResources.getString(R.string.atbash));
                bundle.putString(buttonFourKey, appResources.getString(R.string.polybius));
                break;
            case "Caesar Cipher":
                bundle.putString(cipherKey, appResources.getString(R.string.caesar));
                break;
            case "Vigenere Cipher":
                bundle.putString(cipherKey, appResources.getString(R.string.vigenere));
                break;
            case "Atbash Cipher":
                bundle.putString(cipherKey, appResources.getString(R.string.atbash));
                break;
            case "Polybius Cipher":
                bundle.putString(cipherKey, appResources.getString(R.string.polybius));
                break;
            case "Custom Caesar":
                bundle.putString(titleKey, context.getResources().getString(R.string.custom_caesar_title));
                bundle.putString(descriptionKey, context.getResources().getString(R.string.custom_caesar_description));
                bundle.putString(buttonOneKey, context.getResources().getString(R.string.custom_button));
                bundle.putString(resetKey, context.getResources().getString(R.string.custom_reset_button));
                break;
            case "Custom Atbash":
                bundle.putString(titleKey, context.getResources().getString(R.string.custom_atbash_title));
                bundle.putString(descriptionKey, context.getResources().getString(R.string.custom_atbash_description));
                bundle.putString(buttonOneKey, context.getResources().getString(R.string.custom_button));
                bundle.putString(resetKey, context.getResources().getString(R.string.custom_reset_button));
                break;
            case "Custom Polybius":
                bundle.putString(titleKey, appResources.getString(R.string.custom_polybius_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.custom_polybius_description));
                bundle.putString(buttonOneKey, appResources.getString(R.string.custom_button));
                bundle.putString(resetKey, appResources.getString(R.string.custom_reset_button));
                break;
            case "Caesar Demonstration":
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.caesar_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.caesar_output_description));
                bundle.putString(cipherImageKey, "CaesarDemonstrationImages");
                break;
            case "Vigenere Demonstration":
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.vigenere_input_description));
                bundle.putString(cipherkeyDescriptionKey, appResources.getString(R.string.vigenere_key_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.vigenere_output_description));
                bundle.putString(cipherImageKey, "VigenereDemonstrationImages");
                break;
            case "Atbash Demonstration":
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.atbash_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.atbash_output_description));
                bundle.putString(cipherImageKey, "AtbashDemonstrationImages");
                break;
            case "Polybius Demonstration":
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.polybius_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.polybius_output_description));
                bundle.putString(cipherImageKey, "PolybiusDemonstrationImages");
                break;
            case "Caesar Visualisation":
                bundle.putString(visualisationKey, appResources.getString(R.string.caesar_visualisation_description));
                bundle.putString(cipherImageKey, "CaesarVisualisationImage");
                break;
            case "Vigenere Visualisation":
                bundle.putString(visualisationKey, appResources.getString(R.string.vigenere_visualisation_description));
                bundle.putString(cipherImageKey, "VigenereVisualisationImage");
                break;
            case "Atbash Visualisation":
                bundle.putString(visualisationKey, appResources.getString(R.string.atbash_visualisation_description));
                bundle.putString(cipherImageKey, "AtbashVisualisationImage");
                break;
            case "Polybius Visualisation":
                bundle.putString(visualisationKey, appResources.getString(R.string.polybius_visualisation_description));
                bundle.putString(cipherImageKey, "PolybiusVisualisationImage");
                break;
            default:
                break;
        }

        fragments.get(position).setArguments(bundle);

        return fragments.get(position); //get the position of the current fragment from out fragment array list
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

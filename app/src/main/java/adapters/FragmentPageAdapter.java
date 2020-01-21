package adapters;

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

    private static final String titleKey = "Title"; // sectionFragment cipher title
    private static final String descriptionKey = "Description"; // sectionFragment cipher description

    private static final String cipherKey = "Cipher"; // Which Cipher layout to generate

    private static final String resetKey = "Reset"; // Reset button

    private static final String buttonOneKey = "ButtonOne"; // quadrup fragment for different pages
    private static final String buttonTwoKey = "ButtonTwo";
    private static final String buttonThreeKey = "ButtonThree";
    private static final String buttonFourKey = "ButtonFour";

    private static final String inputDescriptionKey = "DemonstrationInputDescription"; // which input description to use for which key/no-key cipher
    private static final String outputDescriptionKey = "DemonstrationOutputDescription"; // which output description to use for which key/no-key cipher

    private static final String cipherkeyDescriptionKey = "KeyDescription"; // which key description to use for which key cipher

    private static final String cipherDemonstrationKey = "Demonstration"; // which EditText pairs to use for ciphers when demonstrating


    private static final String visualisationKey = "Visualisation"; // which Cipher visualisation to choose
    private static final String visualisationImageKey = "VisualisationImage"; // which image to use for the visualisation cipher

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

    public static String getCipherDemonstrationKey() {return cipherDemonstrationKey;}


    public static String getVisualisationKey() {return visualisationKey;}
    public static String getVisualisationImageKey() {return visualisationImageKey;}

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
            case "Main Screen": // Main Activity
                bundle.putString(titleKey, appResources.getString(R.string.app_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.app_description));
                break;
            case "Caesar Screen": // Caesar
                bundle.putString(titleKey, appResources.getString(R.string.caesar_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.caesar_description));
                break;
            case "Vigenere Screen": // Vigenere
                bundle.putString(titleKey, appResources.getString(R.string.vigenere_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.vigenere_description));
                break;
            case "Atbash Screen": // Atbash
                bundle.putString(titleKey, appResources.getString(R.string.atbash_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.atbash_description));
                break;
            case "Polybius Screen": // Polybius
                bundle.putString(titleKey, appResources.getString(R.string.polybius_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.polybius_description));
                break;
            case "Page 1": // Page 1 holds the 4 OG ciphers
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
                bundle.putString(cipherDemonstrationKey, "CaesarDemonstration");
                break;
            case "Vigenere Demonstration":
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.vigenere_input_description));
                bundle.putString(cipherkeyDescriptionKey, appResources.getString(R.string.vigenere_key_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.vigenere_output_description));
                bundle.putString(cipherDemonstrationKey, "VigenereDemonstration");
                break;
            case "Atbash Demonstration":
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.atbash_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.atbash_output_description));
                bundle.putString(cipherDemonstrationKey, "AtbashDemonstration");
                break;
            case "Polybius Demonstration":
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.polybius_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.polybius_output_description));
                bundle.putString(cipherDemonstrationKey, "PolybiusDemonstration");
                break;
            case "Caesar Visualisation":
                bundle.putString(visualisationKey, appResources.getString(R.string.caesar_visualisation_description));
                bundle.putString(visualisationImageKey, "CaesarVisualisationImage");
                break;
            case "Vigenere Visualisation":
                bundle.putString(visualisationKey, appResources.getString(R.string.vigenere_visualisation_description));
                bundle.putString(visualisationImageKey, "VigenereVisualisationImage");
                break;
            case "Atbash Visualisation":
                bundle.putString(visualisationKey, appResources.getString(R.string.atbash_visualisation_description));
                bundle.putString(visualisationImageKey, "AtbashVisualisationImage");
                break;
            case "Polybius Visualisation":
                bundle.putString(visualisationKey, appResources.getString(R.string.polybius_visualisation_description));
                bundle.putString(visualisationImageKey, "PolybiusVisualisationImage");
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

package ui;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cipherhub.R;

import java.util.*;

public class SectionPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>(); //list for both quadrup and section fragments
    private List<String> fragmentTitles = new ArrayList<>();

    private static String titleKey = "Title";
    private static String descriptionKey = "Description";

    private static String buttonOneKey = "ButtonOne";
    private static String buttonTwoKey = "ButtonTwo";
    private static String buttonThreeKey = "ButtonThree";
    private static String buttonFourKey = "ButtonFour";


    public SectionPageAdapter(FragmentManager fm) { //call parent constructor for FragmentManager in order to override methods
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) { //add a given fragment method
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        switch(fragmentTitles.get(position)) {
            case "Main Screen": //main activity
                bundle.putString(titleKey, Resources.getSystem().getString(R.string.app_title));
                bundle.putString(descriptionKey, Resources.getSystem().getString(R.string.app_description));
                break;
            case "Caesar Screen": //caesar
                bundle.putString(titleKey, Resources.getSystem().getString(R.string.caesar_title));
                bundle.putString(descriptionKey, Resources.getSystem().getString(R.string.caesar_description));
                break;
            case "Vigenere Screen": //vigenere
                bundle.putString(titleKey, Resources.getSystem().getString(R.string.vigenere_title));
                bundle.putString(descriptionKey, Resources.getSystem().getString(R.string.vigenere_description));
                break;
            case "Atbash Screen": //atbash
                bundle.putString(titleKey, Resources.getSystem().getString(R.string.atbash_title));
                bundle.putString(descriptionKey, Resources.getSystem().getString(R.string.atbash_description));
                break;
            case "Polybius Screen": //polybius
                bundle.putString(titleKey, Resources.getSystem().getString(R.string.polybius_title));
                bundle.putString(descriptionKey, Resources.getSystem().getString(R.string.polybius_description));
                break;
            case "Page 1":
                bundle.putString(buttonOneKey, Resources.getSystem().getString(R.string.change_activity_caesar));
                bundle.putString(buttonTwoKey, Resources.getSystem().getString(R.string.change_activity_vigenere));
                bundle.putString(buttonThreeKey, Resources.getSystem().getString(R.string.change_activity_atbash));
                bundle.putString(buttonFourKey, Resources.getSystem().getString(R.string.change_activity_polybius));
                break;
            default:
                break;
        }

        return fragments.get(position); //get the position of the current fragment from out fragment array list
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

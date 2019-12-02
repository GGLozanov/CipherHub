package ui;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cipherhub.R;

import java.util.*;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>(); //list for both quadrup, cipher and section fragments
    private List<String> fragmentTitles = new ArrayList<>();

    private static final String titleKey = "Title";
    private static final String descriptionKey = "Description";
    private static final String cipherKey = "Cipher";

    private static final String buttonOneKey = "ButtonOne";
    private static final String buttonTwoKey = "ButtonTwo";
    private static final String buttonThreeKey = "ButtonThree";
    private static final String buttonFourKey = "ButtonFour";


    public FragmentPageAdapter(FragmentManager fm) { //call parent constructor for FragmentManager in order to override methods
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
                bundle.putString(titleKey, "Cipherhub");
                bundle.putString(descriptionKey, "Your one-stop shop for all your cryptography-oriented needs!");
                break;
            case "Caesar Screen": //caesar
                bundle.putString(titleKey, "Caesar Cipher");
                bundle.putString(descriptionKey, "The Caesar cipher is one of the earliest known and simplest ciphers. It is a type of substitution cipher in which each letter in the plaintext is \"shifted\" a certain number of places down the alphabet. For example, with a shift of 1, A would be replaced by B, B would become C, and so on.");
                break;
            case "Vigenere Screen": //vigenere
                bundle.putString(titleKey, "Vigenere Cipher");
                bundle.putString(descriptionKey, "The Vigenère cipher (French pronunciation: [viʒnɛːʁ]) is a method of encrypting alphabetic text by using a series of interwoven Caesar ciphers, based on the letters of a keyword. It employs a form of polyalphabetic substitution.");
                break;
            case "Atbash Screen": //atbash
                bundle.putString(titleKey, "Atbash Cipher");
                bundle.putString(descriptionKey, "Atbash (Hebrew: אתבש\u200E; also transliterated Atbaš) is a monoalphabetic substitution cipher originally used to encrypt the Hebrew alphabet. It can be modified for use with any known writing system with a standard collating order");
                break;
            case "Polybius Screen": //polybius
                bundle.putString(titleKey, "Polybius Cipher");
                bundle.putString(descriptionKey, "In cryptography, the Polybius square, also known as the Polybius checkerboard, is a device invented by the Ancient Greeks Cleoxenus and Democleitus, and perfected by the Ancient Greek historian and scholar Polybius,[1] for fractionating plaintext characters so that they can be represented by a smaller set of symbols.");
                break;
            case "Page 1":
                bundle.putString(buttonOneKey, "Caesar");
                bundle.putString(buttonTwoKey, "Vigenere");
                bundle.putString(buttonThreeKey, "Atbash");
                bundle.putString(buttonFourKey, "Polybius");
                break;
            case "Caesar Cipher":
                bundle.putString(cipherKey, "Caesar");
                break;
            case "Vigenere Cipher":
                bundle.putString(cipherKey, "Vigenere");
                break;
            case "Atbash Cipher":
                bundle.putString(cipherKey, "Atbash");
                break;
            case "Polybius Cipher":
                bundle.putString(cipherKey, "Polybius");
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

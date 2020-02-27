package adapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cipherhub.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Enums;
import com.google.common.base.Optional;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>(); //list for both quadrup, cipher and section fragments (all fragments classes that extent Fragment)
    private List<String> fragmentTitles = new ArrayList<>(); //list for fragment titles by which to search in switch()
    private Context context;

    Handler handler;

    public FragmentPageAdapter(FragmentManager fm, final Context context) {
        // call parent constructor for FragmentManager in order to override methods
        // also pass necessary context from instantiated FragmentPageAdapter to use for accessing the resources of the app
        super(fm);
        this.context = context;
        handler = new Handler(context.getResources()); //variable for accessing the Resources of the app and to avoid multiple calls to the same method
    }

    public void addFragment(Fragment fragment, String title) { //add a given fragment method
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    public List<Fragment> getFragments() {return fragments;}

    @Override
    public Fragment getItem(int position) {

        String tag = fragmentTitles.get(position);

        // TODO: Separate redundant checks into function and improve code legibility (find a way to call given function; enum switch again??)

        // Enum init

        // Screen
        Optional<Enumerations.Screen> screen = Enums.getIfPresent(Enumerations.Screen.class, tag);
        if(screen.isPresent()) {
            handler.handleScreen(screen.get());
        }
        // Cipher
        Optional<Enumerations.Cipher> cipher = Enums.getIfPresent(Enumerations.Cipher.class, tag);
        if(cipher.isPresent()) {
            handler.handleCipher(cipher.get());
        }

        // Page
        Optional<Enumerations.Page> page = Enums.getIfPresent(Enumerations.Page.class, tag);
        if(page.isPresent()) {
            handler.handlePage(page.get());
        }

        // CustomCipher
        Optional<Enumerations.CustomCiphers> customCiphers = Enums.getIfPresent(Enumerations.CustomCiphers.class, tag);
        if(customCiphers.isPresent()) {
            handler.handleCustomCipher(customCiphers.get());
        }

        // Demonstration
        Optional<Enumerations.Demonstration> demonstration = Enums.getIfPresent(Enumerations.Demonstration.class, tag);
        if(demonstration.isPresent()) {
            handler.handleDemonstration(demonstration.get());
        }

        // Visualisation
        Optional<Enumerations.Visualisation> visualisation =  Enums.getIfPresent(Enumerations.Visualisation.class, tag);
        if(visualisation.isPresent()) {
            handler.handleVisualisation(visualisation.get());
        }

        fragments.get(position).setArguments(handler.getBundle());

        return fragments.get(position); // get the position of the current fragment from out fragment array list
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

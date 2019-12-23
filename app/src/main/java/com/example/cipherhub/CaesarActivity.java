package com.example.cipherhub;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import ui.CipherFragment;
import ui.ui_custom.ui_single_key_ciphers.CustomCaesarFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpPagerInterface;

public class CaesarActivity extends Activity implements SetUpPagerInterface {

    // include viewpager for activities -> done

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new SectionFragment(), "Caesar Screen");
        adapter.addFragment(new CipherFragment(), "Caesar Cipher");
        adapter.addFragment(new CustomCaesarFragment(), "Custom Caesar"); //replace titles with string resource references
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar);

        Toolbar toolbar = findViewById(R.id.toolbar); // get Toolbar widget
        setSupportActionBar(toolbar); // set it as supporting action bar

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }
}
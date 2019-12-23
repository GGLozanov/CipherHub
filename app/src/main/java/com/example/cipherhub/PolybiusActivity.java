package com.example.cipherhub;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import ciphers.PolybiusCipher;
import ui.CipherFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpPagerInterface;
import ui.ui_custom.ui_table_key_ciphers.CustomPolybiusFragment;

public class PolybiusActivity extends Activity implements SetUpPagerInterface {

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new SectionFragment(), "Polybius Screen");
        adapter.addFragment(new CipherFragment(), "Polybius Cipher");
        adapter.addFragment(new CustomPolybiusFragment(), "Custom Polybius");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polybius);

        Toolbar toolbar = findViewById(R.id.toolbar); // get Toolbar widget
        setSupportActionBar(toolbar); // set it as supporting action bar

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }

}

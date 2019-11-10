package com.example.cipherhub; //main designated package

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
//import android.Animation.animation;

public class MainActivity extends AppCompatActivity { //main class that inherits from AppCompatActivity (superclass for Activity)
    @Override
    protected void onCreate(Bundle savedInstanceState) { //overriding the OnCreate() default method for Activity to what we want
        super.onCreate(savedInstanceState); //call parent constructor of AppCompatActivity and pass in the last saved instance (constant)

        setContentView(R.layout.activity_main); //set the screen's layout to the xml file in layout (can interact with it)

        Button CaesarButton = (Button) findViewById(R.id.CaesarButton);
        Button VigenereButton = (Button) findViewById(R.id.VigenereButton);
        Button AtbashButton = (Button) findViewById(R.id.AtbashButton);
        Button PolybiusButton = (Button) findViewById(R.id.PolybiusButton);

        PolybiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPolybiusActivity();
            }
        });

        CaesarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCaesarActivity();
            }
        });

        VigenereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenVigenereActivity();
            }
        });

        AtbashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAtbashActivity();
            }
        });
    }

    public void OpenCaesarActivity(){
        Intent CaesarIntent = new Intent(this, CaesarActivity.class);
        //first constructor argument takes the CONTEXT of the package (this package, the one we're currently in).
        //second constructor argument takes the class of the Activity we want to start; we can access the class through the property 'class' of the Activity.
        //Research ContentProviders (abstract data providers) and URI - Uniform Resource Identifier (string of characters that represent a resource).
        startActivity(CaesarIntent);
    }

    public void OpenVigenereActivity() {
        Intent VigenereIntent = new Intent(this, VigenereActivity.class);

        startActivity(VigenereIntent);
    }

    public void OpenAtbashActivity() {
        Intent AtbashIntent = new Intent(this, AtbashActivity.class);

        startActivity(AtbashIntent);
    }

    public void OpenPolybiusActivity() {
        Intent PolybiusIntent = new Intent(this, PolybiusActivity.class);

        startActivity(PolybiusIntent);
    }
}

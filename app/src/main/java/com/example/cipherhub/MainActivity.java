package com.example.cipherhub; //main designated package

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//import android.Animation.animation;

public class MainActivity extends AppCompatActivity { //main class that inherits from AppCompatActivity (superclass for Activity)

    @Override
    protected void onCreate(Bundle savedInstanceState) { //overriding the OnCreate() default method for Activity to what we want
        super.onCreate(savedInstanceState); //call parent constructor of AppCompatActivity and pass in the last saved instance (constant)
        setContentView(R.layout.activity_main); //set the screen's layout to the xml file in layout (can interact with it)
        Button helloButton = (Button) findViewById(R.id.printHelloButton); //R = resources; id property accesses your id as input
        Button CaesarButton = (Button) findViewById(R.id.CaesarButton);
        final TextView helloText = (TextView) findViewById(R.id.HelloText);
        helloButton.setOnClickListener(new View.OnClickListener() { //We set the listener for the event
            // to new View (view = rectangular area with stuff in it) onClickListener
            //and override its method of onclick to what we want
            //View = android:view (accesses from XML file)
            @Override
            public void onClick(View v) {
                if(helloText.isShown())helloText.setVisibility(View.INVISIBLE); //INVISIBLE is an int that is hardcoded to mean INVISIBLE and is property of the View
                else helloText.setVisibility(View.VISIBLE); //same with VISIBLE
            }
        });
        CaesarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCaesarActivity();
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
}

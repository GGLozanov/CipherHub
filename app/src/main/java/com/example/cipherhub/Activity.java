package com.example.cipherhub;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class Activity extends AppCompatActivity { // abstract superclass for all activities
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu); // Menu inflater takes menu resource and Menu as an argument and adapts it to a view (i.e. inflates it)
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.actionChangeMode: // pressed change mode
                showPopUp(this.findViewById(R.id.toolbar)); // set the anchor view (the one the popup menu will stick to) to the view of the toolbar
                return true; // stop the function here if we have selected the id of a menu option
        }

        return super.onOptionsItemSelected(item); // otherwise call the parent implementation
    }

    public void showPopUp(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view); // takes activity context and current View to instantiate
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setGravity(Gravity.END);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) { // implement own PopupMenu listener
                switch(item.getItemId()) {
                    case R.id.lightMode:
                        return true;
                    case R.id.darkMode:
                        return true;
                    default: return false;
                }
            }
        });

        popupMenu.show();
    }
}

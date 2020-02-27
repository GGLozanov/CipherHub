package com.example.cipherhub;

public interface SetVisibilityModes {

    void setLightTheme();

    void setDarkTheme();

    default void setTheme() {
        if(Activity.getMode()) setDarkTheme();
        else setLightTheme();
    }

}

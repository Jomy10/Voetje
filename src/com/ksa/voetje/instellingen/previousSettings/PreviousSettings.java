package com.ksa.voetje.instellingen.previousSettings;

import com.ksa.voetje.opmaak.themes.ThemeDisplaySystem;
import com.ksa.voetje.instellingen.DisplayInstellingen;
import com.ksa.voetje.instellingen.MorseInstellingen;

import java.io.FileNotFoundException;

public class PreviousSettings {
    private final LastSettings lastSettings = new LastSettings();
    private final Object[] previousSettings;

    //private final SettingsReader settingsReader = new SettingsReader("savedSettings.txt");

    public PreviousSettings() {
        previousSettings = lastSettings.deSerialize();
    }

    /**
     * 0 - theme
     * 1 - Morse
     * 2 - readUpdates
     */
    public Object[] getPreviousSettings() throws FileNotFoundException {
        return previousSettings;
        // return settingsReader.getPreviousSettings(10);
    }

    public void restoreThemeSettings(ThemeDisplaySystem themeDisplaySystem) {
        /*
        int themeSettings = settingsReader.getPreviousTheme();
        if (themeSettings == 1) {
            themeDisplaySystem.setTheme(1);
        } else if (themeSettings == 0) {
            themeDisplaySystem.setTheme(0);
        } else  {
            if (themeDisplaySystem.isMacMenuBarDarkMode()) {
                themeDisplaySystem.setTheme(1);
            } else
                themeDisplaySystem.setTheme(0);
        }
        */
        int themeSettings = 2;
        try {
            themeSettings = (int) getPreviousSettings()[0];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Set theme
        if (themeSettings == 1) {
            themeDisplaySystem.setTheme(1);
            lastSettings.setTheme(1);
            DisplayInstellingen.setThemeSettings(1);
        } else if (themeSettings == 0) {
            themeDisplaySystem.setTheme(0);
            lastSettings.setTheme(0);
            DisplayInstellingen.setThemeSettings(0);
        } else  {
            if (themeDisplaySystem.isMacMenuBarDarkMode()) {
                themeDisplaySystem.setTheme(1);
                lastSettings.setTheme(1);
                DisplayInstellingen.setThemeSettings(1);
            } else {
                themeDisplaySystem.setTheme(0);
                lastSettings.setTheme(0);
                DisplayInstellingen.setThemeSettings(0);
            }
        }
    }

    public void restoreMorseSettings() {
        int morseMaxChar = 10000;
        try {
            morseMaxChar = (int) getPreviousSettings()[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        lastSettings.setMorseMaxChar(morseMaxChar);
        MorseInstellingen.setMorseMaxCharacters(morseMaxChar);
    }

    public boolean[] getReadUpdate() {
        return lastSettings.getReadUpdates();
    }

    public void setUpdateRead(int updateNr) {
        System.out.println("Setting update " + updateNr + " as read...");
        lastSettings.setUpdateRead(updateNr);
    }

    public void setMorseMaxChar(int i) {
        lastSettings.setMorseMaxChar(i);
        System.out.println("MorseMaxChar: " + lastSettings.getMorseMaxChar());
    }

    public void setTheme(int theme) {
        lastSettings.setTheme(theme);
    }

    public void serialize() {
        lastSettings.serialize();
    }
}

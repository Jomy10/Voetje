package com.ksa.voetje.instellingen;

import com.ksa.voetje.booter.Voetje;

public class DisplayInstellingen {
    private static int themeSettings;

    public static void setThemeSettings(int theme) {
        themeSettings = theme;
    }

    public static int getSettings() {
        Voetje.getLogWindow().addToLog("Getting display settings...");
        Voetje.getLogWindow().addToLog("themeSettings = " + themeSettings);
        int settings = themeSettings;
        return settings;
    }

}

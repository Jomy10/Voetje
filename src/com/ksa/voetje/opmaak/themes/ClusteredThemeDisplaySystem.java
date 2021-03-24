package com.ksa.voetje.opmaak.themes;

import com.ksa.voetje.Main;
import com.ksa.voetje.booter.Voetje;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ClusteredThemeDisplaySystem implements ThemeDisplaySystemInterface {
    private ThemeDisplaySystem[] themeDisplaySystems;
    private int current;

    public ClusteredThemeDisplaySystem(int amountOfSystems) {
        themeDisplaySystems = new ThemeDisplaySystem[amountOfSystems];
    }

    /**
     * Adds a new themeDisplaySystem to the cluster
     * Also applies the last saved settings to the themeDisplaySystem
     * @param themeDisplaySystem the ThemeDisplaySystem to be added to the cluster
     */
    public void add(ThemeDisplaySystem themeDisplaySystem) {
        themeDisplaySystems[current] = themeDisplaySystem;
        current++;
        System.out.println("Succesfully added " + themeDisplaySystem + " to a clustered ThemeDisplaySystem");
        Main.getPreviousSettings().restoreThemeSettings(themeDisplaySystem);
    }

    @Override
    public void setTheme(int theme) {
        for (ThemeDisplaySystem themeDisplaySystem : themeDisplaySystems) {
            themeDisplaySystem.setTheme(theme);
        }
    }

    @Override
    public boolean isMacMenuBarDarkMode() {
        Voetje.getLogWindow().addToLog("Searching for system theme...");

        try {
            Process proc = Runtime.getRuntime().exec(new String[]{"defaults", "read", "-g", "AppleInterfaceStyle"});
            proc.waitFor(100L, TimeUnit.MILLISECONDS);
            Voetje.getLogWindow().addToLog("Found system theme.");
            return proc.exitValue() == 0;
        } catch (InterruptedException | IllegalThreadStateException | IOException var1) {
            Voetje.getLogWindow().addToLog("Could not determine, whether 'dark mode' is being used. Falling back to default (light) mode.");
            return false;
        }
    }
}

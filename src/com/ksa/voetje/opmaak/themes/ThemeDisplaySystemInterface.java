package com.ksa.voetje.opmaak.themes;

import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

public interface ThemeDisplaySystemInterface {
    /**
     * Sets the theme
     * @param theme 0 for light, 1 for dark
     */
    void setTheme(int theme);

    boolean isMacMenuBarDarkMode();
}

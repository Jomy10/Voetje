package com.ksa.voetje.opmaak;

import javafx.scene.paint.Color;

public class OpmaakVariables {
    // Nodig?
    /*
    public enum Kleur {DEFAULT, DARK, LIGHT}

    public static double stageHeight;
    public static double stageWidth;
    public static Color color;
    public static Kleur currentTheme;

    public static void setColor(Kleur kleur) {
        if (kleur == Kleur.DEFAULT)
            setBackgroundColor(Cte.LIGHT_COLOR_THEME, Kleur.DEFAULT);
        else if (kleur == Kleur.DARK)
            setBackgroundColor(Cte.DARK_COLOR_THEME, Kleur.DARK);
        else
            throw new IllegalStateException("Unexpected value: " + kleur);
    }

    public static void setBackgroundColor(Color color, Kleur kleur) {
        currentTheme = kleur;
        Var.color = color;
        RootPane.setBackgroundColorRoot();
    }

    public static void setColorTheme(Kleur kleur) {
        if (kleur == Kleur.DEFAULT) { // Default: system default (only Mac supported)
            if (isMacMenuBarDarkMode()) {
                // Dark mode
                setColorTheme(Kleur.DARK);
                currentTheme = Kleur.DEFAULT; // Dit zorgt ervoor dat de selector op Default zal staan de volgende keer
            } else {
                // Light mode
                setColorTheme(Kleur.LIGHT);
                currentTheme = Kleur.DEFAULT;
            }
        }
        else if (kleur == Kleur.DARK) {
            setBackgroundColor(DARK_COLOR_THEME, Kleur.DARK);
            scene.getStylesheets().clear();
            scene.getStylesheets().add("com/ksa/voetje/opmaak/themes/opmaakDark.css");
        }
        else if (kleur == Kleur.LIGHT) {
            setBackgroundColor(LIGHT_COLOR_THEME, Kleur.LIGHT);
            scene.getStylesheets().clear();
            scene.getStylesheets().add("com/ksa/voetje/opmaak/themes/opmaakDefault.css");
        }
    }
     */
}

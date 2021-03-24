package com.ksa.voetje.opmaak;

import javafx.scene.paint.Color;

public class OpmaakConstants {
    // Interfaces //
    /// Lettertypes ///
    public static final int TITLE_SIZE = 24;
    public static final int TUSSEN_TITLE_SIZE = 18; // used in settings
    public static final double FONT_SIZE = 12.5;

    /// Color ///
    // public static File temp = new File("opmaakDefault.css");
    // LIGHT
    public static final Color LIGHT_COLOR_THEME = Color.rgb(226,226,226);
    public static final Color LIGHT_TEXT_COLOR = Color.rgb(30,30,30); // in css file "com.ksa.voetje.opmaak/opmaakDefault.css"
    public static final Color LIGHT_INPUT_FIELD_BACKGROUND = Color.rgb(255,255,255);
    public static final Color LIGHT_INPUT_FIELD_BORDER = Color.rgb(200,200,200);

    // DARK
    public static final Color DARK_COLOR_THEME = Color.rgb(52,52,52);
    public static final Color DARK_TEXT_COLOR = Color.rgb(167,167,167);
    public static final Color DARK_INPUT_FIELD_BACKGROUND = Color.rgb(64,64,64);
    public static final Color DARK_INPUT_FIELD_BORDER = Color.rgb(26,26,26);

    /// Sizing ///
    public static final int INPUT_FIELD_WIDTH = 500;
    public static final int INTERFACE_WIDTH = 500;
    public static final int INTERFACE_MIN_WIDTH = 30;
    public static final String INPUT_INSTRUCTION = "Typ de zin in om te encoderen.";
    public static final int MARGIN = 10;
    public static final double INTERFACE_SPACING = 5; // TODO: kijken hoevel spacing tussen de elementen moet

    // Scene //
    public static final int SCENE_WIDTH = 600;
    public static final int SCENE_HEIGHT = 400;
    public static final int SIDEBAR_WIDTH = 95; // Max sized button = blokmethode = 91.0
    public static final int SIDEBAR_BUTTON_WIDTH = 91;
}

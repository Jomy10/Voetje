package com.ksa.voetje.opmaak.themes;

import com.ksa.voetje.booter.Voetje;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.ksa.voetje.opmaak.OpmaakConstants.DARK_COLOR_THEME;
import static com.ksa.voetje.opmaak.OpmaakConstants.LIGHT_COLOR_THEME;

public class ThemeDisplaySystem implements ThemeDisplaySystemInterface{
    private Pane pane;
    private Stage stage;

    public ThemeDisplaySystem(@NotNull Pane pane) {
        this.pane = pane;
        this.stage = (Stage) pane.getScene().getWindow(); // Gets the stage the pane belongs to (gets primaryStage)
        int theme = 0;
        setTheme(theme);
    }

    // Set theme //
    /**
     * Sets the theme
     * @param theme 0 for light, 1 for dark
     */
    public void setTheme(int theme) {
        if (theme == 0) {
            pane.setBackground(new Background(new BackgroundFill(LIGHT_COLOR_THEME, null, null)));
            stage.getScene().getStylesheets().clear();
            stage.getScene().getStylesheets().add("com/ksa/voetje/opmaak/themes/opmaakDefault.css");
        } else {
            pane.setBackground(new Background(new BackgroundFill(DARK_COLOR_THEME, null, null)));
            stage.getScene().getStylesheets().clear();
            stage.getScene().getStylesheets().add("com/ksa/voetje/opmaak/themes/opmaakDark.css");
        }
    }

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

package com.ksa.voetje;

import com.apple.eawt.*;
import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.themes.ClusteredThemeDisplaySystem;
import com.ksa.voetje.opmaak.themes.ThemeDisplaySystem;
import com.ksa.voetje.instellingen.previousSettings.PreviousSettings;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;
import com.ksa.voetje.startscherm.StartGrid;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

import com.apple.eawt.ApplicationAdapter;

public class Main extends Application {
    private static PreviousSettings previousSettings;
    private static Pane startSchermPane;
    public static HostServices hostServices;

    private static final ClusteredThemeDisplaySystem clusteredThemeDisplaySystem = new ClusteredThemeDisplaySystem(2);
    private static ThemeDisplaySystem themeDisplaySystem;

    public static void main(String[] args) {
        launch();
    }

    // Program
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Primary Stage
        //primaryStage = new GeheimschriftDisplaySystem();
        StartGrid stage = new StartGrid();
        primaryStage = stage;
        hostServices = getHostServices();
        startSchermPane = stage.getPane();

        // Mac Spefic about menu
        try {
            new HandleAboutMac();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initSettings initSettings = new initSettings();
        initSettings.start();

        try {
            // For MacOs
            Voetje.addToLog(this, "Setting the app icon...");
            java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(Objects.requireNonNull(Voetje.class.getClassLoader().getResource("res/appIcon/macOS/voetjeIcon1024.png")));
            com.apple.eawt.Application application = com.apple.eawt.Application.getApplication();
            application.setDockIconImage(icon);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
            // windows app icon & other OS'es who don't support the method in Voetje
            Image iconWin = new Image("res/voetjeBoekjeIcon.png");
            primaryStage.getIcons().add(iconWin);
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
            ErrorMessage securityException = new ErrorMessage("There was a security exception.", e, (Object) "Booter");
            securityException.showAndWait();
        }
    }

    /**
     * Initialises the theme display system for the begin scherm and retrieves the previous settings
     */
    static class initSettings extends Thread {
        @Override
        public void run() {
            Voetje.addToLog("[" + this.getName() + "] Initialising the Theme Display System...");
            // Settings
            themeDisplaySystem = new ThemeDisplaySystem(startSchermPane); // TODO: for startscherm doen, want laten staan in geheimschrift

            // Deserialization //
            // Previous settings initialisation
            Voetje.addToLog("[" + this.getName() + "] Initialising previous settings...");
            previousSettings = new PreviousSettings();

            // adding startGrid to the themedisplay cluster
            //previousSettings.restoreThemeSettings(themeDisplaySystem);
            clusteredThemeDisplaySystem.add(themeDisplaySystem);
        }
    }

    public static PreviousSettings getPreviousSettings() {
        if (previousSettings == null)
            System.out.println("error");
        return previousSettings;
    }

    public static HostServices getHostServicesFromMain() {
        return hostServices;
    }


    // Theme Display System //

    /**
     * Sets the application theme
     * @param theme 0 = light, 1 = dark
     */
    public static void setTheme(int theme) {
        clusteredThemeDisplaySystem.setTheme(theme);
    }

    /**
     * Adds a ThemeDisplaySystem that has to be changed along with other display systems
     */
    public static void addThemeDisplaySystem(ThemeDisplaySystem themeDisplaySystem) {
        clusteredThemeDisplaySystem.add(themeDisplaySystem);
    }

    /**
     * Sets the action that has to be performed when a Mac user selects "about voetje" in the menu
     */
    public static class HandleAboutMac  {
        public HandleAboutMac() {
            com.apple.eawt.Application application = com.apple.eawt.Application.getApplication();
            application.setAboutHandler(new AboutHandler() {
                @Override
                public void handleAbout(AppEvent.AboutEvent aboutEvent) {
                    JOptionPane.showMessageDialog(null, "'t Voetje\nBeta 3.0\nAuthor: Jonas Everaert");
                }
            });
        }
    }
}

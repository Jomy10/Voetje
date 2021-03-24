package com.ksa.voetje.booter;

import com.ksa.voetje.Main;
import com.ksa.voetje.debug.logging.LogWindow;
import com.ksa.voetje.methodes.fileSystem.CopyResource;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Voetje {
    private static final LogWindow logWindow = new LogWindow();
    private static java.awt.Font raamFontTTF;

    public static void main(String[] args) {
        // Start logger
        logWindow.addToLog("Starting application...\nInitialising logger...");
        /*
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JFXPanel(); // this will prepare JavaFX toolkit and environment
            }
        });
         */

        // Bootup process
        BootupPreparationThread bootupThread = new BootupPreparationThread();
        bootupThread.start();

        // Launch main
        Main.main(args);
    }

    // Logging //
    /**
     * Shows the log window
     */
    public static void showLogWindow() {
        logWindow.addToLog("Opening log window...");
        logWindow.showLogWindow();
    }

    /**
     * Retrieves the logWindow
     * @return the log window
     */
    public static LogWindow getLogWindow() {
        return logWindow;
    }

    /**
     * Adds text to the log and log window
     * @param o the object the addToLog command is activated from
     * @param content the text that has to be added to the log
     */
    public static void addToLog(Object o, String content) {
        logWindow.addToLog("[" + o.getClass() + "] " + content);
    }
    public static void addToLog(String o, String content) {
        logWindow.addToLog("[" + o + "] " + content);
    }
    public static void addToLog(String content) {logWindow.addToLog(content);}

    // Methods //
    public static java.awt.Font getRaamgeheimschriftFont() {
        return raamFontTTF;
    }

    // Threads //
    static class BootupPreparationThread extends Thread {
        @Override
        public void run() {
            logWindow.addToLog("[" + this.getName() + "] Doing preparations for the program...");

            // Copy fonts //
            logWindow.addToLog("Attempting to copy font...");
            // Copy otf font
            CopyResource copyOTF = new CopyResource();
            copyOTF.setResource("/com/ksa/voetje/opmaak/lettertypes/Raamgeheimschrift-Regular.otf");
            try {
                copyOTF.copyTo("lettertypes", "Raamgeheimschrift.otf");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            // Copy ttf
            CopyResource copyTTF = new CopyResource();
            copyTTF.setResource("/com/ksa/voetje/opmaak/lettertypes/Raamgeheimschrift-Regular.ttf");
            try {
                copyTTF.copyTo("lettertypes", "Raamgeheimschrift.ttf");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            // Define font
            try {
                java.awt.Font createRaamFont = Font.createFont(Font.TRUETYPE_FONT, new File(copyTTF.getCopiedToPath()));
                GraphicsEnvironment ge =
                        GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(createRaamFont);
                int fontSize = 90;
                raamFontTTF = new Font("Raamgeheimschrift", Font.PLAIN, fontSize);
            } catch (FontFormatException|IOException e) {
                ErrorMessage fontError = new ErrorMessage(
                        "Het raamgeheimschrift lettertype kon niet geladen worden.",
                        "Het programma kan nog steeds gebruikt worden, maar het raamgeheimschrift lettertype ontbreekt mogelijk.",
                        e, (Object) "Booter"
                );
                e.printStackTrace();
            }
        }
    }
}

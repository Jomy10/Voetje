package com.ksa.voetje.opmaak.elements.windows;

import com.ksa.voetje.booter.Voetje;

public class InfoAlert extends javafx.scene.control.Alert {
    /**
     * Confirmation alert.
     * Shows a dialog with information that can be dismissed.
     * @param header The header text
     * @param content The content text
     */
    public InfoAlert(String header, String content) {
        super(AlertType.CONFIRMATION);
        setTitle("Info");
        setHeaderText(header);
        setContentText(content);
    }

    /**
     * Confirmation alert.
     * Shows a dialog with information that can be dismissed.
     * @param header The header text
     * @param content The content text
     * @param title The title of the dialog box
     */
    public InfoAlert(String title, String header, String content) {
        super(AlertType.INFORMATION);
        setTitle(title);
        setHeaderText(header);
        setContentText(content);
    }

    /**
     * Confirmation alert.
     * Shows a dialog with information that can be dismissed.
     * @param header The header text
     * @param content The content text
     * @param title The title of the dialog box
     * @param addToLog If true, then the content will be added to the log
     */
    public InfoAlert(String title, String header, String content, boolean addToLog) {
        super(AlertType.INFORMATION);
        setTitle(title);
        setHeaderText(header);
        setContentText(content);
        if (addToLog)
            Voetje.getLogWindow().addToLog(content);
    }

    /**
     * Confirmation alert.
     * Shows a dialog with information that can be dismissed.
     * @param header The header text
     * @param content The content text
     * @param title The title of the dialog box
     * @param addToLogText Text to be added to the log
     */
    public InfoAlert(String title, String header, String content, String addToLogText) {
        super(AlertType.INFORMATION);
        setTitle(title);
        setHeaderText(header);
        setContentText(content);
        Voetje.getLogWindow().addToLog(addToLogText);
    }
}

/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements.windows;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

// TODO: overal implementeren

public class ErrorMessage extends Alert {
    /**
     * Makes an error message
     * @param message The message of the error
     */
    public ErrorMessage(String message) {
        super(AlertType.ERROR);
        setTitle("Error");
        setHeaderText("Oeps, een error :(");
        setContentText(message);
    }

    /**
     * Makes an error message with a custom header
     * @param message The message of the error
     * @param header The header of the message (default = Oeps een error :()
     */
    public ErrorMessage(String header, String message) {
        super(AlertType.ERROR);
        setTitle("Error");
        if (header != null)
            setHeaderText(header);
        else
            setHeaderText("Oeps, een error :(");
        setContentText(message);
    }

    /**
     * Makes an error message that includes the stacktrace
     * @param message The message of the error
     * @param ex The exception which's stacktrace has to be shown
     * @param o - this. The object from which the error is thrown.
     */
    public ErrorMessage(String message, Exception ex, Object o) {
        super(AlertType.ERROR);
        setTitle("Error");
        setHeaderText("Oeps, een error :(");
        setContentText(message);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();
        System.out.println("[" + o.getClass() + "]" + exceptionText);

        javafx.scene.control.Label label = new Label("Exception stacktrace:");

        javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        getDialogPane().setExpandableContent(expContent);
    }

    /**
     * Makes an error message that includes the stacktrace
     * @param header The header of the error message (default = Oeps, een error :()
     * @param message The message of the error
     * @param ex The exception which's stacktrace has to be shown
     * @param o - this. The object from which the error is thrown.
     */
    public ErrorMessage(String header, String message, Exception ex, Object o) {
        super(AlertType.ERROR);
        if (header != null)
            setTitle("Error");
        else
            setTitle("Oeps, een error :(");
        setHeaderText(header);
        setContentText(message);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();
        System.out.println("[" + o.getClass() + "]" + exceptionText);

        javafx.scene.control.Label label = new Label("Exception stacktrace:");

        javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        getDialogPane().setExpandableContent(expContent);
    }

    /**
     * shows the error message and waits for user input
     * @deprecated showErrorMessage is depreceted, use .showAndWait()
     */
    public void showErrorMessage() {
        showAndWait();
    }
}

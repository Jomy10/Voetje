package com.ksa.voetje.debug.logging;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class LogWindow {
    private final JFrame logFrame;
    private final JTextPane log = new JTextPane();

    private final Log logString = new Log();

    public LogWindow() {
        Date date = new Date();
        String today = date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
        logFrame = new JFrame("Logger " + today);
        logFrame.setPreferredSize(new Dimension(350,400));
        logFrame.getContentPane().add(log);
    }

    public void showLogWindow() {
        logFrame.pack();
        logFrame.setVisible(true);
    }

    public void addToLog(String add) {
        logString.updateLog(add);
        log.setText(logString.getLog());
    }

    /**
     * Adds text to the log
     * @param o the object the addToLog statement is triggered from
     * @param content the content of the log message
     */
    public void addToLog(Object o, String content) {
        logString.updateLog("[" + o.getClass() + "] " + content);
        log.setText(logString.getLog());
    }

    public void addToLog(String objectName, String content) {
        logString.updateLog("[" + objectName + "] " + content);
        log.setText(logString.getLog());
    }

}

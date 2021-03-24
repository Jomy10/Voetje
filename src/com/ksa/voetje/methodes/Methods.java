/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.methodes;

// Clipboard
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Methods {
    // Clipboard
    private static final Clipboard clipboard = Clipboard.getSystemClipboard();
    private static final ClipboardContent clipboardContent = new ClipboardContent();

    public static void stringToClipboard(String s) {
        clipboardContent.putString(s);
        clipboard.setContent(clipboardContent);
        System.out.println("\"" + s + "\"" + " succesfully copied to clipboard");
    }

    public static void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

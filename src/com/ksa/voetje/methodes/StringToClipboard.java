package com.ksa.voetje.methodes;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class StringToClipboard {
    private static final Clipboard clipboard = Clipboard.getSystemClipboard();
    private static final ClipboardContent clipboardContent = new ClipboardContent();

    public StringToClipboard() {}

    public void pasteToClipboard(String s) {
        clipboardContent.putString(s);
        clipboard.setContent(clipboardContent);
        System.out.println("\"" + s + "\"" + " succesfully copied to clipboard");
    }
}

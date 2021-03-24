/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static com.ksa.voetje.opmaak.OpmaakConstants.FONT_SIZE;

public class HoofdTekstLabel extends Label {
    public HoofdTekstLabel(String text) {
        super(text);
        setFont(Font.font(FONT_SIZE));
        getStyleClass().add("hoofdtekstLabel");
        setWrapText(true);
    }

    public HoofdTekstLabel() {
        super();
        setFont(Font.font(FONT_SIZE));
        getStyleClass().add("hoofdtekstLabel");
        setWrapText(true);
    }
}

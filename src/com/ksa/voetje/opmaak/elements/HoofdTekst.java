/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.ksa.voetje.opmaak.OpmaakConstants.FONT_SIZE;

public class HoofdTekst extends Text {
    public HoofdTekst(String text) {
        super(text);
        setFont(Font.font(FONT_SIZE));
        getStyleClass().add("hoofdtekst");
    }

    public HoofdTekst() {
        super();
        setFont(Font.font(FONT_SIZE));
        getStyleClass().add("hoofdtekst");
    }
}

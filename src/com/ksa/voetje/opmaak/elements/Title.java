/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static com.ksa.voetje.opmaak.OpmaakConstants.TITLE_SIZE;

public class Title extends Label {
    public Title(String titel) {
        super(titel);
        setFont(Font.font(TITLE_SIZE));
        getStyleClass().add("titleLabel");
    }
    public Title() {
        super();
        setFont(Font.font(TITLE_SIZE));
        getStyleClass().add("titleLabel");
    }
}

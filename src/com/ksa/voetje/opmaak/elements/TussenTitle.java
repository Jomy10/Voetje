/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static com.ksa.voetje.opmaak.OpmaakConstants.TUSSEN_TITLE_SIZE;


public class TussenTitle extends Label {
    public TussenTitle(String tussenTitel) {
        super(tussenTitel);
        setFont(Font.font(TUSSEN_TITLE_SIZE));
        getStyleClass().add("tussenTitel");
    }
}

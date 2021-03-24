/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.control.Button;

import static com.ksa.voetje.opmaak.OpmaakConstants.SIDEBAR_BUTTON_WIDTH;

public class SidebarButton extends Button{
    public SidebarButton(String text) {
        super(text);
        setPrefWidth(SIDEBAR_BUTTON_WIDTH);
        getStyleClass().add("sidebarButton");
    }
}

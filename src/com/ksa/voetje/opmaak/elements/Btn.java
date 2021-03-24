/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.control.Button;

public class Btn extends Button {
    public Btn() {
        super();
        getStyleClass().add("methodButton");
    }
    public Btn(String text) {
        super(text);
        getStyleClass().add("methodButton");
    }
}

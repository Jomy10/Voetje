/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.interfaces.panes;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class InterfaceNoMargin extends VBox {
    public InterfaceNoMargin(double spacing, Node... nodes) {
        super(spacing, nodes);
        setStyle("-fx-background-color:transparent;");
        getStylesheets().add("com/ksa/voetje/opmaak/css.css");
        getStyleClass().add("vbox");
    }
}

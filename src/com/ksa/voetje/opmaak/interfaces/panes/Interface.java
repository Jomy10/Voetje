/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.interfaces.panes;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import static com.ksa.voetje.opmaak.OpmaakConstants.*;

public class Interface extends HBox {
    private InterfaceNoMargin interfaceNoMargin;
    public Interface(double spacing, Node... nodes) {
        interfaceNoMargin = new InterfaceNoMargin(spacing, nodes);
        getChildren().add(interfaceNoMargin);
        setMargin(interfaceNoMargin, new Insets(MARGIN));
        setPrefWidth(INTERFACE_WIDTH);
        setMinWidth(INTERFACE_MIN_WIDTH);
        setStyle("-fx-background-color:transparent;");
    }

    public Interface(double spacing) {
        interfaceNoMargin = new InterfaceNoMargin(spacing);
        getChildren().add(interfaceNoMargin);
        setMargin(interfaceNoMargin, new Insets(MARGIN));
        setPrefWidth(INTERFACE_WIDTH);
        setMinWidth(INTERFACE_MIN_WIDTH);
        setStyle("-fx-background-color:transparent;");
    }

    public void add(Node element) {
        interfaceNoMargin.getChildren().add(element);
    }
}

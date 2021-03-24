/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.interfaces.panes;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.awt.*;

public class MethodScrollPane extends ScrollPane {
    public MethodScrollPane(Node content) {
        super();
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setFitToWidth(true);
        setStyle("-fx-background-color:transparent;");
        setContent(content);
        //getStylesheets().add("com/ksa/voetje/opmaak/interfaces/panes/stylesheet.css");

    }

    public MethodScrollPane() {
        super();
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setFitToWidth(true);
        setStyle("-fx-background-color:transparent;");
        //getStylesheets().add("com/ksa/voetje/opmaak/interfaces/panes/stylesheet.css");
    }

    public void setBackgroundColor(Color color) {
        setBackground(new Background(new BackgroundFill(color, null, null)));
    }
}

package com.ksa.voetje.opmaak.interfaces.panes;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class SidebarScrollPane extends ScrollPane{

    public SidebarScrollPane(Node content) {
        super();
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setFitToWidth(true);
        setStyle("-fx-background-color:transparent;");
        setContent(content);
        //getStylesheets().add("com/ksa/voetje/opmaak/interfaces/panes/stylesheet.css");
        setVbarPolicy(ScrollBarPolicy.NEVER);
    }
}

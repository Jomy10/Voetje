package com.ksa.voetje.geheimschrift.display;

import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class RootPane extends BorderPane {
    public RootPane() {};

    public RootPane(Node node) {
        super(node);
    }

    public RootPane(Node node, Node node1, Node node2, Node node3, Node node4) {
        super(node, node1, node2, node3, node4);
    }

    public void setBackgroundColor(Color color) {
        setBackground(new Background(new BackgroundFill(color, null, null)));
    }
}

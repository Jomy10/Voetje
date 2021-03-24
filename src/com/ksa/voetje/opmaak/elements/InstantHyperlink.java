/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import com.ksa.voetje.methodes.Methods;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

public class InstantHyperlink extends Hyperlink {
    public InstantHyperlink(String tekst, String url, boolean visited) {
        super(tekst);
        getStyleClass().add("instantHyperLink");
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (visited) {
                    setVisited(true);
                }
                Methods.openWebpage(url);
            }
        });
    }

    // visited link is highlighted //
    public InstantHyperlink(String tekst, String url) {
        super(tekst);
        getStyleClass().add("instantHyperLink");
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setVisited(true);
                Methods.openWebpage(url);
            }
        });
    }
}
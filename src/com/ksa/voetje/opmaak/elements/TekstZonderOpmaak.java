/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TekstZonderOpmaak extends Text {
    // Constructors //
    public TekstZonderOpmaak() {
        super();
        getStyleClass().add("hoofdtekst");
    }
    public TekstZonderOpmaak(int size) {
        super();
        setFont(new Font(size));
        getStyleClass().add("hoofdtekst");
    }
    public TekstZonderOpmaak(String content, int size) {
        super();
        setFont(new Font(size));
        setText(content);
        getStyleClass().add("hoofdtekst");
    }
    public TekstZonderOpmaak(String content) {
        super();
        setText(content);
        getStyleClass().add("hoofdtekst");
    }
    public TekstZonderOpmaak(String content, boolean sidebarElement) {
        super();
        setText(content);
        getStyleClass().add("sidebarText");
    }
}

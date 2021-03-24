/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.control.TextField;

import static com.ksa.voetje.opmaak.OpmaakConstants.INPUT_FIELD_WIDTH;

public class SmallInputField extends TextField {
    public SmallInputField(String promptText) {
        setPrefWidth(INPUT_FIELD_WIDTH);
        setPromptText(promptText);
        getStyleClass().add("smallInputField");
    }
    public SmallInputField(String promptText, String text) {
        setText(text);
        setPrefWidth(INPUT_FIELD_WIDTH);
        setPromptText(promptText);
        getStyleClass().add("smallInputField");
    }
    public SmallInputField() {
        setPrefWidth(INPUT_FIELD_WIDTH);
        getStyleClass().add("smallInputField");
    }
    public SmallInputField(boolean noFixedWidth) {
        getStyleClass().add("smallInputField");
    }
}

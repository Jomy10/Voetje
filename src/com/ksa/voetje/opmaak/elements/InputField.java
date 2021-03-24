/*
 * Copyright (c) 2021. Jonas Everaert
 */

package com.ksa.voetje.opmaak.elements;

import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;

import static com.ksa.voetje.opmaak.OpmaakConstants.INPUT_FIELD_WIDTH;
import static com.ksa.voetje.opmaak.OpmaakConstants.INPUT_INSTRUCTION;


public class InputField extends TextArea {
    public InputField() {
        setWrapText(true);
        setPrefWidth(INPUT_FIELD_WIDTH);
        setPromptText(INPUT_INSTRUCTION);
        getStyleClass().add("inputField");
    }

    public InputField(String tooltip) {
        setWrapText(true);
        setPrefWidth(INPUT_FIELD_WIDTH);
        setPromptText(INPUT_INSTRUCTION);
        setTooltip(
                new Tooltip(tooltip)
        );
        getStyleClass().add("inputField");
    }
}

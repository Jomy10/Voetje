package com.ksa.voetje.geheimschrift.display.otherInterfaces.instellingen;

import com.ksa.voetje.instellingen.MorseInstellingen;
import com.ksa.voetje.instellingen.previousSettings.PreviousSettings;
import com.ksa.voetje.opmaak.elements.*;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class MorseInstellingenInterface extends MethodScrollPane {
    private final Title morseTitle = new Title("Morse");
    private final HoofdTekstLabel maxCharLabel = new HoofdTekstLabel("Maximum aantal karakters");
    private final SmallInputField maxCharField = new SmallInputField();
    private final Btn maxCharBtn = new Btn("Opslaan");

    private PreviousSettings previousSettings;

    private final FlowPane maxCharPane = new FlowPane(maxCharField, maxCharBtn);
    private final Interface morseInterface = new Interface(INTERFACE_SPACING, morseTitle, maxCharLabel, maxCharPane);

    public MorseInstellingenInterface(PreviousSettings previousSettings) {
        this.previousSettings = previousSettings;
        setContent(morseInterface);
        maxCharField.setText(String.valueOf(MorseInstellingen.morseMaxCharacters));
        maxCharField.setPrefWidth(100);
        maxCharField.setMaxWidth(200);
        maxCharPane.setVgap(4);
        maxCharPane.setHgap(4);
        setButtonActions();
    }

    private void setButtonActions() {
        maxCharBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MorseInstellingen.setMorseMaxCharacters(Integer.parseInt(maxCharField.getText()));
                previousSettings.setMorseMaxChar(Integer.parseInt(maxCharField.getText()));
                System.out.println("Input maxCharField: " + maxCharField.getText());
            }
        });
    }
}

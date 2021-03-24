package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces;

import com.ksa.voetje.geheimschrift.geheimschrift.WoordOmkeren;
import com.ksa.voetje.methodes.StringToClipboard;
import com.ksa.voetje.opmaak.OpmaakConstants;
import com.ksa.voetje.opmaak.elements.Btn;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.InputField;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class WoordOmkerenInterface extends MethodScrollPane {
    // Elementen van de interface:
    private static final Title title = new Title("Woorden Omkeren"); // titel
    private static final HoofdTekstLabel instructies = new HoofdTekstLabel("Geef een zin in om te encoderen.");
    private static final InputField inputField = new InputField(); // input venster
    private static final HoofdTekstLabel outputDescriptie = new HoofdTekstLabel("\nOmgekeerde woorden:");
    private static final HoofdTekstLabel outputLabel = new HoofdTekstLabel(); // output
    private static final Btn readInputBtn = new Btn("Encoderen");

    // Panes
    private final Interface windInterface = new Interface(
            OpmaakConstants.INTERFACE_SPACING,
            title, instructies, inputField, readInputBtn, outputDescriptie, outputLabel
    );
    public WoordOmkerenInterface() {
        super();
        setContent(windInterface);
        setButtonActions();
    }

    private void setButtonActions() {
        readInputBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Reading input...");
                WoordOmkeren woordOmkeren = new WoordOmkeren(inputField.getText());
                String output = woordOmkeren.omkeren();
                outputLabel.setText(output);
                StringToClipboard stringToClipboard = new StringToClipboard();
                stringToClipboard.pasteToClipboard(output);
            }
        });
    }
}

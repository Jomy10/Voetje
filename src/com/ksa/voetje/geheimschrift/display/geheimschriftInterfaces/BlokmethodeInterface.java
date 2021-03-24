package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces;

import com.ksa.voetje.geheimschrift.geheimschrift.Blokmethode;
import com.ksa.voetje.methodes.StringToClipboard;
import com.ksa.voetje.opmaak.OpmaakConstants;
import com.ksa.voetje.opmaak.elements.Btn;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.InputField;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BlokmethodeInterface extends MethodScrollPane {
    // Elementen van de interface:
    private final Title blokTitel = new Title("Blokmethode"); // titel
    private final HoofdTekstLabel blokInstructies = new HoofdTekstLabel("Geef een zin in om te encoderen.");
    private final InputField inputField = new InputField("maximum aantal karakters per woord is 30.\nLeestekens worden ook meegerekend als deel van een woord"); // input venster
    private final HoofdTekstLabel outputDescriptie = new HoofdTekstLabel("\nBlokmethode:");
    private final HoofdTekstLabel outputLabel = new HoofdTekstLabel(); // output
    private final Btn readInputBtn = new Btn("Encoderen");

    // Panes
    private final Interface blokInterface = new Interface(
            OpmaakConstants.INTERFACE_SPACING,
            blokTitel, blokInstructies, inputField, readInputBtn, outputDescriptie, outputLabel
    );
    // Constructor
    public BlokmethodeInterface() {
        super();
        setContent(blokInterface);
        setButtonActions();
    }

    private void setButtonActions() {
        readInputBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Reading input...");
                String input = inputField.getText();
                Blokmethode blokmethode = new Blokmethode(input);
                String output = blokmethode.blokmethodeUitvoeren();
                if (output == null || output.equals("Exception error in Blokmethode"))
                    errorOngeldigTeken.showErrorMessage();
                else {
                    outputLabel.setText(output);
                    StringToClipboard stringToClipboard = new StringToClipboard();
                    stringToClipboard.pasteToClipboard(output);
                }
            }
        });
    }

    // errors
    private static final ErrorMessage errorOngeldigTeken = new ErrorMessage("Je input bevat een ongeldig teken.");
}

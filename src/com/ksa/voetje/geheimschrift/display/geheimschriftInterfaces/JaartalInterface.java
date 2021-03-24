package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces;

import com.ksa.voetje.geheimschrift.geheimschrift.Jaartal;
import com.ksa.voetje.opmaak.elements.*;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;
import com.ksa.voetje.methodes.Methods;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class JaartalInterface extends MethodScrollPane {
    // Elementen van de interface //
    private final Title titel = new Title("Jaartal");
    private final HoofdTekstLabel inputInstructiesJaar = new HoofdTekstLabel("Jaartal:");
    private final SmallInputField jaartalField = new SmallInputField("vb. 1990");
    private final HoofdTekstLabel inputInstructies = new HoofdTekstLabel("Typ de tekst die je wenst te encoderen naar het Jaartal geheimschrift. " +
            "\nJe tekst mag geen returns bevatten.");
    private final InputField inputField = new InputField();
    private final HoofdTekstLabel jaartalTekst = new HoofdTekstLabel("Jaar: "); // Aanpassen obv. jaar "Jaar: xxxx"
    private final HoofdTekstLabel outputTekst = new HoofdTekstLabel("\nGeheimschrift:");
    private final HoofdTekstLabel outputTekstJaar = new HoofdTekstLabel(); // Output
    private final Btn readInputButton = new Btn("Encoderen");

    // Panes //
    private final Interface jaarInterface = new Interface(
            INTERFACE_SPACING,
            titel, inputInstructiesJaar, jaartalField, inputInstructies, inputField,
            readInputButton, jaartalTekst, outputTekst, outputTekstJaar
    );
    public JaartalInterface() {
        super();
        setContent(jaarInterface);
        jaartalField.setTooltip(
                new Tooltip("Jaartal moet 4 letters bevatten")
        );
        setButtonActions();
    }

    private void setButtonActions() {
        readInputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                jaartalUitvoeren();
            }
        });
    }

    private void jaartalUitvoeren() {
        System.out.println("== Jaartal uitvoer ==");
        String zin = inputField.getText();
        System.out.println("Input = " + zin);
        String jaar = jaartalField.getText();
        System.out.println("jaar = " + jaar);
        Jaartal jaartal = new Jaartal(zin, jaar);

        String output = jaartal.getOutput();
        if (output == null) { // error
            ErrorMessage jaartalTeKort = new ErrorMessage("Je jaartal moet exact 4 karakters bevatten.");
            jaartalTeKort.showErrorMessage();
        } else {
            jaartalTekst.setText("Jaar: " + jaar);
            outputTekstJaar.setText(output);
            Methods.stringToClipboard(output);
        }
    }
}

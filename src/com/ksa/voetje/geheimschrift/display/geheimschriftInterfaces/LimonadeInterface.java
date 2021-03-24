package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces;

import com.ksa.voetje.geheimschrift.geheimschrift.Limonade;
import com.ksa.voetje.opmaak.elements.*;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;

import static com.ksa.voetje.opmaak.OpmaakConstants.FONT_SIZE;
import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;
import static com.ksa.voetje.methodes.Methods.stringToClipboard;

public class LimonadeInterface extends MethodScrollPane {
    // Interface Elementen //
    private final Title limoTitel = new Title("Limonade");
    private final HoofdTekstLabel inputInstructiesLimonade = new HoofdTekstLabel("Typ een woord om te gebruiken als limonade woord (mag niet 2 keer dezelfde letter bevatten).");
    private final SmallInputField limonadeInputField = new SmallInputField("limonade", "limonade");
    private final HoofdTekstLabel inputInstructies = new HoofdTekstLabel("Typ de tekst die je wenst te encoderen naar Limonade.");
    private final InputField inputField = new InputField();
    private final HoofdTekstLabel alfabetTekst = new HoofdTekstLabel("\nAlfabet:");
    private final HoofdTekstLabel alfabetLabel = new HoofdTekstLabel();
    private final HoofdTekstLabel outputTekst = new HoofdTekstLabel("\nLimonade:");
    private final HoofdTekstLabel outputTekstLimo = new HoofdTekstLabel();
    private final Btn readInputButton = new Btn("Encoderen");

    // Panes //
    private final Interface limoInterface = new Interface(
            INTERFACE_SPACING,
            limoTitel, inputInstructiesLimonade, limonadeInputField, inputInstructies, inputField,
            readInputButton, alfabetTekst, alfabetLabel, outputTekst, outputTekstLimo
    );

    public LimonadeInterface() {
        super();
        setContent(limoInterface);
        setButtonActions();
        alfabetLabel.setFont(Font.font ("Courier", FONT_SIZE));
    }

    private void setButtonActions() {
        readInputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String[] oplossing = getSolution();
                alfabetLabel.setText(oplossing[0]);
                outputTekstLimo.setText(oplossing[1]);
                stringToClipboard(oplossing[1]);
            }
        });
    }

    private String[] getSolution() {
        // get input
        String input = inputField.getText();
        String limo = limonadeInputField.getText();

        Limonade limonade = new Limonade(limo, input);
        if (!limonade.limonadeChecken())
            showErrorLimonade();
        String alfabet = limonade.returnAlfabet();
        String output = limonade.getOutput();
        return new String[]{alfabet, output};
    }

    // Dubbele letter in limonade error
    private final ErrorMessage errorDubbeleLetter = new ErrorMessage("Je limonade woord mag geen dubbele karakters bevatten!");
    private void showErrorLimonade() {
        errorDubbeleLetter.showErrorMessage();
    }

}

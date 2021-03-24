package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces;

import com.ksa.voetje.geheimschrift.geheimschrift.Morse;
import com.ksa.voetje.instellingen.MorseInstellingen;
import com.ksa.voetje.opmaak.elements.Btn;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.InputField;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class MorseInterface extends MethodScrollPane {
    // Elementen van de interface
    private final InputField inputField = new InputField();
    private final HoofdTekstLabel outputLabel = new HoofdTekstLabel(); // TODO: outputLabel naar tekst zodat niet helft van een woord op een andere alinea staat + mss class voor maken zodat over kan gebruiken
    private final Title morseTitel = new Title("Morse");
    private final HoofdTekstLabel instructieInvullenLabel = new HoofdTekstLabel("Geef de zin in om te encoderen naar Morse.");
    private final Btn readInputButton = new Btn("Encoderen");
    private final Btn leestekens = new Btn();
    private final Btn specialeTekens = new Btn();
    private final Btn onbekendeWaarden = new Btn();

    // Panes
    private final FlowPane buttonsPane = new FlowPane(
            readInputButton, leestekens, specialeTekens, onbekendeWaarden
    );
    private final Interface morseInterface = new Interface(
            INTERFACE_SPACING, morseTitel, instructieInvullenLabel, inputField, buttonsPane, new HoofdTekstLabel("\nMorse"), outputLabel
    );

    // Constructor
    public MorseInterface() {
        super();
        setContent(morseInterface);

        // Buttons
        setButtonActions();
        updateLeestekens();
        updateSpecialeTekens();
        updateOnbekendeWaarden();
    }

    // I/O
    private String getInput() {
        return inputField.getText();
    }

    public String encodeMessage() {
        Morse morse = new Morse(
                MorseInstellingen.getSpecialeTekens(),
                MorseInstellingen.getLeestekens(),
                MorseInstellingen.getOnbekendeWaarden(),
                getInput());
        return morse.morseUitvoeren();
    }

    private void setOutputLabel(String s) {
        outputLabel.setText(s);
    }

    // Buttons
    private void setButtonActions() {
        readInputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String output = encodeMessage();
                setOutputLabel(output);
            }
        });
        leestekens.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MorseInstellingen.setLeestekens();
                updateLeestekens();
            }
        });
        specialeTekens.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MorseInstellingen.setSpecialeTekens();
                updateSpecialeTekens();
            }
        });
        onbekendeWaarden.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MorseInstellingen.setOnbekendeWaarden();
                updateOnbekendeWaarden();
            }
        });
    }

    // Instellingen
    private void updateLeestekens() {
        String stateString;
        if (MorseInstellingen.getLeestekens())
            stateString = "aan";
        else
            stateString = "uit";
        leestekens.setText("Leestekens: " + stateString);
    }
    private void updateSpecialeTekens() {
        String stateString;
        if (MorseInstellingen.getSpecialeTekens())
            stateString = "aan";
        else
            stateString = "uit";
        specialeTekens.setText("Speciale tekens: " + stateString);
    }
    private void updateOnbekendeWaarden() {
        String stateString;
        if (MorseInstellingen.getOnbekendeWaarden())
            stateString = "aan";
        else
            stateString = "uit";
        onbekendeWaarden.setText("Onbekende waarden: " + stateString);
    }

    // Errors
    private static final ErrorMessage error1 = new ErrorMessage("Probeer om alle vraagtekens te verwijderen uit je tekst, als er geen vraagtekens in je tekst staan ligt het misschien aan een ander teken. (ik fix dit ooit wel)");
    public static void errorAlert() {
        error1.showErrorMessage();
    }

    private static final ErrorMessage error2 = new ErrorMessage("Het encoderen duurt te lang, probeer alle " +
            "speciale tekens te verwijderen om dit te vermijden. Als je tekst meer dan "
            + MorseInstellingen.morseMaxCharacters + " tekens bevat kan dit ook een oorzaak zijn van het probleem. " +
            "\nAls je meer dan " + MorseInstellingen.morseMaxCharacters
            + " tekens wil encodoren, dan kan je dit veranderen in instellingen " +
            "(maximaal aantal tekens is 32-bit integer = 2,147,483,647). " +
            "Als je het maximaal aantal tekens te hoog zet in instellingen, kan het zijn dat het programma lang vast " +
            "loopt bij een error.\nTekens die de oorzaak kunnen zijn van dit probleem is bijvoorbeeld '$'.");
    public static void errorAlert2() {
        error2.showErrorMessage();
    }

}

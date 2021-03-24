package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.geheimschrift.geheimschrift.Raamgeheimschrift;
import com.ksa.voetje.methodes.fileSystem.OpenFile;
import com.ksa.voetje.opmaak.elements.*;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class RaamgeheimschriftInterface extends MethodScrollPane {
    // Lettertype //
    private final HoofdTekstLabel infoLettertype = new HoofdTekstLabel("Je kan het lettertype voor het Raamgeheimschrift installeren en gebruiken in Word.");
    private final Btn download = new Btn("Installeer lettertype");

    // Elementen //
    private final Title titel = new Title("Raamgeheimschrift");
    private final HoofdTekstLabel instructieInvullen = new HoofdTekstLabel("Geef de zin in om te encoderen naar Raamgeheimschrift.");
    private final Btn saveImage = new Btn("Bewaar afbeelding");
    private final InputField inputField = new InputField();


    // Panes //
    private final Interface raamInterface = new Interface(INTERFACE_SPACING,
            titel, infoLettertype, download, instructieInvullen, inputField, saveImage
    );

    private final VBox vBox = new VBox(raamInterface);

    public RaamgeheimschriftInterface() {
        super();
        setContent(vBox);
        setButtonActions();
    }

    private void setButtonActions() {
        saveImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Raamgeheimschrift raamgeheimschrift = new Raamgeheimschrift(inputField.getText());
                raamgeheimschrift.saveAsPng();
            }
        });
        download.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OpenFile openFont = new OpenFile(System.getProperty("user.home")
                        + "/Library/Application Support/com.ksa/voetje/lettertypes/Raamgeheimschrift.otf");
                try {
                    openFont.openFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}

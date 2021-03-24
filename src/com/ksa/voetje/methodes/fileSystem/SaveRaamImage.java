package com.ksa.voetje.methodes.fileSystem;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.geheimschrift.geheimschrift.Raamgeheimschrift;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class SaveRaamImage {
    Raamgeheimschrift raamgeheimschrift ;
    public SaveRaamImage(Raamgeheimschrift raamgeheimschrift) {
        this.raamgeheimschrift = raamgeheimschrift;
    }

    public void saveImage() {
        if (raamgeheimschrift.checkIfFileExists()) {
            // file exists
            Voetje.getLogWindow().addToLog("File already exists.");

            Alert overwriteDialog = new Alert(Alert.AlertType.CONFIRMATION);
            overwriteDialog.setTitle("Bestand bestaat al");
            overwriteDialog.setHeaderText("Het bestand bestaat al.");
            overwriteDialog.setContentText("Wil je het bestand overschrijven?");

            Optional<ButtonType> result =overwriteDialog.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                saveImage();
            } else {
                // ... user chose CANCEL or closed the dialog
                Voetje.getLogWindow().addToLog("File wasn't created because the user chose not to to overwrite it.");
            }

        } else {
            // file does not exist
            Voetje.getLogWindow().addToLog("File does not exist yet.");
            saveImage();
        }
    }
}

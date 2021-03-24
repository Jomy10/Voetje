package com.ksa.voetje.geheimschrift.geheimschrift;

import com.ksa.voetje.methodes.fileSystem.SavingSystem;
import com.ksa.voetje.methodes.imageMaker.textImage.RaamgeheimschriftImageConstructor;

import java.io.File;
import java.io.IOException;

public class Raamgeheimschrift {
    private final RaamgeheimschriftImageConstructor raamImage;

    public Raamgeheimschrift(String input) {
        raamImage = new RaamgeheimschriftImageConstructor(input);
        raamImage.startTyping();
    }

    @Deprecated
    public void getSaveFolder() {
        raamImage.getSaveFolder();
        System.out.println(raamImage.getFolderPath());
    }

    public void clearFolderPath() {
        System.out.println("Clearing folder path...");
        raamImage.clearFolderPath();
    }

    public String getFolderPath() {
        return raamImage.getFolderPath();
    }

    public boolean checkIfFileExists() {
        return raamImage.checkIfFileExists();
    }

    public void saveAsPng() {
        raamImage.saveAsPng();
    }


}

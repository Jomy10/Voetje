package com.ksa.voetje.methodes.fileSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SaveFiles {
    private String pathToBeSavedTo;
    private String fileName;
    private File file;

    // Constructors //
    public SaveFiles(String path) {
        this.pathToBeSavedTo = path;
    }
    public SaveFiles() {
        this.pathToBeSavedTo = System.getProperty("user.home") + "/Library/Application Support/com.ksa/voetje";
    }

    // set path //
    public void setPathToBeSavedTo(String path) {
        this.pathToBeSavedTo = path;
    }
    public void setPathToBeSavedToToDefault() {
        this.pathToBeSavedTo = System.getProperty("user.home") + "/Library/Application Support/com.ksa/voetje";
    }
    public void setPathToBeSavedToRelativeToHome(String relPath) {
        this.pathToBeSavedTo = System.getProperty("user.home") + relPath;
    }

    // get Path //
    public String getSavedPath() {
        return this.pathToBeSavedTo;
    }

    // Set file name //
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Initialise File //
    /**
     * Requires a fileName and pathName to be initialised
     * @return true if the file could be initialised
     */
    public boolean initialiseFile() {
        if (pathToBeSavedTo != null && fileName != null) {
            file = new File(pathToBeSavedTo + "/" + fileName);
            return true;
        } else
            return false;
    }

    // Check if file already exists //
    private boolean checkIfAlreadyExists() {
        return file.exists();
    }

    // Save file //
    private void saveFile() {
        System.out.println("== Saving File ==");
        String fullPath = pathToBeSavedTo + "/" + fileName;
        System.out.println("fullPath = " + fullPath);


        CreateDirectory.createDirectory(pathToBeSavedTo);
        System.out.println("== FileOutputStream ==");
        FileOutputStream fout = null;
        System.out.println("FileOutputStream intitialised to be null.");
        try {
            System.out.println("Initialising FileOutputStream...");
            // fout = new FileOutputStream(getClass().getResource("/com/ksa/voetje/savedFiles/savedSettings.txt").toURI().toString());
            //fout = new FileOutputStream("src/com/ksa/voetje/savedFiles/savedSettings.txt");
            fout = new FileOutputStream(fullPath);
            //fout = new FileOutputStream(file);
            System.out.println("FileOutputStream succesfully initialised.");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in saveFile");
            e.printStackTrace();
        }


    }
}

package com.ksa.voetje.methodes.fileSystem;

import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class CopyResource {
    private InputStream resource;
    private String filePathToBeSaveTo;

    public CopyResource() {
    }

    // get Resource //
    /**
     * @param resourcePath ex. "/com/ksa/voetje/opmaak/lettertypes/Raamgeheimschrift-Regular.otf"
     */
    public void setResource(String resourcePath) {
        this.resource = getClass().getResourceAsStream(resourcePath);
        System.out.println("Resource intialised: " + resource);
    }

    /**
     * will be saved to /com.ksa/voetje/folderInVoetje...
     * @param folderInVoetje the folder in the working directory, includes the fileName
     */
    public void copyTo(String folderInVoetje, String FileName) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();

        String pathToBeSavedTo;
        if (os.contains("mac"))
            pathToBeSavedTo = System.getProperty("user.home") + "/Library/Application Support/com.ksa/voetje";
        else if (os.contains("windows"))
            pathToBeSavedTo = System.getProperty("user.home") + "\\Local Settings\\ApplicationData";
        else if (os.contains("linux"))
            pathToBeSavedTo = null; // TODO: how on Linux?
        else {
            ErrorMessage couldNotFindOS = new ErrorMessage("Je besturingssysteem wordt niet herkent. Gelieve cntact op te nemen in de help pagina om dit probleem op te lossen");
            couldNotFindOS.showAndWait();
            pathToBeSavedTo = null;
        }

        pathToBeSavedTo += "/" + folderInVoetje;
        filePathToBeSaveTo = pathToBeSavedTo + "/" + FileName;

        InputStream copyFrom = resource;
        Path copyTo = Paths.get(filePathToBeSaveTo);

        // Make directory //
        CreateDirectory.createDirectory(pathToBeSavedTo);

        // Check if file exists, otherwise keep //
        File copyToFile = new File(filePathToBeSaveTo);

        if (copyToFile.exists()) {
            System.out.println("File " + copyTo + " already exists.");
        } else {
            // Copy file to location
            Files.copy(resource, copyTo);
        }
    }

    public String getCopiedToPath() {
        return filePathToBeSaveTo;
    }


}

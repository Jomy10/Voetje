package com.ksa.voetje.methodes.imageMaker.textImage;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.methodes.fileSystem.FolderChooser;
import com.ksa.voetje.methodes.fileSystem.SavingMethod;
import com.ksa.voetje.methodes.fileSystem.SavingSystem;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RaamgeheimschriftImageConstructor implements SavingMethod {
    private final int width = 2480;
    private int height;
    private final String content;
    private final int amountOfLines;
    private final int fontSize = 90;
    private final TextImageConstructor textImageConstructor;
    private String[] linesToType;
    private static String folderPath;

    private SavingSystem savingSystem;

    public RaamgeheimschriftImageConstructor(String content) {
        this.content = content;
        this.amountOfLines = countLines();
        calculateHeight();
        textImageConstructor = new TextImageConstructor(width, height);

        Font raamFont2 = null;
        raamFont2 = Voetje.getRaamgeheimschriftFont();
        Voetje.getLogWindow().addToLog("Loaded Raamgeheimschrift font succesfully.");
        textImageConstructor.setFont(raamFont2/*new Font("Raamgeheimschrift", Font.PLAIN, fontSize)*/, Color.BLACK);
    }

    /**
     * Counts the amount of lines needed and seperates lines based on the amount of characters
     * The maximum is 37 characters per line.
     * @return amount of lines + linesToType is updated
     */
    private int countLines() {
        String[] lines = content.split("\r\n|\r|\n");
        int linesProcessed = 0;
        int linesToProcess = 0;

        // Calculate required lines
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 37) { // More than 37 characters on the line
                int linesNeeded = (int) Math.ceil(lines[i].length() / 37.0);
                System.out.println("Zin lengte: " + lines[i].length());
                System.out.println("For the next sentenc, there are " + linesNeeded + " lines needed.");
                for (int j = 0; j < linesNeeded; j++) {
                    linesToProcess++;
                    System.out.println("linesToProcess = " + linesToProcess);
                }
            } else { // Less than 37 characters
                linesToProcess++;
                System.out.println("linesToProcess = " + linesToProcess);
            }
        }
        System.out.println("Required lines: " + linesToProcess);

        linesToType = new String[linesToProcess];
        // Store required lines in linestToType
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 37) { // More than 37 characters on the line
                int linesNeeded = (int) Math.ceil(lines[i].length() / 37.0);
                for (int j = 0; j < linesNeeded; j++) {
                    if (j*37+37 > lines[i].length()) {
                        linesToType[linesProcessed] = lines[i].substring(j * 37, lines[i].length());
                        linesProcessed++;
                    } else {
                        linesToType[linesProcessed] = lines[i].substring(j * 37, j * 37 + 37);
                        linesProcessed++;
                    }
                }
            } else { // Less than 37 characters
                linesToType[linesProcessed] = lines[i];
                linesProcessed++;
                System.out.println("linesProcessed = " + linesProcessed);
            }
        }
        debug();
        return linesToType.length;
    }

    private void calculateHeight() {
        height = amountOfLines * (25 + fontSize) + 25;
    }

    /**
     * Starts making the image
     */
    public void startTyping() {
        for (int i = 0; i < linesToType.length; i++) {
            textImageConstructor.typeNextLine(linesToType[i]);
        }
        // Close the g2c context
        textImageConstructor.disposeOfGraphicsContext();
    }

    public boolean checkIfFileExists() {
        System.out.println(folderPath/* + "/" + imageName*/);
        return textImageConstructor.checkIfFileExists(folderPath /*+ "/" + imageName*/);
    }

    /**
     * Saves the image as a png, uses the folderPath and the imageName
     */
    public void saveAsPng() {
        //String fullPath = folderPath /*+ "/" + imageName*/;
        System.out.println("Saving raamgeheimschrift image as png...");
        savingSystem = new SavingSystem(this, 0);
    }

    /**
     */
    @Override
    public void saveImageThread(){
        System.out.println("Continuing...");

        File fileToSave = savingSystem.getFileToSave();
        String absolutePath = fileToSave.getAbsolutePath();
        if (!absolutePath.contains(".png"))
            absolutePath += ".png";

        // if exists, ask if overrid
        boolean fileExists = textImageConstructor.checkIfFileExists(absolutePath);

        if (fileExists) {
            int confirmationInput = JOptionPane.showConfirmDialog(null, "De afbeelidng bestaal al, wil je het overschrijven?");
            // 0=yes, 1=no, 2=cancel

            if (confirmationInput == 0) {
                // overschrijven
                try {
                    textImageConstructor.saveAsPng(absolutePath);
                    Voetje.getLogWindow().addToLog(this, "Bestand opgeslagen.");
                } catch (IOException e) {
                    Voetje.getLogWindow().addToLog(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
            else {
                // Niet overschrijven
                Voetje.getLogWindow().addToLog(this, "Saving cancelled.");
            }
        }
        else {
            // File bestond nog niet
            try {
                textImageConstructor.saveAsPng(absolutePath);
                Voetje.getLogWindow().addToLog(this, "Bestand opgeslagen.");
            } catch (IOException e) {
                Voetje.getLogWindow().addToLog(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Opens the folder chooser and saves the chosen folder in folderPath
     * @deprecated
     */
    public void getSaveFolder() {
        String[] args = null;
        // FolderChooser.main(args);
        //folderPath = FolderChooser.getFolderPath();
    }

    /**
     * Sets a name for the image, must contain the file extension
     */
    public static void appendPNG() {
        if (!folderPath.contains(".png")) {
            folderPath = folderPath + ".png";
        } else
            folderPath = folderPath;
        /* Old; setImageName
        imageName = name;
        if (!imageName.contains(".png")) {
            imageName = imageName + ".png";
        }
         */
    }

    private void debug() {
        System.out.println("=== DEBUG ===");
        for (int i = 0; i < linesToType.length; i++) {
            System.out.println("[" + i + "] " + linesToType[i]);
        }
    }

    public static void setFolderPath(String s) {
        folderPath = s;
        appendPNG();
    }

    public String getFolderPath() {
        System.out.println("Getting folderpath: " + folderPath);
        return folderPath;
    }

    public void clearFolderPath() {
        folderPath = null;
        System.out.println("folderPath = " + folderPath);
    }

    @Deprecated
    public static void main(String[] args) throws IOException {
        RaamgeheimschriftImageConstructor raamImage = new RaamgeheimschriftImageConstructor("test\nteste\nDit is een zin\nklopt!\nDit is een zin met meer dan 37 tekens, denk ik. Of toch niet, lolololololol");
        raamImage.startTyping();
        // raamImage.saveAsPng();
        raamImage.getSaveFolder();
        Scanner keyboard = new Scanner(System.in);
        //raamImage.imageName = keyboard.nextLine();
        System.out.println("saving png");
        raamImage.saveAsPng();
    }
}

package com.ksa.voetje.methodes.fileSystem;

import com.ksa.voetje.methodes.imageMaker.shapeImage.ChineesShapeImageConstructor;
import com.ksa.voetje.methodes.imageMaker.shapeImage.LadderImageConstructor;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;

// NEW //
public class SavingSystem {
    private JFileChooser fc;
    private File fileToSave;
    /**
     * A JPanel that shows the file explorer and lets you select a file/directory for saving purposes
     * It is always executed from a class that implements the SavingMethod interface
     */
    public SavingSystem(SavingMethod executedFrom, int objectType) {
        // TODO: the objects this is executed from have to implement an interface, so that in this method it can use the method from that interface
        /*
        ChineesShapeImageConstructor chinees = null;
        LadderImageConstructor ladder = null;
        if (objectType == 0) {
            chinees = (ChineesShapeImageConstructor) executedFrom;
        } else if (objectType == 1) {
            ladder = (LadderImageConstructor) executedFrom;
        }

         */
        JFrame jFrame = new JFrame();
        System.out.println("[SavingSystem] creating a file chooser...");
        jFrame.setTitle("Kies een locatie en een naam voor je afbeelding");
        jFrame.setMinimumSize(new Dimension(100,100));
        //jFrame.pack();
        //jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.println("[SavingSystem] Initialising file choser...");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jFrame.setAlwaysOnTop(true);

                //Create a file chooser
                fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // can select files and directories
                fc.setDialogTitle("Kies een map en een naam voor je bestand");
                System.out.println("[SavingSystem] file chooser created.");

                System.out.println("[SavingSystem] showing file chooser...");
                int userSelection = fc.showSaveDialog(jFrame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    fileToSave = fc.getSelectedFile();
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                    jFrame.dispose();
                    executedFrom.saveImageThread();
                } else if (userSelection == JFileChooser.CANCEL_OPTION) {
                    // User cancelled
                    fileToSave = null;
                    jFrame.dispose();
                } else { // JFileChoser.ERROR_OPTION
                    // error or user closes dialog
                    fileToSave = null;
                    jFrame.dispose(); // dispose of the frame
                }
            }
        });

        System.out.println("[SavingSystem] Ended without error.");
    }

    public File getFileToSave() {
        return fileToSave;
    }

    // Example //
    @Deprecated
    public static void main(String[] args) {
        //SavingSystem savingSystem = new SavingSystem(new Object());
        System.out.println("saving system created");
        //File file = savingSystem.getFileToSave();
    }

}



package com.ksa.voetje.methodes.imageMaker.shapeImage;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.geheimschrift.geheimschrift.cijferschriften.Cijferschrift;
import com.ksa.voetje.methodes.fileSystem.SavingMethod;
import com.ksa.voetje.methodes.fileSystem.SavingSystem;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class LadderImageConstructor implements SavingMethod {
    private final int width = 2480;
    private final int height;

    private final String input;
    private String[] linesToType;
    private int amountOfLines;
    private final double maxCharOnLine = 29.0;

    private SavingSystem savingSystem;

    private ShapeImageConstructor shapeImageConstructor;

    public LadderImageConstructor(String input) {
        System.out.println("[LadderImageConstructor] Initialising...");
        this.input = input;

        // Calculate height and create an empty image
        this.height = calculateHeight();
        System.out.println("Height of the image is: " + height);
        shapeImageConstructor = new ShapeImageConstructor(width, height);

        encoderen();
    }

    /**
     * Calculates the height of the image
     *
     * @return the height of the image
     */
    private int calculateHeight() {
        return calclulateLines() * (20 /* plaats tussen de lijnen */ + 65 /* Grootte van de vormen */) + 25;
    }

    /**
     * Counts the amount of lines needed and seperates lines based on the amount of characters
     * The maximum is maxCharactersOnLine characters per line.
     *
     * @return amount of lines + linesToType is updated
     */
    private int calclulateLines() {
        String[] lines = input.split("\r\n|\r|\n"); // splits the input based on new lines
        int linesProcessed = 0;
        int linesToProcess = 0;

        // Calculate required lines
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > maxCharOnLine) { // More than maxCharOnLine characters on the line
                int linesNeeded = (int) Math.ceil(lines[i].length() / maxCharOnLine);
                System.out.println("Zin lengte: " + lines[i].length());
                System.out.println("For the next sentenc, there are " + linesNeeded + " lines needed.");
                for (int j = 0; j < linesNeeded; j++) {
                    linesToProcess++;
                    System.out.println("linesToProcess = " + linesToProcess);
                }
            } else { // Less than maxCharOnLine characters
                linesToProcess++;
                System.out.println("linesToProcess = " + linesToProcess);
            }
        }
        System.out.println("Required lines: " + linesToProcess);

        linesToType = new String[linesToProcess];
        // Store required lines in linestToType
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > maxCharOnLine) { // More than maxCharOnLine characters on the line
                int linesNeeded = (int) Math.ceil(lines[i].length() / maxCharOnLine);
                for (int j = 0; j < linesNeeded; j++) {
                    if (j * maxCharOnLine + maxCharOnLine > lines[i].length()) {
                        linesToType[linesProcessed] = lines[i].substring(j * (int) maxCharOnLine, lines[i].length());
                        linesProcessed++;
                    } else {
                        linesToType[linesProcessed] = lines[i].substring(j * (int) maxCharOnLine, j * (int) maxCharOnLine + (int) maxCharOnLine);
                        linesProcessed++;
                    }
                }
            } else { // Less than maxCharOnLine characters
                linesToType[linesProcessed] = lines[i];
                linesProcessed++;
                System.out.println("linesProcessed = " + linesProcessed);
            }
        }

        amountOfLines = linesToType.length;
        return linesToType.length;
    }

    public void encoderen() {
        System.out.println("LadderImageConstructor.encoderen: Start encoderen...");
        int amountOfLinesProcessed = 0; // houd bij hoeveel lijnen er al volledig zijn op de image
        int amountOfNumbersProcessedOnTheLine = 0; // houd bij hoeveel cijfers er al staan op de huidige lijn, max maxCharOnLine

        System.out.println("Amount of lines to type: " + linesToType.length);
        // Per lijn
        for (int i = 0; i < linesToType.length; i++) {
            System.out.println("=== Processing line " + i + "... ===");
            System.out.println("amountOfLinesProcessed = " + amountOfLinesProcessed);
            Cijferschrift chineseCijferschrift = new Cijferschrift(linesToType[i], Cijferschrift.SoortCijferschrift.normaal, 0, null);
            String outputLine = chineseCijferschrift.encoderen();
            // berekening van streepjes en boogjes //
            // Kijken naar woorden apart
            String[] cijfers = outputLine.split(" "); // alle woorden apart opslaan in een array

            // Per cijfer
            for (String cijfer : cijfers) { // alle elementen van de array apart bekijken
                System.out.println(" == Processing number " + cijfer + "... ==");

                int processingCijfer = 0;
                try {
                    processingCijfer = Integer.parseInt(cijfer);
                } catch (NumberFormatException ex) {
                    Voetje.getLogWindow().addToLog("[ChineesShapeImageConstructor] Processing a non-character.");
                    if (cijfer.equals(" ") || cijfer.equals("")) {
                        processingCijfer = -1; // spaties
                    }
                    else if (cijfer.equals(".") || cijfer.equals("?") || cijfer.equals("!") | cijfer.equals(",") | cijfer.equals(";") | cijfer.equals(":")) {
                        processingCijfer = -2; // leestekens
                    }
                    else if (cijfer.equals("é") || cijfer.equals("à") || cijfer.equals("á") || cijfer.equals("è") || cijfer.equals("ç") || cijfer.equals("ß") || cijfer.equals("ù") ) {
                        switch (cijfer) {
                            case "è":
                            case "é": { processingCijfer = Integer.parseInt("5"); break;} // e
                            case "à":
                            case "á": { processingCijfer = Integer.parseInt("1"); break;} // a
                            case "ç": { processingCijfer = Integer.parseInt("3"); break;} // c
                            case "ß": { processingCijfer = Integer.parseInt("19"); break;} // s
                            case "ù": { processingCijfer = Integer.parseInt("21"); break;} // u
                            default : { processingCijfer = -3; break;} // omzetten naar een ander cijfer
                        }
                    }
                    else if (cijfer.equals(")") || cijfer.equals("(") || cijfer.equals("€") || cijfer.equals("&") || cijfer.equals("*")) {
                        processingCijfer = -4; // andere (ignore)
                    }
                    else {
                        if (ex.getLocalizedMessage().contains("For input string:")) {
                            ErrorMessage fouteTeken = new ErrorMessage("Er gebeurde een error tijdens het maken van een shape afbeelding voor het Ladder Cijferschrift." +
                                    "\nDe oorzaak is een ongeldig teken \"" + ex.getLocalizedMessage().charAt(19) + "\"." +
                                    "\nAls je dit teken graag zou willen gebruiken in de codering, gelieve feedback te sturen via de help tab."
                                    , ex, this);
                            fouteTeken.showAndWait();
                            processingCijfer = -98; // Foute teken
                        } else {
                            ErrorMessage onverwachteError = new ErrorMessage("Er gebeurde een onverwachte error tijdens het maken van een shape afbeelding voor het Ladder Cijferschrift.\nProbeer bepaalde tekens uit je tekst te verwijderen.", ex, this);
                            onverwachteError.showAndWait();
                            processingCijfer = -99; // unkown error
                        }
                    }
                } // Exception handler

                // Calculate what processes need to be done //
                /* processes array
                 * [0] aantal horizontale strepen
                 * [1] aantal verticale strepen
                 */
                int[] processes = new int[2];
                if (processingCijfer > 0) { // als een cijfer is
                    if (processingCijfer % 5 == 0) { // als deelbaar is door 5, Verticale streepjes zetten en telkens -5 doen tot aan 0
                        while (processingCijfer != 0) {
                            processes[1] += 1;
                            processingCijfer -= 5;
                        }
                    } else { // niet deelbaar door 5: horizontale strepen
                        while (processingCijfer % 5 != 0) {
                            processes[0] += 1;
                            processingCijfer -= 1;
                        }
                        while (processingCijfer % 5 == 0 && processingCijfer != 0) {
                            processes[1] += 1;
                            processingCijfer -= 5;
                        }
                    }
                }
                System.out.println("Calculated the amount of processes (shapes) needed");
                System.out.println("Processes: [0] - " + processes[0] + " [1] - " + processes[1]);

                // Drawing the shapes //
                int verticalHeight = 65;
                int horizontalWidth = 65;
                int plaatsTssStreepjes = 10;
                int plaatsTssCijfers = 20; // TODO
                int verticalePlaatsTssCijfers = 20;

                int marginBovenCijfers = 25;

                int amountOfVerticalLinesProcessed = 0;
                int amountOfHorizontalLinesProcessed = 0;

                int margin = 5;
                int plaatsTssHorizontaleStreepjes = 15;
                // Verticale lijnen
                // 65 = vertical line height -> 55 (margin of 5) / (4 - 1) (4 = max amount of horizontal lines) = 19,66
                while (processes[0] != 0) {
                    int x1, y1, x2, y2;
                    switch (processes[0]) {
                        case 1: {
                            x1 = 10 + amountOfNumbersProcessedOnTheLine * (plaatsTssCijfers + horizontalWidth);
                            y1 = marginBovenCijfers + margin + plaatsTssHorizontaleStreepjes + amountOfLinesProcessed * (verticalHeight + verticalePlaatsTssCijfers);
                            x2 = x1 + horizontalWidth;
                            y2 = y1;
                            break;
                        }
                        case 2: {
                            x1 = 10 + amountOfNumbersProcessedOnTheLine * (plaatsTssCijfers + horizontalWidth);
                            y1 = marginBovenCijfers + margin + plaatsTssHorizontaleStreepjes * 2 + amountOfLinesProcessed * (verticalHeight + verticalePlaatsTssCijfers);
                            x2 = x1 + horizontalWidth;
                            y2 = y1;
                            break;
                        }
                        case 3: {
                            x1 = 10 + amountOfNumbersProcessedOnTheLine * (plaatsTssCijfers + horizontalWidth);
                            y1 = marginBovenCijfers + margin + amountOfLinesProcessed * (verticalHeight + verticalePlaatsTssCijfers);
                            x2 = x1 + horizontalWidth;
                            y2 = y1;
                            break;
                        }
                        case 4: {
                            x1 = 10 + amountOfNumbersProcessedOnTheLine * (plaatsTssCijfers + horizontalWidth);
                            y1 = marginBovenCijfers + margin + plaatsTssHorizontaleStreepjes * 3 + amountOfLinesProcessed * (verticalHeight + verticalePlaatsTssCijfers);
                            x2 = x1 + horizontalWidth;
                            y2 = y1;
                            break;
                        }
                        default: {
                            x1 = x2 = y1 = y2 = 0; // default
                            Exception ex = new IllegalStateException("Unexpected value: " + processes[0]);
                            ErrorMessage illegalStateError = new ErrorMessage("Onverwachte waarde voor processes[0] in " + this.getClass() + ".", ex, this);
                            illegalStateError.showAndWait();
                        }
                    }
                    shapeImageConstructor.drawShape(0, x1, y1, x2, y2);
                    processes[0]--;
                }
                // verticale streepjes
                while (processes[1] != 0) {
                    int x1 = 25 + amountOfVerticalLinesProcessed * plaatsTssStreepjes + amountOfNumbersProcessedOnTheLine * (plaatsTssCijfers + horizontalWidth);
                    int y1 = marginBovenCijfers + amountOfLinesProcessed * (verticalHeight + verticalePlaatsTssCijfers);
                    int x2 = x1;
                    int y2 = y1 + verticalHeight;

                    shapeImageConstructor.drawShape(0, x1, y1, x2, y2);
                    processes[1]--;
                    amountOfVerticalLinesProcessed++;
                }

                // einde sub-process voor 1 letter
                amountOfNumbersProcessedOnTheLine++;
                if (processingCijfer == -1) {
                    amountOfNumbersProcessedOnTheLine++; // plaats tusssen laten
                }
                else if (processingCijfer == -99) {
                    amountOfNumbersProcessedOnTheLine++; // onverwachte error
                    // TODO: what else?
                }

            } // einde sub-process per lijn (for loop)
            System.out.println("End of sub-process (a line has been processed.");
            // volgende lijn neme
            amountOfLinesProcessed += 1;
            amountOfNumbersProcessedOnTheLine = 0;
        }
        System.out.println("End of sub-process (all lines have been processed).");
        shapeImageConstructor.disposeOfGraphicsContext();
    }

    // Opslaan //
    public void saveAsPng() {
        System.out.println("[" + this.getClass() + "] Saving ladder image as png...");
        savingSystem = new SavingSystem(this, 1);
    }

    @Override
    public void saveImageThread() {
        File fileToSave = savingSystem.getFileToSave();
        String absolutePath = fileToSave.getAbsolutePath();
        if (!absolutePath.contains(".png")) {
            absolutePath += ".png";
        }

        // als al bestaat, vragen of het overschreven moet worden
        boolean fileExists = shapeImageConstructor.checkIfFileExists(absolutePath);

        if (fileExists) {
            int confirmationInput = JOptionPane.showConfirmDialog(null, "De afbeelding bestaat al, wil je het overschrijven?");
            // 0=yes, 1=no, 2=cancel
            if (confirmationInput == 0) { // overschrijven
                try {
                    shapeImageConstructor.saveAsPng(absolutePath);
                    Voetje.getLogWindow().addToLog(this, "Bestand opgeslagen.");
                } catch (IOException ioException) {
                    Voetje.getLogWindow().addToLog(ioException.getLocalizedMessage());
                    ioException.printStackTrace();
                }
            } else // niet overschrijven
                Voetje.getLogWindow().addToLog(this, "Saving cancelled.");
        } else { // file didn't exist => save
            // Opslaan als png
            try {
                shapeImageConstructor.saveAsPng(absolutePath);
                Voetje.getLogWindow().addToLog(this, "Bestand opgeslagen.");
            } catch (IOException ioException) {
                Voetje.getLogWindow().addToLog(ioException.getLocalizedMessage());
                ioException.printStackTrace();
            }
        }
    }
}

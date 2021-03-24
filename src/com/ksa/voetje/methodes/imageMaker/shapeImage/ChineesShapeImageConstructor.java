package com.ksa.voetje.methodes.imageMaker.shapeImage;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.geheimschrift.geheimschrift.cijferschriften.Cijferschrift;
import com.ksa.voetje.methodes.fileSystem.SavingMethod;
import com.ksa.voetje.methodes.fileSystem.SavingSystem;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ChineesShapeImageConstructor implements SavingMethod {
   private final int width = 2480;
   private final int height;

   private final String input;
   private String[] linesToType;
   private int amountOfLines;
   private double maxCharOnLine = 27.0;

   private SavingSystem savingSystem;

   private ShapeImageConstructor shapeImageConstructor;

   // Constructor and its methods //
   public ChineesShapeImageConstructor(String input) {
      System.out.println("=== ChineesShapeImageConstructor initializing ===");
      this.input = input;

      // Calculate height and create an empty image
      this.height = calculateHeight();
      System.out.println("Height of the image is: " + height);
      shapeImageConstructor = new ShapeImageConstructor(width, height);

      encoderen();
   }

   /**
    * Calculates the height of the image
    * @return the height of the image
    */
   private int calculateHeight() {
      return calclulateLines() * (25 /* plaats tussen de lijnen */ + 90 /* Grootte van de lijnen */) + 25;
   }

   /**
    * Counts the amount of lines needed and seperates lines based on the amount of characters
    * The maximum is maxCharactersOnLine characters per line.
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
               if (j*maxCharOnLine+maxCharOnLine > lines[i].length()) {
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

   // Encoderen //
   public void encoderen() {
      System.out.println("Start encoderen chinese cijfers...");
      int amountOfLinesProcessed = 0; // houd bij hoeveel lijnen er al volledig zijn op de image
      int amountOfNumbersProcessedOnTheLine = 0; // houd bij hoeveel cijfers er al staan op de huidige lijn, max maxCharOnLine

      System.out.println("Amount of lines to type: " + linesToType.length);
      for (int i = 0; i < linesToType.length; i++) {
         System.out.println("=== Processing line " + i + "... ===");
         Cijferschrift chineseCijferschrift = new Cijferschrift(linesToType[i], Cijferschrift.SoortCijferschrift.normaal, 0, null);
         String outputLine = chineseCijferschrift.encoderen();
         // berekening van streepjes en boogjes //
         // Kijken naar woorden apart
         String[] cijfers = outputLine.split(" "); // alle woorden apart opslaan in een array
         for (String cijfer : cijfers) { // alle elementen van de array apart bekijken
            System.out.println(" == Processing number " + cijfer + "... ==");

            int processingCijfer = 0;
            try {
               processingCijfer = Integer.parseInt(cijfer);
            } catch (Exception ex) {
               Voetje.getLogWindow().addToLog("[ChineesShapeImageConstructor] Processing a non-character.");
               if (cijfer.equals(" ") || cijfer.equals("")) {
                  processingCijfer = -1; // spaties
               }
               else {
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Oeps, een error :(");
                  alert.setHeaderText("Een onverwachte error gebeurde tijdens het maken van een shape afbeelding.");
                  alert.setContentText("Er gebeurde een onverwachte error tijdens het maken van een shape afbeelding voor het Chinees Cijferschrift.");

                  StringWriter sw = new StringWriter();
                  PrintWriter pw = new PrintWriter(sw);
                  ex.printStackTrace(pw);
                  String exceptionText = sw.toString();
                  System.out.println("[ChineesShapeImageConstructor.encoderen]\n" + exceptionText);

                  javafx.scene.control.Label label = new Label("Exception stacktrace:");

                  javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea(exceptionText);
                  textArea.setEditable(false);
                  textArea.setWrapText(true);

                  textArea.setMaxWidth(Double.MAX_VALUE);
                  textArea.setMaxHeight(Double.MAX_VALUE);
                  GridPane.setVgrow(textArea, Priority.ALWAYS);
                  GridPane.setHgrow(textArea, Priority.ALWAYS);

                  GridPane expContent = new GridPane();
                  expContent.setMaxWidth(Double.MAX_VALUE);
                  expContent.add(label, 0, 0);
                  expContent.add(textArea, 0, 1);

                  // Set expandable Exception into the dialog pane.
                  alert.getDialogPane().setExpandableContent(expContent);

                  alert.showAndWait();
               }
            } // Exception handler
            /* processes array
             * [0] aantal horizontale strepen
             * [1] aantal verticale strepen
             * [2] aantal boogjes
             */
            int[] processes = new int[3];
            if (processingCijfer > 0) { // als een cijfer is
               if (processingCijfer % 5 == 0) { // als deelbaar is door 5, streepje zetten en telkens -5 doen tot aan 0
                  while (processingCijfer != 0) {
                     processes[2] += 1;
                     processingCijfer -= 5;
                  }
               } else { // als niet deelbaar door 5, dan streepjes zetten tot deelbaar is door 5, dan zie boven. Eerste streep is horizontaal
                  processes[0] = 1;
                  processingCijfer -= 1;
                  while (processingCijfer != 0) { // zolang we niet gedaan zijn
                     if (processingCijfer % 5 == 0) { // als deelbaar is door 5
                        processes[2] += 1;
                        processingCijfer -= 5;
                     } else { // niet deelbaar door 5
                        processes[1] += 1;
                        processingCijfer -= 1;
                     }
                  }
               }

               System.out.println("Calculated the amount of processes (shapes) needed");
               System.out.println("Processes: [0] - " + processes[0] + " [1] - " + processes[1] + " [2] - " + processes[2]);
               // Drawing the shapes //
               int heightOfShapes = 65;// hoogte van de tekens
               int plaatsTssVerticaleStreepjes = 10;
               int plaatsTussenCijfers = 90;

               int amountOfVerticalShapesProcessed = 0;

               if (processes[0] == 1) { // horizontale lijn nodig
                  // 67,027 plaats per cijfer => 65 -> 65 / 7 = 9,29 -> 9 per verticale streep/boog
                  int x1 = 10 /*plaats tss rand links en het cijfer*/ + amountOfNumbersProcessedOnTheLine * plaatsTussenCijfers;
                  int y1 = 25 /*plaats boven de letters*/ + amountOfLinesProcessed * (25 + heightOfShapes) + heightOfShapes / 2 /*midden van de verticale shapes*/; // TODO center
                  int x2 = 10 * (processes[1] + processes[2]) + x1 + 10 /*een functie van hoeveel streepjes en letters er nodig zijn + 10*/;
                  int y2 = y1 /*horizontale streep*/;
                  shapeImageConstructor.drawShape(0, x1, y1, x2, y2);
                  processes[0] = 0;
                  System.out.println("Horizontal line drawn.");
                  System.out.println("Processes: [0] - " + processes[0] + " [1]" + processes[1] + " [2]" + processes[2]);
               }

               while (processes[1] > 0 /*dus terwijl != 0*/) { // verticale streepjes zetten
                  int x1 = 1 + amountOfNumbersProcessedOnTheLine * plaatsTussenCijfers + 10 /*plaats tss begin horizontale streep en volgende verticale streep*/ + amountOfVerticalShapesProcessed * plaatsTssVerticaleStreepjes;
                  int y1 = 25 /*plaats boven de letters*/ + amountOfLinesProcessed * (25 + heightOfShapes) /*plaats boven de middelste streep*/;
                  int x2 = x1;
                  int y2 = y1 + heightOfShapes;
                  shapeImageConstructor.drawShape(0, x1, y1, x2, y2);
                  amountOfVerticalShapesProcessed++;
                  processes[1] -= 1;
                  System.out.println("Vertical line drawn.");
                  System.out.println("Processes: [0] - " + processes[0] + " [1]" + processes[1] + " [2]" + processes[2]);
               }

               while (processes[2] > 0) { // Bogen
                  int x1 = 1 + amountOfNumbersProcessedOnTheLine * plaatsTussenCijfers + 10 /*plaats tss begin horizontale streep en volgende verticale streep*/ + amountOfVerticalShapesProcessed * plaatsTssVerticaleStreepjes;
                  int y1 = 25 /*plaats boven de letters*/ + amountOfLinesProcessed * (25 + heightOfShapes) /*plaats boven de middelste streep*/;
                  int width = 10;
                  int height = heightOfShapes;
                  shapeImageConstructor.drawShape(1, x1, y1, width, height);
                  amountOfVerticalShapesProcessed++;
                  processes[2] -= 1;
                  System.out.println("Arch drawn.");
                  System.out.println("Processes: [0] - " + processes[0] + " [1]" + processes[1] + " [2]" + processes[2]);
               }

               // einde sub-process voor 1 letter
               amountOfNumbersProcessedOnTheLine++;
               if (amountOfNumbersProcessedOnTheLine == maxCharOnLine) { // go to the next line
                  amountOfLinesProcessed += 1;
                  amountOfNumbersProcessedOnTheLine = 0;
               }
               System.out.println("End of letter...\nAmount of numbers processed on this line: " + amountOfNumbersProcessedOnTheLine + "\nAmount of lines processed total: " + amountOfLinesProcessed);
            }
            else { // als processing cijfer = 0
               if (processingCijfer == -1) {
                  amountOfNumbersProcessedOnTheLine++; // plaats tusssen laten
               }
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
      System.out.println("Saving Chinees image as png...");
      savingSystem = new SavingSystem(this, 0);
   }

   @Override
   public void saveImageThread() {
      // Continue when savingSystem has chosen path
      System.out.println("Continuing...");

      File fileToSave = savingSystem.getFileToSave();
      String absolutePath = fileToSave.getAbsolutePath();
      if (!absolutePath.contains(".png"))
         absolutePath += ".png";

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
            Voetje.getLogWindow().addToLog("Bestand opgeslagen.");
         } catch (IOException ioException) {
            Voetje.getLogWindow().addToLog(ioException.getLocalizedMessage());
            ioException.printStackTrace();
         }
      }
   }
}

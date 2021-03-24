package com.ksa.voetje.startscherm;

import com.ksa.voetje.Main;
import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.geheimschrift.display.GeheimschriftDisplaySystem;
import com.ksa.voetje.opmaak.themes.ThemeDisplaySystem;
import com.ksa.voetje.instellingen.DisplayInstellingen;
import com.ksa.voetje.instellingen.MorseInstellingen;
import com.ksa.voetje.instellingen.previousSettings.PreviousSettings;
import com.ksa.voetje.methodes.downladWebContent.UpdateFinder;
import com.ksa.voetje.opmaak.elements.Btn;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tochtTechnieken.display.TochtTechniekenDisplaySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class StartGrid extends Stage {
    private static final BorderPane rootPane = new BorderPane();
    private static final javafx.scene.control.ScrollPane scrollPane = new ScrollPane();

    private static boolean gridInitialised = false;

    // Elements //
    // Encooderen
    Image encoderenImg;
    ImageView encoderenImgView;
    HoofdTekstLabel encoderenText;
    VBox encoderenBox = new VBox(/*8*/);

    // Tochttechnieken
    Image tochtImg;
    ImageView tochtImgView;
    HoofdTekstLabel tochtText;
    VBox tochtBox = new VBox();

    Btn sjorBtn = new Btn("Sjortechnieken");
    Btn kokenBtn = new Btn("Koken");
    Btn liedjesBtn = new Btn("Liedjes");
    Btn shopBtn = new Btn("Papieren versie");
    Btn instellingenBtn = new Btn("Instellingen");
    Btn feedbackBtn = new Btn("Feedback");
    //Image image = new javafx.scene.image.Image("res/startGrid/icons/encoderenTemp.png");
    //ImageView imageView = new ImageView(image);
    TilePane grid = new TilePane();

    // TODO: move initialization of other interfaces to this, in the buttons so that the program starts faster.
    // TODO: RootPane.getRootPane() -> setCenter the content of this interface

    // TODO: in instellingen automatically hide this window when one of the grid buttons has been pressed or not
    // TODO: tip of the day or something that displays somewhere (randomly chosen, can be disabled). have above TODO as a tip as well
    public StartGrid() {
        super();
        // TODO: initialise the display instellingen somewhere else than GeheimschriftDisplaysystem and use across the StartGrid and GeheimschriftDisplaySystem
        // TODO: instelling om default te openen als tabs of als nieuwe vensters
        double gap = 50;
        grid.getChildren().addAll(encoderenBox, tochtBox, sjorBtn, kokenBtn, liedjesBtn, shopBtn, feedbackBtn, instellingenBtn);
        grid.setHgap(gap); // Horizontal gap between each tile
        grid.setVgap(gap); // Vertical gap between each tile
        grid.setAlignment(Pos.CENTER);
        grid.setPrefColumns(3); // pref amount of columns
        // Tiles
        grid.setPrefTileHeight(120);
        grid.setPrefTileWidth(100);

        // init grid elements //
        new InitGrid().start();

        // Pane and scene
        rootPane.setCenter(grid);
        scrollPane.setContent(rootPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene scene = new Scene(scrollPane);
        this.setScene(scene);

        setWidth(450);
        setMinWidth(100);
        setMinHeight(325);

        setTitle("'t Voetje");
        show();

        // TODO: in settings, have an option to open this window as a sidebar (set to the side of the screen with width 100 and the height of the screen

        this.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent event) {
                int displaySettings = DisplayInstellingen.getSettings();
                String[] morseSettings = MorseInstellingen.getSettings();
                Main.getPreviousSettings().setTheme(displaySettings);
                Main.getPreviousSettings().serialize();

                System.exit(0);
            }
        });

        // Pas initialiseren nadat programma al is opgestart -> sneller
        new Stages().initStages();

        // Dan updates zoeken
        new Updates().start();
    }

    private class InitGrid extends Thread {
        @Override
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Encoderen //
                    encoderenImg = new Image("res/startGrid/icons/encoderen.png", 250,250, true, true, true);
                    encoderenImgView = new ImageView(encoderenImg);
                    encoderenText = new HoofdTekstLabel("Encoderen");
                    encoderenBox.getChildren().addAll(encoderenImgView, encoderenText);

                    encoderenBox.setAlignment(Pos.CENTER);

                    encoderenImgView.setPreserveRatio(true);
                    encoderenImgView.setFitHeight(100);
                    encoderenImgView.setFitWidth(100);

                    // Tochttechnieken //
                    tochtImg = new Image("res/startGrid/icons/tochttechnieken.png", 250, 250, true, true, true);
                    tochtImgView = new ImageView(tochtImg);
                    tochtText = new HoofdTekstLabel("Tochttechnieken");
                    tochtBox.getChildren().addAll(tochtImgView, tochtText);

                    tochtBox.setAlignment(Pos.CENTER);

                    tochtImgView.setPreserveRatio(true);
                    tochtImgView.setFitWidth(100);
                    tochtImgView.setFitWidth(100);

                    gridInitialised = true;
                }
            });
        }
    }

    private class Stages {
        private Stage encoderenStage;
        private Stage tochtStage;

        public void initStages() {
            InitStages initStages = new InitStages();
            initStages.start();

            initButtonActions initButtonActions = new initButtonActions();
            initButtonActions.start();
        }

        private class InitStages extends Thread {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        encoderenStage = new GeheimschriftDisplaySystem();
                        tochtStage = new TochtTechniekenDisplaySystem();
                    }
                });
            }
        }

        private class initButtonActions extends Thread {
            @Override
            public void run() {
                while (!gridInitialised) {
                    try {
                        sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Encoderen //
                encoderenImgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        encoderenStage.show();
                    }
                });
                encoderenImgView.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        encoderenImgView.setCursor(Cursor.HAND);
                    }
                });

                // TochtTechnieken //
                tochtImgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        tochtStage.show();
                    }
                });
                tochtImgView.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        tochtImgView.setCursor(Cursor.HAND);
                    }
                });

                /*
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        encoderenStage.show();
                    }
                });
                imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        imageView.setCursor(Cursor.HAND);
                    }
                });

                 */

                /*
                encoderenBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        encoderenStage.show();
                    }
                });

                 */
            }
        }
    }

    public BorderPane getPane() {
        return rootPane;
    }

    // TODO: fetch the latest version number and compare to current
    private static class Updates extends Thread {
        @Override
        public void run() {
            System.out.println("Running updates");
            UpdateFinder updateFinder = null;
            try {
                updateFinder = new UpdateFinder();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Voetje.addToLog(this.getName(), "Kon geen updates vinden");
            }

            assert updateFinder != null;
            String newUpdate = updateFinder.getNewUpdate();
            System.out.println("newUpdate = " + newUpdate);
            if (newUpdate != null) {
                new UpdatePanel(newUpdate, updateFinder);
            }
        }
    }

    static class UpdatePanel extends JPanel {
        public UpdatePanel(String update, UpdateFinder updateFinder) {
            super();
            JFrame updateWindow = new JFrame("Update");
            updateWindow.add(this, BorderLayout.NORTH);
            JTextArea updateText = new JTextArea(update);
            this.add(updateText, BorderLayout.NORTH);

            updateText.setLineWrap(true);
            Dimension SIZE = new Dimension(400,300);
            updateText.setEditable(false);
            updateText.setSize(SIZE);
            updateText.setMaximumSize(new Dimension(720,720));
            this.setSize(SIZE);
            updateWindow.setLocationRelativeTo(null);
            updateWindow.setSize(SIZE);
            updateWindow.setMinimumSize(SIZE);
            updateWindow.pack();
            updateWindow.setVisible(true);

            updateWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    updateFinder.setLastUpdateRead();
                }
            });
        }
    }
}

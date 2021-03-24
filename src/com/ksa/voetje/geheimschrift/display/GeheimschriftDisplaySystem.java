package com.ksa.voetje.geheimschrift.display;

import com.ksa.voetje.Main;
import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces.*;
import com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces.cijferschrift.CijferschriftInterface;
import com.ksa.voetje.geheimschrift.display.otherInterfaces.HelpInterface;
import com.ksa.voetje.geheimschrift.display.otherInterfaces.instellingen.InstellingenInterface;
import com.ksa.voetje.opmaak.themes.ThemeDisplaySystem;
import com.ksa.voetje.instellingen.previousSettings.PreviousSettings;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.SidebarButton;
import com.ksa.voetje.opmaak.elements.TekstZonderOpmaak;
import com.ksa.voetje.opmaak.interfaces.panes.SidebarScrollPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static com.ksa.voetje.opmaak.OpmaakConstants.*;

public class GeheimschriftDisplaySystem extends Stage {
    // Scene and stage
    private final RootPane rootPane = new RootPane();
    private final RootScene rootScene = new RootScene(rootPane, SCENE_WIDTH, SCENE_HEIGHT);

    // Theme
    private ThemeDisplaySystem themeDisplaySystem;

    // Settings
    private PreviousSettings previousSettings;

    private MorseInterface morseInterface;
    private BlokmethodeInterface blokmethodeInterface;
    private LimonadeInterface limonadeInterface;
    private JaartalInterface jaartalInterface;
    private RaamgeheimschriftInterface raamgeheimschriftInterface;
    private WindroosInterface windroosInterface;
    private WoordOmkerenInterface woordOmkerenInterface;
    private CijferschriftInterface cijferschriftInterface;
    private KijkKleurInterface kijkKleurInterface;
    private LetterVervangenInterface vervangInterface;
    private HelpInterface helpInterface;
    private InstellingenInterface instellingenInterface;

    public GeheimschriftDisplaySystem() {
        super();

        // Set sidebar
        rootPane.setLeft(sidebarScroll);
        initSidebar initSidebar = new initSidebar();
        initSidebar.start();

        // initialise the different interfaces //
        initInterfaces interfaceInitializer = new initInterfaces();
        interfaceInitializer.start();

        // Contents
        rootPane.setCenter(interfacePane);
        setStandardContents();

        // Primary stage //
        // Close request
        setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                // TODO: set startGrid visible when closing or hiding (setOnHiding)??
            }
        });

        // stage parameters
        setMinWidth(450);
        setMinHeight(325);

        setTitle("Encoderen");
        setScene(rootScene);

        // TODO: Disable full screen or change when in full screen

        // initialise settings and restore the previous settings //
        initSettings initSet = new initSettings();
        initSet.start();
    }

    /**
     * Initialises theme settings and restores the previous settings for the Geheimschrift window (morse and theme)
     */
    private class initSettings extends Thread {
        @Override
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Theme Display System Initialisation
                    Voetje.addToLog(this,"Initialising the Theme Display System...");
                    themeDisplaySystem = new ThemeDisplaySystem(rootPane);

                    Main.addThemeDisplaySystem(themeDisplaySystem);

                    // Deserialization //
                    // Previous settings initialisation
                    Voetje.addToLog(this, "Initialising previous settings...");
                    previousSettings = Main.getPreviousSettings();

                    // Restoring of morse max char
                    Voetje.addToLog(this, "Restoring previous settings for morse...");
                    previousSettings.restoreMorseSettings();

                    // Interface initialiseren //
                    instellingenInterface = new InstellingenInterface(themeDisplaySystem, getPreviousSettings());
                }
            });
        }
    }

    // Settings
    private PreviousSettings getPreviousSettings() {
        return previousSettings;
    }

    // Display Elements //
    // Sidebar
    private final VBox sidebar = new VBox(); // -> methods
    private final SidebarScrollPane sidebarScroll = new SidebarScrollPane(sidebar);

    private SidebarButton btnMorse;
    private SidebarButton btnLimo;
    private SidebarButton btnBlok;
    private SidebarButton btnJaar;
    private SidebarButton btnRaam;
    private SidebarButton btnWind;
    private SidebarButton btnOmkeren;
    private SidebarButton btnCijfer;
    private SidebarButton btnKijkKleur;
    private SidebarButton btnVervang;
    private SidebarButton btnInstellingen;
    private SidebarButton btnHelp;

    private class initSidebar extends Thread {
        @Override
        public void run() {
            // Avoid throwing IllegalStateException by running from a non-JavaFX thread.
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    btnMorse = new SidebarButton("Morse");
                    btnLimo = new SidebarButton("Limonade");
                    btnBlok = new SidebarButton("Blokmethode");
                    btnJaar = new SidebarButton("Jaartal");
                    btnRaam = new SidebarButton("Raam");
                    btnWind = new SidebarButton("Windroos");
                    btnOmkeren = new SidebarButton("Omkeren");
                    btnCijfer = new SidebarButton("Cijfer");
                    btnKijkKleur = new SidebarButton("Kijk-Kleur");
                    btnVervang = new SidebarButton("Vervangen");
                    btnInstellingen = new SidebarButton("Instellingen");
                    btnHelp = new SidebarButton("Help");

                    sidebar.setPrefWidth(SIDEBAR_WIDTH);
                    sidebar.getChildren().add(new TekstZonderOpmaak(" Geheimschrift", true));
                    sidebar.getChildren().addAll(btnMorse, btnLimo, btnBlok, btnJaar, btnRaam, btnWind, btnOmkeren, btnCijfer, btnKijkKleur, btnVervang, new Rectangle(SIDEBAR_BUTTON_WIDTH, 1, SIDEBAR_BUTTON_WIDTH, 1), btnHelp, btnInstellingen);

                    ButtonActions buttonActions = new ButtonActions();
                    buttonActions.start();
                }
            });
        }
    }

    private class ButtonActions extends Thread {
        @Override
        public void run() {
            // Avoid throwing IllegalStateException by running from a non-JavaFX thread.
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    btnMorse.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnMorse.setDisable(true);
                            rootPane.setCenter(morseInterface);
                        }
                    });

                    btnBlok.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnBlok.setDisable(true);
                            rootPane.setCenter(blokmethodeInterface);
                        }
                    });

                    btnLimo.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnLimo.setDisable(true);
                            rootPane.setCenter(limonadeInterface);
                        }
                    });

                    btnJaar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnJaar.setDisable(true);
                            rootPane.setCenter(jaartalInterface);
                        }
                    });

                    btnRaam.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnRaam.setDisable(true);
                            rootPane.setCenter(raamgeheimschriftInterface);
                        }
                    });

                    btnWind.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnWind.setDisable(true);
                            rootPane.setCenter(windroosInterface);
                        }
                    });

                    btnOmkeren.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnOmkeren.setDisable(true);
                            rootPane.setCenter(woordOmkerenInterface);
                        }
                    });

                    btnCijfer.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnCijfer.setDisable(true);
                            rootPane.setCenter(cijferschriftInterface);
                        }
                    });

                    btnKijkKleur.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnKijkKleur.setDisable(true);
                            rootPane.setCenter(kijkKleurInterface);
                        }
                    });

                    btnVervang.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnVervang.setDisable(true);
                            rootPane.setCenter(vervangInterface);
                        }
                    });

                    btnHelp.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnHelp.setDisable(true);
                            rootPane.setCenter(helpInterface);
                        }
                    });

                    btnInstellingen.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            reEnableAllButtons();
                            btnInstellingen.setDisable(true);
                            rootPane.setCenter(instellingenInterface);
                        }
                    });
                }
            });
        }
    }

    private void reEnableAllButtons() {
        btnMorse.setDisable(false);
        btnLimo.setDisable(false);
        btnBlok.setDisable(false);
        btnJaar.setDisable(false);
        btnRaam.setDisable(false);
        btnWind.setDisable(false);
        btnOmkeren.setDisable(false);
        btnCijfer.setDisable(false);
        btnKijkKleur.setDisable(false);
        btnVervang.setDisable(false);
        btnHelp.setDisable(false);
        btnInstellingen.setDisable(false);

        // close windows from methods
        if (kijkKleurInterface.isTableFrameIsVisible())
            kijkKleurInterface.closeTableFrame();
    }

    // Contents
    private static final FlowPane interfacePane = new FlowPane();
    public void setStandardContents() {
        HoofdTekstLabel instructies = new HoofdTekstLabel();
        instructies.setWrapText(true);
        instructies.setPrefWidth(INTERFACE_WIDTH);
        instructies.setText("\nKlik op een van de knoppen om te beginnen met encoderen.\nResultaten worden automatisch gekopieërd naar het klembord.");
        interfacePane.getChildren().clear();
        interfacePane.getChildren().add(instructies);
    }

    // Methods
    private class initInterfaces extends Thread {
        @Override
        public void run() {
            // Avoid throwing IllegalStateException by running from a non-JavaFX thread.
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    morseInterface = new MorseInterface();
                    limonadeInterface = new LimonadeInterface();
                    blokmethodeInterface = new BlokmethodeInterface();
                    jaartalInterface = new JaartalInterface();
                    raamgeheimschriftInterface = new RaamgeheimschriftInterface();
                    windroosInterface = new WindroosInterface();
                    woordOmkerenInterface = new WoordOmkerenInterface();
                    cijferschriftInterface = new CijferschriftInterface();
                    kijkKleurInterface = new KijkKleurInterface();
                    vervangInterface = new LetterVervangenInterface();
                    helpInterface = new HelpInterface();
                }
            });
        }
    }
}

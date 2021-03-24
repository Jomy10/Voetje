package com.ksa.voetje.geheimschrift.display.otherInterfaces.instellingen;

import com.ksa.voetje.opmaak.themes.ThemeDisplaySystem;
import com.ksa.voetje.instellingen.previousSettings.PreviousSettings;
import com.ksa.voetje.opmaak.elements.SidebarButton;
import com.ksa.voetje.opmaak.elements.TekstZonderOpmaak;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;

public class InstellingenInterface extends BorderPane {
    private final SidebarButton morseInstellingenButton = new SidebarButton("Morse");
    private final SidebarButton displaySettingsButtton = new SidebarButton("Display");
    private final SidebarButton debugInstellingenButton = new SidebarButton("Debug");

    private final ThemeDisplaySystem passthroughSystem;
    private final PreviousSettings previousSettings;

    private final VBox instellingenSidebar = new VBox(
            new TekstZonderOpmaak(" Instellingen", true),
            morseInstellingenButton,
            displaySettingsButtton,
            debugInstellingenButton);
    public InstellingenInterface(ThemeDisplaySystem passthroughSystem, PreviousSettings previousSettings) {
        this.previousSettings = previousSettings;
        this.passthroughSystem = passthroughSystem;
        setLeft(instellingenSidebar);
        setButtonActions();
    }

    private void setButtonActions() {
        displaySettingsButtton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                reenableAllButton();
                displaySettingsButtton.setDisable(true);
                DisplayInstellingenInterface displayInterface = new DisplayInstellingenInterface(passthroughSystem);
                setCenter(displayInterface);
            }
        });

        morseInstellingenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                reenableAllButton();
                morseInstellingenButton.setDisable(true);
                MorseInstellingenInterface morseInterface = new MorseInstellingenInterface(previousSettings);
                setCenter(morseInterface);
            }
        });

        debugInstellingenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                reenableAllButton();
                debugInstellingenButton.setDisable(true);
                DebugInstellingenInterface debugInterface = new DebugInstellingenInterface();
                setCenter(debugInterface);
            }
        });
    }

    private void reenableAllButton() {
        morseInstellingenButton.setDisable(false);
        displaySettingsButtton.setDisable(false);
        debugInstellingenButton.setDisable(false);
    }
}

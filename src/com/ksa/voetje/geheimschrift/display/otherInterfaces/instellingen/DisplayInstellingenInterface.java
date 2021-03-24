package com.ksa.voetje.geheimschrift.display.otherInterfaces.instellingen;

import com.ksa.voetje.Main;
import com.ksa.voetje.opmaak.themes.ThemeDisplaySystem;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class DisplayInstellingenInterface extends MethodScrollPane {
    private final Title displayTitle = new Title("Display");
    public final ThemeDisplaySystem themeDisplaySystem;

    private final FlowPane themePane = new FlowPane(new HoofdTekstLabel("Theme: "));
    private final Interface displayInterface = new Interface(INTERFACE_SPACING,
            displayTitle, themePane);
    public DisplayInstellingenInterface(ThemeDisplaySystem themeDisplaySystem) {
        this.themeDisplaySystem = themeDisplaySystem;
        setContent(displayInterface);
        themeList();
    }

    private void themeList() {
        ObservableList<String> themeOptions = FXCollections.observableArrayList(new String[]{"Default", "Light", "Dark"});
        ComboBox themeSelector = new ComboBox(themeOptions);
        themeSelector.setValue("Default");
        themePane.getChildren().add(themeSelector);

        themeSelector.setOnAction((event) -> {
            int selectedIndex = themeSelector.getSelectionModel().getSelectedIndex();
            Object selectedItem = themeSelector.getSelectionModel().getSelectedItem();
            System.out.println("[themeSelector] Selection made: [" + selectedIndex + "] " + selectedItem);
            switch(selectedIndex) {
                case 0:
                    if (themeDisplaySystem.isMacMenuBarDarkMode()) {
                        Main.setTheme(1);
                        com.ksa.voetje.instellingen.DisplayInstellingen.setThemeSettings(1);
                    } else {
                        Main.setTheme(0);
                        com.ksa.voetje.instellingen.DisplayInstellingen.setThemeSettings(0);
                    }
                    break;
                case 1:
                    Main.setTheme(0);
                    // Save setting in string
                    com.ksa.voetje.instellingen.DisplayInstellingen.setThemeSettings(0);
                    break;
                case 2:
                    Main.setTheme(1);
                    // Save setting in string
                    com.ksa.voetje.instellingen.DisplayInstellingen.setThemeSettings(1);
                    break;
            }
        });
    }


}

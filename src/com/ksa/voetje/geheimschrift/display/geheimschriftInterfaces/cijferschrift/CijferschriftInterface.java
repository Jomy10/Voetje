package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces.cijferschrift;

import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.InputField;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class CijferschriftInterface extends MethodScrollPane {
    // Elementen van de interface
    private static final HoofdTekstLabel instructies = new HoofdTekstLabel("Geef een zin in om te encoderen.");
    private static final InputField inputField = new InputField(); // input venster

    // Panes //
    // General
    private final FlowPane methodSelectorPane = new FlowPane();
    private final FlowPane methodSpecificInterfaces = new FlowPane();
    private final Interface cijferInterface = new Interface(INTERFACE_SPACING,
            new Title("Cijferschrift"), instructies, inputField, methodSelectorPane, methodSpecificInterfaces);
    public CijferschriftInterface() {
        super();
        setContent(cijferInterface);
        setMethodSelectorPane();
        setCijferschriftInterface();

    }

    public String getInput() {
        return inputField.getText();
    }

    private void setMethodSelectorPane() {
        ObservableList<String> methodOptions = FXCollections.observableArrayList("Cijferschrift", "Chinese cijfers", "Laddermethode");
        ComboBox methodSelector = new ComboBox(methodOptions);
        methodSelector.setValue("Cijferschrift");
        methodSelectorPane.getChildren().add(methodSelector);

        methodSelector.setOnAction((event) -> {
            int selectedIndex = methodSelector.getSelectionModel().getSelectedIndex();
            Object selectedItem = methodSelector.getSelectionModel().getSelectedItem();
            System.out.println("[methodOptions] Selection made: [" + selectedIndex + "] " + selectedItem);
            switch(selectedIndex) {
                case 0: // cijferschrift
                    setCijferschriftInterface();
                    break;
                case 1: // chinees
                        setChineesInterface();
                    break;
                case 2: // ladder
                        setLadderInterface();
                    break;
            }
        });
    }

    private void setCijferschriftInterface() {
        methodSpecificInterfaces.getChildren().clear();
        methodSpecificInterfaces.getChildren().add(new CijferschriftSpecificInterface(this));
    }

    private void setChineesInterface() {
        methodSpecificInterfaces.getChildren().clear();
        methodSpecificInterfaces.getChildren().add(new ChineseCijfersInterface(this));
    }

    private void setLadderInterface() {
        methodSpecificInterfaces.getChildren().clear();
        methodSpecificInterfaces.getChildren().add(new LaddermethodeInterface(this));
    }
}

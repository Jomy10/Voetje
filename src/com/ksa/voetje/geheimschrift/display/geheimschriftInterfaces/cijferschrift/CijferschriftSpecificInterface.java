package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces.cijferschrift;

import com.ksa.voetje.geheimschrift.geheimschrift.cijferschriften.Cijferschrift;
import com.ksa.voetje.methodes.StringToClipboard;
import com.ksa.voetje.opmaak.elements.Btn;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.SmallInputField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static com.ksa.voetje.opmaak.OpmaakConstants.*;

public class CijferschriftSpecificInterface extends VBox {
    // elementen
    private final HoofdTekstLabel cijfer = new HoofdTekstLabel("Cijferschrift: ");
    private final CijferschriftInterface cijferInterface;
    private final ObservableList<String> encodeerOpties = FXCollections.observableArrayList("a = 1", "offset", "zelf kiezen");
    private final ComboBox encodeerSelectie = new ComboBox(encodeerOpties);

    // Specific panes
    private final RegularPane regularPane = new RegularPane();
    private final OffsetPane offsetPane = new OffsetPane();
    private final AlfabetPane alfabetPane = new AlfabetPane();

    // Parameters
    private Cijferschrift.SoortCijferschrift soortCijferschrift;

    // Methods
    private final StringToClipboard stringToClipboard = new StringToClipboard();


    public CijferschriftSpecificInterface(CijferschriftInterface cijferInterface) {
        super(INTERFACE_SPACING);
        getChildren().addAll(/*cijfer, */encodeerSelectie, /* Hier nog iets zetten van welke letter welk cijfer is */regularPane);
        setMargin(encodeerSelectie, new Insets(10,0,3,0));
        this.cijferInterface = cijferInterface;
        this.soortCijferschrift = Cijferschrift.SoortCijferschrift.normaal; // DEFAULT value
        setButtonActions();
    }


    private void setButtonActions() {
        // selector
        encodeerSelectie.setValue("a = 1");

        encodeerSelectie.setOnAction((event) -> {
            int selectedIndex = encodeerSelectie.getSelectionModel().getSelectedIndex();
            Object selectedItem = encodeerSelectie.getSelectionModel().getSelectedItem();
            System.out.println("[methodOptions] Selection made: [" + selectedIndex + "] " + selectedItem);
            switch(selectedIndex) {
                case 0: // a = 1
                    this.soortCijferschrift = Cijferschrift.SoortCijferschrift.normaal;
                    setRegularInterface();
                    break;
                case 1: // offset
                    this.soortCijferschrift = Cijferschrift.SoortCijferschrift.offset;
                    setOffsetInterface();
                    break;
                case 2: // zelf kiezen
                    this.soortCijferschrift = Cijferschrift.SoortCijferschrift.eigenAlfabet;
                    setAlfabetInterface();
                    break;
            }
        });
    }

    private void setRegularInterface() {
        getChildren().remove(1);
        getChildren().add(regularPane);

    }
    private void setOffsetInterface() {
        getChildren().remove(1);
        getChildren().add(offsetPane);
    }
    private void setAlfabetInterface() {
        getChildren().remove(1);
        getChildren().add(alfabetPane);
    }


    /**
     * The specific pane for the regular method
     */
    class RegularPane extends VBox {
        private final Btn encoderen = new Btn("Encoderen");
        private final HoofdTekstLabel outputText = new HoofdTekstLabel();

        RegularPane() {
            super(5); // spacing
            getChildren().addAll(encoderen, outputText);
            setMargin(encoderen, new Insets(5,0,0,0));

            encoderen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Cijferschrift cijferschrift = new Cijferschrift(cijferInterface.getInput(), Cijferschrift.SoortCijferschrift.normaal, 0, null);
                    String output = cijferschrift.encoderen();
                    stringToClipboard.pasteToClipboard(output);
                    outputText.setText(output);
                }
            });
        }
    }

    /**
     * The specific pane for the offset method
     */
    class OffsetPane extends VBox {
        private final SmallInputField offsetInputField = new SmallInputField("Typ een cijfer. Als je bv. 2 hebt ingevuld, dan wordt a = 1 + 2, b = 2 + 2, ...");
        private final Btn encoderen = new Btn("Encoderen");
        private final HoofdTekstLabel outputText = new HoofdTekstLabel();

        OffsetPane() {
            super(5); // spacing
            getChildren().addAll(offsetInputField, encoderen, outputText);
            setMargin(offsetInputField, new Insets(5,0,0,0));

            encoderen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Cijferschrift cijferschrift = new Cijferschrift(cijferInterface.getInput(), Cijferschrift.SoortCijferschrift.offset, Integer.parseInt(offsetInputField.getText()), null);
                    String output = cijferschrift.encoderen();
                    stringToClipboard.pasteToClipboard(output);
                    outputText.setText(output);
                }
            });
        }
    }

    /**
     * The specific pane for the alfabet method
     */
    class AlfabetPane extends VBox {
        private final SmallInputField alfabetInputField = new SmallInputField("Typ een cijfer en druk op enter.");
        private final Btn encoderen = new Btn("Encoderen");
        private final HoofdTekstLabel outputTekst = new HoofdTekstLabel();
        private final HoofdTekstLabel alfabetTekst = new HoofdTekstLabel();

        private final BorderPane outputPane = new BorderPane();

        private int[] alfabet = new int[26];
        private int i = 0;

        AlfabetPane() {
            super(5); // spacing

            setMargin(alfabetInputField, new Insets(5, 0, 0, 0));
            setPrefWidth(INTERFACE_WIDTH - 40);
            setMinWidth(10);

            outputPane.setRight(alfabetTekst);
            outputPane.setCenter(outputTekst);
            BorderPane.setAlignment(outputTekst, Pos.TOP_LEFT);
            alfabetTekst.setFont(Font.font("Courier"));

            getChildren().addAll(alfabetInputField, encoderen, outputPane);

            alfabetInputField.setOnKeyReleased(event -> {
                if (event.getCode() == KeyCode.ENTER){
                    System.out.println(alfabetInputField.getText());
                    alfabet[i] = Integer.parseInt(alfabetInputField.getText());
                    alfabetTekst.setText(
                            "a = " + alfabet[0] +
                            "\nb = " + alfabet[1] +
                            "\nc = " + alfabet[2] +
                            "\nd = " + alfabet[3] +
                            "\ne = " + alfabet[4] +
                            "\nf = " + alfabet[5] +
                            "\ng = " + alfabet[6] +
                            "\nh = " + alfabet[7] +
                            "\ni = " + alfabet[8] +
                            "\nj = " + alfabet[9] +
                            "\nk = " + alfabet[10] +
                            "\nl = " + alfabet[11] +
                            "\nm = " + alfabet[12] +
                            "\nn = " + alfabet[13] +
                            "\no = " + alfabet[14] +
                            "\np = " + alfabet[15] +
                            "\nq = " + alfabet[16] +
                            "\nr = " + alfabet[17] +
                            "\ns = " + alfabet[18] +
                            "\nt = " + alfabet[19] +
                            "\nu = " + alfabet[20] +
                            "\nv = " + alfabet[21] +
                            "\nw = " + alfabet[22] +
                            "\nx = " + alfabet[23] +
                            "\ny = " + alfabet[24] +
                            "\nz = " + alfabet[25]
                    );
                    i++;
                    alfabetInputField.clear();
                    if (i == 26)
                        i = 0; // reset
                }
            });

            encoderen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Cijferschrift cijferschrift = new Cijferschrift(cijferInterface.getInput(), Cijferschrift.SoortCijferschrift.eigenAlfabet, 0, alfabet);
                    String output = cijferschrift.encoderen();
                    stringToClipboard.pasteToClipboard(output);
                    outputTekst.setText(output);
                }
            });
        }
    }
}

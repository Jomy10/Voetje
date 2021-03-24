package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.geheimschrift.geheimschrift.LettersVervangen;
import com.ksa.voetje.methodes.Methods;
import com.ksa.voetje.opmaak.OpmaakConstants;
import com.ksa.voetje.opmaak.elements.*;
import com.ksa.voetje.opmaak.elements.windows.ErrorMessage;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LetterVervangenInterface extends MethodScrollPane {
    // Cijferschrift met offset, maar dan cijfers terug vervangen door letters en als > 26, -26 doen en dan vervangen door letter

    // Elementen van de interface //
    private final HoofdTekstLabel instructies = new HoofdTekstLabel("Geef een zin in om te encoderen.");
    private final InputField inputField = new InputField(); // input venster
    private final HoofdTekstLabel outputLabel = new HoofdTekstLabel(); // output
    private final SmallInputField offsetField = new SmallInputField("Typ een cijfer. Als je bv. 2 hebt ingevuld, dan wordt a = c, b = d, ...");
    private final Btn readInputBtn = new Btn("Encoderen");

    // method 0
    private final ObservableList<String> methodOptions = FXCollections.observableArrayList("offset", "zelf kiezen");
    private ComboBox methodSelector = new ComboBox(methodOptions);

    // method 1
    private final Btn toonAlfabet = new Btn("Toon alfabet tabel");
    private final SmallInputField alfabetInputField = new SmallInputField("Typ een letter en druk op enter.");
        // Table
        private JFrame tableFrame;
        private final AlfabetTabelPanel tabelPanel = new AlfabetTabelPanel();
        private boolean tableFrameIsVisible = false;

        // setting table value
        int currentCol = 0;

    private int selectedMethod = 0; // 0 = offset, 1 = zelf kiezen

    // TODO: zelf alfabet invullen met tabel -> selector voor offset of zelf invullen
    // Panes //
    private final VBox specificInterface = new VBox(offsetField);
    private final Interface vervangInterface = new Interface(
            OpmaakConstants.INTERFACE_SPACING,
            new Title("Letters vervangen door andere"), instructies, inputField, methodSelector, specificInterface, readInputBtn, outputLabel
    );

    // Constructor //
    public LetterVervangenInterface() {
        super();
        setContent(vervangInterface);
        setButtonActions();
    }

    public void setButtonActions() {
        readInputBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (selectedMethod == 0) {
                    try {
                        LettersVervangen lettersVervangen = new LettersVervangen(inputField.getText(), Integer.parseInt(offsetField.getText())); // TODO add try catch -> give error message that offset has to be an integer
                        String output = lettersVervangen.encode();
                        Methods.stringToClipboard(output);
                        outputLabel.setText(output);
                    } catch (NumberFormatException e) {
                        ErrorMessage hasToBeInt = new ErrorMessage("De offset moet een getal zijn.");
                        hasToBeInt.showAndWait();
                    }
                }
                else if (selectedMethod == 1) {
                    LettersVervangen lettersVervangen = new LettersVervangen(inputField.getText(), getAlfabet());
                    String output = lettersVervangen.encode();
                    Methods.stringToClipboard(output);
                    outputLabel.setText(output);
                }
                else {
                    ErrorMessage unexpectedError = new ErrorMessage("Een onverwachte error gebeurde in [" + this.getClass() + "].");
                    unexpectedError.showAndWait();
                }
            }
        });

        toonAlfabet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // open table frame
                showTableFrame();
            }
        });

        methodSelector.setValue("offset");

        methodSelector.setOnAction((event) -> {
            int selectedIndex = methodSelector.getSelectionModel().getSelectedIndex();
            Object selectedItem = methodSelector.getSelectionModel().getSelectedItem();
            System.out.println("[methodOptions] Selection made: [" + selectedIndex + "] " + selectedItem);
            switch(selectedIndex) {
                case 0: // offset
                    specificInterface.getChildren().clear();
                    specificInterface.getChildren().add(offsetField);
                    selectedMethod = 0;
                    break;
                case 1: // zelf kiezen
                    specificInterface.getChildren().clear();
                    specificInterface.getChildren().addAll(toonAlfabet, alfabetInputField);
                    selectedMethod = 1;
                    break;
            }
        });

        alfabetInputField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                System.out.println(alfabetInputField.getText());
                setTableData(alfabetInputField.getText().charAt(0), currentCol);
                currentCol++;

                // update the table frame
                if (tableFrameIsVisible) {
                    closeTableFrame();
                    showTableFrame();
                }
                alfabetInputField.setText("");
            }
        });
    }

    // Alfabet tabel //
    /**
     * Returns the current table data as a 2-dimensional array char[][]
     * @return the table data
     */
    private char[][] getTableData() {
        return tabelPanel.getTableData();
    }

    /**
     * @return the current custom alphabet
     */
    private char[] getAlfabet() {
        return getTableData()[1];
    }

    /**
     * Sets a cell value in the alphabet table
     * @param cellValue the value that has to be inserted
     * @param col the column where the value has to be inserted
     */
    private void setTableData(char cellValue, int col) {
        tabelPanel.setCellValue(cellValue, 1, col);
    }

    /** Creates an new table frame and opens the window. */
    private void showTableFrame() {
        if (tableFrameIsVisible) {
            closeTableFrame();
        }
        Voetje.addToLog(this, "Opening table frame...");
        tableFrame = null;
        tableFrame = new JFrame();
        tableFrame.add(tabelPanel);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setTitle("Letter Vervangen Alfabet");
        // window close
        tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tableFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                tableFrameIsVisible = false;
            }
        });

        tableFrame.pack();
        tableFrame.setVisible(true);
        tableFrameIsVisible = true;
    }

    /**
     * Closes the table frame.
     */
    private void closeTableFrame() {
        tableFrame.dispose();
        tableFrameIsVisible = false;
    }

    /** @return true if the table is showing */
    public boolean isTableFrameIsVisible() {
        return tableFrameIsVisible;
    }

    /**
     * A JPanel that contains a JTable with the Alfabet model.
     */
    static class AlfabetTabelPanel extends JPanel {
        private final alfabetTableModel model = new alfabetTableModel();
        public AlfabetTabelPanel() {
            super(new GridLayout(1, 0));

            JTable table = new JTable(model);

            table.setTableHeader(null);
            table.setPreferredScrollableViewportSize(new Dimension(500, 50));
            table.setFillsViewportHeight(true);
            table.setAutoCreateRowSorter(false);

            // Create the scroll pane and add the table to it.
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to this panel.
            add(scrollPane);
        }

        public void setCellValue(char cellValue, int row, int column) {
            Object o = (Object) cellValue;
            model.setValueAt(o, row, column);
        }

        public char[][] getTableData() {
            return model.getTableData();
        }

        class alfabetTableModel extends DefaultTableModel {
            private char[][] tableData = {
                    {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o','p','q','r','s','t','u','v','w','x','y','z'},
                    {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o','p','q','r','s','t','u','v','w','x','y','z'}
            };

            @Override
            public int getRowCount() {
                return 2;
            }

            @Override
            public int getColumnCount() {
                return 26;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return tableData[rowIndex][columnIndex];
            }

            /*
             * Don't need to implement this method unless your table's
             * editable.
             */
            public boolean isCellEditable(int row, int col) {
                //Note that the data/cell address is constant,
                //no matter where the cell appears onscreen.
                return row != 0; // flase if row == 0, true else
            }

            @Override
            public void setValueAt(Object value, int row, int col) {
                String stringValue = value.toString();
                if (isCellEditable(row, col)) {
                    tableData[row][col] = Character.toLowerCase(stringValue.charAt(0));
                }
            }

            public char[][] getTableData() {
                return tableData;
            }
        }
    }
}

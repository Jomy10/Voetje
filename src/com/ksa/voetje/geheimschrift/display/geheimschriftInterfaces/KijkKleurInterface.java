package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.geheimschrift.geheimschrift.KijkKleur;
import com.ksa.voetje.methodes.Methods;
import com.ksa.voetje.opmaak.elements.Btn;
import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.InputField;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class KijkKleurInterface extends MethodScrollPane {
    // Elementen //
    // Table
    private JFrame tableFrame;
    private final KijkKleurTable kijkKleurTable = new KijkKleurTable();
    private final Btn showTableBtn = new Btn("Toon Kijk-Kleur tabel");
    private boolean tableFrameIsVisible = false;

    // other elements
    private final InputField inputField = new InputField();
    private final Btn encode = new Btn("Encoderen");
    private final HoofdTekstLabel outputText = new HoofdTekstLabel();

    Interface kijkKleurInterface = new Interface(INTERFACE_SPACING,
            new Title("KIJK-KLEUR methode"),
            new HoofdTekstLabel("Je kan aanpassen welke letters gebruikt worden om te coderen door ze aan te passen in de tabel:"),
            showTableBtn, inputField, encode, outputText);
    public KijkKleurInterface() {
        super();
        setContent(kijkKleurInterface);
        setButtonActions();
    }

    private void setButtonActions() {
        showTableBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showTableFrame();
            }
        });

        encode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                KijkKleur kijkKleur = new KijkKleur(getTableData(), inputField.getText()); // todo: read input
                String output = kijkKleur.encoderen();
                Methods.stringToClipboard(output);
                outputText.setText(output);
            }
        });
    }

    // Table //
    /**
     * Returns the current table data as a 2-dimensional array char[][]
     * @return the table data
     */
    public char[][] getTableData() {
        return kijkKleurTable.getTableData();
    }

    /**
     * Creates an new table frame and opens the window.
     */
    private void showTableFrame() {
        if (isTableFrameIsVisible()) {
            closeTableFrame();
        }
        Voetje.getLogWindow().addToLog(this, "Opening table frame...");
        tableFrame = null;
        tableFrame = new JFrame();
        tableFrame.add(kijkKleurTable);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setTitle("KIJK-KLEUR Tabel");
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
     * Closes the table frame
     */
    public void closeTableFrame() {
        tableFrame.dispose();
        tableFrameIsVisible = false;
    }

    /**
     *
     * @return true is the table is showing
     */
    public boolean isTableFrameIsVisible() {
        return tableFrameIsVisible;
    }

    /**
     * A JPanel that contains a JTable with the KIJK-KLEUR model
     */
    static class KijkKleurTable extends JPanel {
        private final KijkKleurTableModel model = new KijkKleurTableModel();
        public KijkKleurTable() {
            super(new GridLayout(1, 0));

            JTable table = new JTable(model);

            table.setTableHeader(null);
            table.setPreferredScrollableViewportSize(new Dimension(500, 100));
            table.setFillsViewportHeight(true);
            table.setAutoCreateRowSorter(false);

            //Create the scroll pane and add the table to it.
            JScrollPane scrollPane = new JScrollPane(table);

            //Add the scroll pane to this panel.
            add(scrollPane);
        }

        public void setCellValue(char cellValue, int row, int column) {
            Object o = (Object) cellValue;
            model.setValueAt(o, row, column);
        }

        public char[][] getTableData() {
            return model.getTableData();
        }

        class KijkKleurTableModel extends DefaultTableModel {
            private char[][] tableData = {
                    {'/','K','I','J','K','-'},
                    {'K','a','b','c','d','e'},
                    {'L','f','g','h','i','j'},
                    {'E','k','l','m','n','o'},
                    {'U','p','q','r','s','t'},
                    {'R','u','v','w','x','z'}
            };

            @Override
            public int getRowCount() {
                return 6;
            }

            @Override
            public int getColumnCount() {
                return 6;
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
                if (row == 0 && col == 0) // hoek
                    return false;
                if (row == 0 || col == 0)
                    return true;
                else
                    return false;
            }

            @Override
            public void setValueAt(Object value, int row, int col) {
                String stringValue = value.toString();
                if (isCellEditable(row, col)) {
                    tableData[row][col] = Character.toUpperCase(stringValue.charAt(0));
                }
            }

            public char[][] getTableData() {
                return tableData;
            }

        }
    }
}

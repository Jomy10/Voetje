package com.ksa.voetje.geheimschrift.geheimschrift;

import java.util.Arrays;

public class KijkKleur {
    private final char[][] tableData;
    private final String input;
    private final int aantalRijen;
    private final int aantalKolommen;

    public KijkKleur(char[][] tableData, String input) {
        this.tableData = tableData;
        // DEBUG: print 2d array
        System.out.println("TABLEDATA");
        System.out.println(Arrays.deepToString(this.tableData));
        this.input = input.toLowerCase();
        System.out.println("Input = " + this.input);
        this.aantalRijen = this.tableData.length;
        this.aantalKolommen = this.tableData[0].length;
    }

    public String encoderen() {
        StringBuilder output = new StringBuilder();

        char[] kijk = new char[aantalKolommen - 1];
        for (int i = 1; i < aantalRijen; i++) {
            kijk[i - 1] = tableData[0][i];
        }
        char[] kleur = new char[aantalRijen - 1];
        for (int i = 1; i < aantalRijen; i++) {
            kleur[i - 1] = tableData[i][0];
        }

        // DEBUG: print 1d array
        System.out.println("KIJK");
        System.out.println(Arrays.toString(kijk));
        System.out.println("KLEUR");
        System.out.println(Arrays.toString(kleur));

        /*
         * [kolom][rij]
         */
        char[][] alfabet = new char[aantalRijen - 1][aantalKolommen - 1];
        for (int i = 1; i < aantalKolommen; i++) {
            for (int j = 1; j < aantalRijen; j++) {
                alfabet[i-1][j-1] = tableData[i][j];
            }
        }
        // DEBUG
        System.out.println("ALFABET");
        System.out.println(Arrays.deepToString(alfabet));



        // input verwerken
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                output.append("\n");
            }
            else if (input.charAt(i) == ' ') {
                output.append(" ");
            }
            else {
                boolean charFound = false;
                for (int r = 0; r < aantalRijen - 1; r++) {
                    for (int k = 0; k < aantalKolommen - 1; k++) {
                        if (input.charAt(i) == alfabet[r][k]) {
                            System.out.println("Char = " + input.charAt(i) + ", k = " + k + ", r = " + r);
                            output.append(kijk[k]).append(kleur[r]);
                            if (i != input.length() - 1)
                                output.append("/");
                            charFound = true;
                            break;
                        }
                    }
                    if (charFound) {
                        break;
                    }
                }
                if (!charFound) {
                    output.append(input.charAt(i));
                    if (i != input.length() - 1)
                        output.append("/");
                }
            }
            // char found or char appended to output
        }
        return output.toString();
    }
}

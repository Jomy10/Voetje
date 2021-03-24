package com.ksa.voetje.geheimschrift.geheimschrift;

import com.ksa.voetje.debug.Kleuren;

public class Jaartal {
    private final String input;
    private final String jaar;
    private String output = "";

    public Jaartal(String input, String jaar) {
        this.input = input;
        this.jaar = jaar;
    }

    public String getOutput() {
        int j1 = -1;
        int j2 = -1;
        int j3 = -1;
        int j4 = -1;
        try {
            j1 = Integer.parseInt(jaar.substring(0, 1));
            j2 = Integer.parseInt(jaar.substring(1, 2));
            j3 = Integer.parseInt(jaar.substring(2, 3));
            j4 = Integer.parseInt(jaar.substring(3, 4));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(Kleuren.RED + e + Kleuren.RESET);
            return null;
        }
        System.out.println("Jaartal = " + j1 + j2 + j3 + j4);

        // Maximaal 100 jaartallen achter elkaar
        String[] jaar1 = new String[100];
        String[] jaar2 = new String[100];
        String[] jaar3 = new String[100];
        String[] jaar4 = new String[100];

        String zin = this.input;

        // spaties weghallen uit zin
        zin = zin.replaceAll(" ", "").toUpperCase();
        int i = 0;
        System.out.println("zin = " + zin);

        // zolang de zin niet volledig verwerkt is
        while (!zin.equals("")) {
            // zin groter dan jaartal
            if (j1 + j2 + j3 + j4 <= zin.length()) {
                System.out.println("Zin > sum of jaartal");
                jaar1[i] = zin.substring(0, j1);
                jaar2[i] = zin.substring(j1, j2 + j1);
                jaar3[i] = zin.substring(j2 + j1, j3 + j2 + j1);
                jaar4[i] = zin.substring(j3 + j2 + j1, j4 + j3 + j2 + j1);
                zin = zin.substring(j4 + j3 + j2 + j1);
            }
            // zin kleiner
            else if (j1 >= zin.length()) {
                System.out.println("j1 >= zi,");
                if (zin.length() == j1) {
                    jaar1[i] = zin.substring(0, j1);
                    zin = "";
                } else {
                    jaar1[i] = zin.substring(0, zin.length());

                    jaar1[i] = jaar1[i];
                    for (int j = 0; j < j1 - zin.length(); j++)
                        jaar1[i] += "X"; // Java 15: jaar1[i] = jaar1[i] + "X".repeat(j1 - zin.length());
                    zin = "";
                }
            } else if (j1 + j2 >= zin.length()) {
                System.out.println("j1 + j2 >= zin");
                if (zin.length() == j1 + j2) {
                    jaar1[i] = zin.substring(0, j1);
                    jaar2[i] = zin.substring(j1, j2 + j1);
                    zin = "";
                } else {
                    jaar1[i] = zin.substring(0, j1);
                    jaar2[i] = zin.substring(j1, zin.length());
                    for (int j = 0; j < j1 + j2 - zin.length(); j++)
                         jaar2[i] += "X";
                    System.out.println("jaar2[" + i + "] = " + jaar2[i]);
                    zin = "";
                }
            } else if (j1 + j2 + j3 >= zin.length()) {
                System.out.println("j1 + j2 + j3 >= zin");
                if (zin.length() == j1 + j2 + j3) {
                    jaar1[i] = zin.substring(0, j1);
                    jaar2[i] = zin.substring(j1, j2 + j1);
                    jaar3[i] = zin.substring(j2 + j1, j2 + j1 + j3);
                    zin = "";
                } else {
                    jaar1[i] = zin.substring(0, j1);
                    jaar2[i] = zin.substring(j1, j2 + j1);

                    jaar3[i] = zin.substring(j2 + j1, zin.length());
                    for (int j = 0; j < j1 + j2 + j3 - zin.length(); j++)
                        jaar3[i] += "X";

                    zin = "";
                }
            } else if (j1 + j2 + j3 + j4 > zin.length()) {
                // DEBUG
                int temp = j1 + j2 + j3 + j4;
                System.out.println("j1 + j2 + j3 + j4 = " + temp);
                System.out.println("lengte zin = " + zin.length());
                System.out.println("j1 + j2 + j3 + j4 > zin");
                //

                jaar1[i] = zin.substring(0, j1);
                jaar2[i] = zin.substring(j1, j2 + j1);
                jaar3[i] = zin.substring(j2 + j1, j2 + j1 + j3);


                jaar4[i] = zin.substring(j2 + j1 + j3, zin.length());
                for (int j = 0; j < j1 + j2 + j3 + j4 - zin.length(); j++)
                    jaar4[i] += "X";
                zin = "";
            }

            // DEBUG
            System.out.println(Kleuren.GREEN + "Jaartallen Schema: " + Kleuren.RESET);
            System.out.println("jaar1[" + i + "] = " + jaar1[i]);
            System.out.println("jaar2[" + i + "] = " + jaar2[i]);
            System.out.println("jaar3[" + i + "] = " + jaar3[i]);
            System.out.println("jaar4[" + i + "] = " + jaar4[i]);
            System.out.println("zin = " + zin);
            //

            i++;

        } // einde while loop
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k <= i; k++) {
                try {
                    output = output + jaar1[k].charAt(j);
                    System.out.println("1: " + output);
                } // jaar1
                catch (Exception ignored) {
                }
                try {
                    output = output + jaar2[k].charAt(j);
                    System.out.println("2: " + output);
                } catch (Exception ignored) {
                }
                try {
                    output = output + jaar3[k].charAt(j);
                } catch (Exception ignored) {
                }
                try {
                    output = output + jaar4[k].charAt(j);
                } catch (Exception ignored) {
                    // (Exception e) als iets willen doen als er een error is
                }
            }
            output = output + " ";
        } // einde for loop

        return output;
    }
}

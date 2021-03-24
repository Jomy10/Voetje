package com.ksa.voetje.geheimschrift.geheimschrift;

import com.ksa.voetje.debug.Kleuren;

public class Blokmethode {
    private String input;
    private int Xcount = 0;
    private boolean blokmethodeContinue = true;
    private int i = 0;

    public Blokmethode(String input) {
        this.input = input;
    }

    // Uitvoering
    public String blokmethodeUitvoeren() {
        String oplossing = "";
        // INPUT
        input = input.trim();
        System.out.println("User input: " + input);

        // Loop
        while (blokmethodeContinue) {
            System.out.println("\nFinding next word...");
            // Woord uit input halen
            try {
                System.out.println("Index of next line: " + input.indexOf("\n"));
                System.out.println("Index of next space: " + input.indexOf(" "));
                int spatie;
                boolean line = false;
                boolean lastWordOnLine = false;
                if (input.contains("\n") && input.contains(" ") && (input.indexOf("\n") < input.indexOf(" "))) {
                    System.out.println("next line < next space");
                    spatie = input.indexOf("\n");
                    lastWordOnLine = true;
                    line = true;
                }
                else if (input.contains("\n")) {
                    System.out.println("next line > next space");
                    spatie = input.indexOf(" ");
                    lastWordOnLine = false;
                    line = true;
                }
                else {
                    System.out.println("input contains no new lines");
                    spatie = input.indexOf(" ");
                    lastWordOnLine = false;
                    line = false;
                }
                System.out.println("Variable spatie = " + spatie);
                System.out.println("lastWordOnLine = " + lastWordOnLine);
                if (spatie < 0 && !line) {
                    spatie = input.length();
                    lastWordOnLine = false;
                }
                else if (spatie < 0 && line) {
                    spatie = input.indexOf("\n");
                    lastWordOnLine = true;
                }
                System.out.println("Variable spatie = " + spatie);
                String woord = input.substring(0, spatie);
                System.out.println("Processing woord: " + woord);
                input = input.replaceFirst(woord, "");
                System.out.println("Remaining: \"" + input + "\"");
                woord = woord.toUpperCase().trim();
                if (!input.equals(""))
                    input = input.substring(1, input.length());

                // vierkantswortel checken en X'en toevoegen aan woord
                int woordLengte = woord.length();
                while ((Math.sqrt(woordLengte) != Math.round(Math.sqrt(woordLengte))) || woordLengte == 1) {
                    Xcount++;
                    woordLengte++;
                }
                System.out.println("Xcount = " + Xcount);

                String woordX = woord;
                // X'en toevoegen aan woordX
                while (Xcount != 0) {
                    woordX = woordX + "X";
                    Xcount--;
                }

                int sqrt = (int) Math.sqrt(woordLengte);
                // oplossing in oplossing storen
                System.out.println("Processing woord...");
                switch (sqrt) {
                    case 2:
                        while (i != sqrt) {
                            oplossing = oplossing + String.valueOf(woordX.charAt(0 + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + i));
                            i++;
                            System.out.println("Current solution: " + oplossing);
                        }
                        break;
                    case 3:
                        while (i != sqrt) {
                            oplossing = oplossing + String.valueOf(woordX.charAt(0 + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + i));
                            i++;
                            System.out.println("Current solution: " + oplossing);
                        }
                        break;
                    case 4:
                        while (i != sqrt) {
                            oplossing = oplossing + String.valueOf(woordX.charAt(0 + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + sqrt + i));
                            i++;
                            System.out.println("Current solution: " + oplossing);
                        }
                        break;
                    case 5:
                        while (i != sqrt) {
                            oplossing = oplossing + String.valueOf(woordX.charAt(0 + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + sqrt + i));
                            i++;
                            System.out.println("Current solution: " + oplossing);
                        }
                        break;
                    case 6:
                        while (i != sqrt) {
                            oplossing = oplossing + String.valueOf(woordX.charAt(0 + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + sqrt + i));
                            oplossing = oplossing + String.valueOf(woordX.charAt(sqrt + sqrt + sqrt + sqrt + i));
                            i++;
                            System.out.println("Current solution: " + oplossing);
                        }
                        break;
                }

                Xcount = 0;
                i = 0;

                if (input.equals(""))
                    blokmethodeContinue = false;

                if (!lastWordOnLine)
                    oplossing = oplossing + " ";
                else
                    oplossing = oplossing + "\n";


            } catch (Exception e) {
                String exception = "Exception error in Blokmethode";
                System.out.println(Kleuren.RED + exception + Kleuren.RESET);
                return exception;
            }
        }

        // Oplossing uitprinten
        System.out.println(Kleuren.PURPLE + oplossing + Kleuren.RESET);
        blokmethodeContinue = true;

        return oplossing;
    }

}

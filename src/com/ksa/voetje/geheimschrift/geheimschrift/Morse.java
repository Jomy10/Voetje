package com.ksa.voetje.geheimschrift.geheimschrift;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.debug.Kleuren;
import com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces.MorseInterface;
import com.ksa.voetje.instellingen.MorseInstellingen;

public class Morse {
    private String input;
    private boolean morseContinue = true;
    private int processTime = 0;

    // instellingen
    private boolean specialeTekens = false;
    private boolean leestekens = false;
    private boolean onbekendeWaarden = true;

    // getters en setters instellingen
    public void setMorseContinue(boolean b) {
        morseContinue = b;
    }

    public Morse(String input) {
        this.input = input;
    }

    public Morse(boolean specialeTekens, boolean leestekens, boolean onbekendeWaarden, String input) {
        this.input = input;
        this.specialeTekens = specialeTekens;
        this.leestekens = leestekens;
        this.onbekendeWaarden = onbekendeWaarden;
    }

    // Morse uitvoeren
    public String morseUitvoeren() {
        System.out.println("=== MORSE ===");
        input = input.toLowerCase();
        System.out.println("input morse: " + input);
        String output = "";

        // LOOP
        System.out.println("input = " + input);
        while (morseContinue) {
            char currentLetter = input.charAt(0);
            try {
                input = input.replaceFirst(String.valueOf(currentLetter), "");
            } catch (java.util.regex.PatternSyntaxException e1) {
                try {
                    input = input.replaceAll(String.valueOf("\\" + currentLetter), "");
                } catch (Exception e2) {
                    Voetje.getLogWindow().addToLog(Kleuren.RED + "Error in uitvoering van Morse bij 2de poging. \n" + Kleuren.RESET);
                    MorseInterface.errorAlert();
                    input = "";
                    e2.printStackTrace();
                }
            } catch (Exception e) {
                Voetje.getLogWindow().addToLog(Kleuren.RED + "Error in uitvoering van Morse \n" + Kleuren.RESET);
                MorseInterface.errorAlert();
                e.printStackTrace();

            }

            System.out.println("currentLetter = " + currentLetter);
            System.out.println("input = " + input);

            // currentLetter omzetten naar morse in output
            switch (currentLetter) {
                case 'a' : { output = output + ".- "; break;}
                case ('b') : { output = output + "-... "; break;}
                case ('c') : { output = output + "-.-. "; break;}
                case ('d') : { output = output + "-.. "; break;}
                case ('e') : { output = output + ". "; break;}
                case ('f') : { output = output + "..-. "; break;}
                case ('g') : { output = output + "--. "; break;}
                case ('h') : { output = output + ".... "; break;}
                case ('i') : { output = output + ".. "; break;}
                case ('j') : { output = output + ".--- "; break;}
                case ('k') : { output = output + "-.- "; break;}
                case ('l') : { output = output + ".-.. "; break;}
                case ('m') : { output = output + "-- "; break;}
                case ('n') : { output = output + "-. "; break;}
                case ('o') : { output = output + "--- "; break;}
                case ('p') : { output = output + ".--. "; break;}
                case ('q') : { output = output + "--.- "; break;}
                case ('r') : { output = output + ".-. "; break;}
                case ('s') : { output = output + "... "; break;}
                case ('t') : { output = output + "- "; break;}
                case ('u') : { output = output + "..- "; break;}
                case ('v') : { output = output + "...- "; break;}
                case ('w') : { output = output + ".-- "; break;}
                case ('x') : { output = output + "-..- "; break;}
                case ('y') : { output = output + "-.-- "; break;}
                case ('z') : { output = output + "--.. "; break;}
                case ('1') : { output = output + ".---- "; break;}
                case ('2') : { output = output + "..--- "; break;}
                case ('3') : { output = output + "...-- "; break;}
                case ('4') : { output = output + "....- "; break;}
                case ('5') : { output = output + "..... "; break;}
                case ('6') : { output = output + "-.... "; break;}
                case ('7') : { output = output + "--... "; break;}
                case ('8') : { output = output + "---.. "; break;}
                case ('9') : { output = output + "----. "; break;}
                case ('0') : { output = output + "----- "; break;}
                case (' ') : { output = output + "/ "; break;}
                case ('.') : {
                    if (!leestekens)
                        output = output + "// ";
                    else
                        output = output + "/ .-.-.- ";
                    break;
                }
                case ('?') : {
                    if (!leestekens)
                        output = output + "// ";
                    else
                        output = output + "/ ..--.. ";
                    break;
                } // vraagteken
                case ('!') : {
                    if (!leestekens)
                        output = output + "// ";
                    else
                        output = output + "/ -.-.-- ";
                    break;
                }
                case (',') : {
                    if (!leestekens)
                        output = output + ", ";
                    else
                        output = output + "/ --..-- ";
                    break;
                }
                case ('-') : {
                    if (!specialeTekens)
                        output = output + "'-' ";
                    else
                        output = output + "/ -....- ";
                    break;
                }
                case ('/') : {
                    if (!specialeTekens)
                        output = output + "'/' ";
                    else
                        output = output + "/ -..-.";
                    break;
                }
                case (':') : {
                    if (!specialeTekens)
                        output = output + ": ";
                    else
                        output = output + "/ ---... ";
                    break;
                }
                case ('\'') : {
                    if (!specialeTekens)
                        output = output + "\' ";
                    else
                        output = output + "/ .----. ";
                    break;
                }
                case (')') : {
                    if (!specialeTekens)
                        output = output + ") ";
                    else
                        output = output + "/ -.--.- ";
                    break;
                }
                case ('(') : {
                    if (!specialeTekens)
                        output = output + "( ";
                    else
                        output = output + "/ -.--. ";
                    break;
                }
                case (';') : {
                    if (!specialeTekens)
                        output = output + "; ";
                    else
                        output = output + "/ -.-.- ";
                    break;
                }
                case ('=') : {
                    if (!specialeTekens)
                        output = output + "= ";
                    else
                        output = output + "/ -...- ";
                    break;
                }
                case ('@') : {
                    if (!specialeTekens)
                        output = output + "@ ";
                    else
                        output = output + "/ .--.-. ";
                    break;
                }
                case ('\n') : { output += "\n"; break;}
                default : {
                    if (onbekendeWaarden)
                        output = output + " ? ";
                    else
                        output = output + "";
                    break;
                }

            }

            // Stop process als het te lang duurt
            processTime++;
            System.out.println(Kleuren.PURPLE_BACKGROUND_BRIGHT + "ProcessTime: " + processTime + Kleuren.RESET);
            if (processTime > MorseInstellingen.morseMaxCharacters) {
                System.out.println(Kleuren.RED + "ERROR 3: Er is een ongeldig teken in je input waardoor het programma oneindig lang blijft runnen, " +
                        "of je input bevat meer dan 10000 karakters." + Kleuren.RESET);
                MorseInterface.errorAlert2();
                morseContinue = false;
                processTime = 0;
            }

            System.out.println("Input: " + input);
            System.out.println("Output: " + output);

            // stop LOOP
            if (input.equals(""))
                morseContinue = false;
        }
        morseContinue = true;
        System.out.println(Kleuren.PURPLE + output + Kleuren.RESET);
        return output;
    }

}

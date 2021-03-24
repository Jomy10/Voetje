package com.ksa.voetje.geheimschrift.geheimschrift;

public class Windroos {
    private String input;

    public Windroos(String input) {
        this.input = input;
        this.input = input.toLowerCase();
    }

    public String solve() {
        StringBuilder output = new StringBuilder();
        boolean previousWasSpace = false;

        for (int i = 0; i < input.length(); i++) {
            boolean charIsSpace = false;
            switch (input.charAt(i)) {
                case 'a': { output.append("NNNO"); break;}
                case 'b': { output.append("NONNO"); break;}
                case 'c': { output.append("NOONO"); break;}
                case 'd': { output.append("OONO"); break;}
                case 'e': { output.append("OOZO"); break;}
                case 'f': { output.append("ZOOZO"); break;}
                case 'g': { output.append("ZOZZO"); break;}
                case 'h': { output.append("ZZZO"); break;}
                case 'i': { output.append("ZZZW"); break;}
                case 'j': { output.append("ZWZZW"); break;}
                case 'k': { output.append("ZWWZW"); break;}
                case 'l': { output.append("WWZW"); break;}
                case 'm': { output.append("WWNW"); break;}
                case 'n': { output.append("NWWNW"); break;}
                case 'o': { output.append("NWNNW"); break;}
                case 'p': { output.append("NNNW"); break;}
                case 'q': { output.append("NNNO*"); break;}
                case 'r': { output.append("NONNO*"); break;}
                case 's': { output.append("NOONO*"); break;}
                case 't': { output.append("OONO*"); break;}
                case 'u': { output.append("OOZO*"); break;}
                case 'v': { output.append("ZOOZO*"); break;}
                case 'w': { output.append("ZOZZO*"); break;}
                case 'x': { output.append("ZZZO*"); break;}
                case 'y': { output.append("ZZZW*"); break;}
                case 'z': { output.append("ZWZZW*"); break;}
                default : {
                    if (!previousWasSpace)
                        output.append("/");
                    charIsSpace = true;
                    break;
                }
            }

            // Om te zorgen dat er een / gezet wordt na het laatste woord
            if (i == input.length() - 1) {
                output.append("/");
                charIsSpace = true;
            }

            if (!charIsSpace) {
                previousWasSpace = false;
                output.append(".");
            }
            else
                previousWasSpace = true; // so this one is space

        }
        return output.toString();
    }
}


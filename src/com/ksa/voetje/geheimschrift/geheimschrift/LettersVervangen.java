package com.ksa.voetje.geheimschrift.geheimschrift;

import com.ksa.voetje.geheimschrift.geheimschrift.cijferschriften.Cijferschrift;

public class LettersVervangen {
    private final String input;
    private final char[] alfabet;
    private final char[] gewoneAlfabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o','p','q','r','s','t','u','v','w','x','y','z'};

    /**
     * Initialises a letters vervangen encoding method with input input and an alfabet
     * @param input de meegegeven input
     * @param alfabet a = alfabet[0], b = alfabet[1], ...
     */
    public LettersVervangen(String input,  char[] alfabet) {
        this.input = input;
        this.alfabet = alfabet;
    }

    /**
     * Initialises a letters vervangen encoding method with input input and an offset
     * that changes the alfabet
     * @param input de meegegeven input
     * @param offset als 1, dan a = b, b = c, ..., z = a
     */
    public LettersVervangen(String input, int offset) {
        this.input = input;
        Cijferschrift cijferschrift = new Cijferschrift(null, Cijferschrift.SoortCijferschrift.offset, offset, null);
        this.alfabet = cijferschrift.calculateAlphabet();
    }

    public String encode() {
        StringBuilder output = new StringBuilder();

        // kijken per letter
        for (int i = 0; i < input.length(); i++) {
            boolean letterGevonden = false;
            // zoeken in alfabet waar letter is
            for (int j = 0; j < alfabet.length; j++) {
                if (gewoneAlfabet[j] == input.charAt(i)) {
                    output.append(alfabet[j]);
                    letterGevonden = true;
                    break;
                }
            }
            if (!letterGevonden) {
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }
}

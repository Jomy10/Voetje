package com.ksa.voetje.geheimschrift.geheimschrift.cijferschriften;

public class Cijferschrift {
    public enum SoortCijferschrift {
        normaal, // a = 1
        offset, //
        eigenAlfabet
    }
    private final String input;
    private int offset;
    private final SoortCijferschrift soortCodering;
    private int[] alfabet = null;
    public Cijferschrift(String input, SoortCijferschrift soortCijferschrift, int offset /* DEFAULT = 0 */, int[] alfabet/* Beginnend vanaf a = alfabet[0], ... */) {
        this.input = input;
        this.offset = 0;
        this.soortCodering = soortCijferschrift;

        switch (soortCijferschrift) {
            case normaal:
                offset = 0;
                break;
            case offset:
                this.offset = offset;
                break;
            case eigenAlfabet:
                this.alfabet = alfabet;
                break;
        }
    }

    public String encoderen() {
        // TODO: een seperator voor spaties maken ofzo zodat tussen woorden een seperator hebben
        StringBuilder output = new StringBuilder();

        char currentLetter;
        int currentLetterInInt;

        if (this.soortCodering.equals(SoortCijferschrift.normaal) || this.soortCodering.equals(SoortCijferschrift.offset)) {
            for (int i = 0; i < input.length(); i++) {
                currentLetter = input.charAt(i);
                currentLetterInInt = Character.toLowerCase(currentLetter) - 'a' + 1 + offset;
                System.out.println(currentLetterInInt);
                if (currentLetterInInt < 1 + offset || currentLetterInInt > 26 + offset) // not a letter => append in char form
                    output.append(currentLetter); // TODO: cijfers in een andere vorm schrijven zodat niet verward kunnen worden met letters in cijfer vorm -> char naar int van een cijfer is de cijfer zelf! (mss ⓵ of ⑴ ofzo of italic?)
                else
                    output.append(currentLetterInInt);
                output.append(" ");
            }
        } else { // alfabet
            for (int i = 0; i < input.length(); i++) {
                currentLetter = input.charAt(i);
                currentLetterInInt = Character.toLowerCase(currentLetter) - 'a';
                System.out.println(currentLetterInInt);
                if (currentLetterInInt < 0 || currentLetterInInt > 25) {
                    output.append(currentLetter);
                } else {
                    output.append(this.alfabet[currentLetterInInt]);
                }
                output.append(" ");
            }
        }
        return output.toString();
    }

    /**
     * Calculates an alphabeth for the offset method.
     * @return a char array in the form of a = char[0], b = char[1]
     */
    public char[] calculateAlphabet() {
        char[] alfabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] returnAlfabet = new char[alfabet.length];

        for (int i = 0; i < alfabet.length; i++) {
            char outputLetter;
            char currentLetter = alfabet[i];
            int currentLetterInInt = Character.toLowerCase(currentLetter) - 'a' + 1 + offset;
            System.out.println(currentLetterInInt);
            if (currentLetterInInt > 26) // go back to the beginning of the alphabet
                currentLetterInInt -= 26;
            System.out.println("int: " + (currentLetterInInt + 'a' - 1));
            outputLetter = (char) (currentLetterInInt + 'a' - 1);
            returnAlfabet[i] = outputLetter;
            System.out.println("outputLetter = " + outputLetter);
        }
        return returnAlfabet;
    }
}

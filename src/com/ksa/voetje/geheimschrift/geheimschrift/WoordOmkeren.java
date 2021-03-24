package com.ksa.voetje.geheimschrift.geheimschrift;

public class WoordOmkeren {
    String[] input;

    public WoordOmkeren(String input) {
        String temp = input.replaceAll("\n", " ");
        this.input = temp.split(" ");
    }

    public String omkeren() {
        String output = "";
        boolean appenChar = false;
        char charToAppend = 0;
        for (String woord : input) {
            for (int i = 0; i < woord.length(); i++) {
                char charAti = woord.charAt(woord.length()-1-i);
                if (charAti != '.' && charAti != '?' && charAti != ',' && charAti != '!')
                    output += charAti;
                else {
                    appenChar = true;
                    charToAppend = charAti;
                }
            }
            if (appenChar)
                output += charToAppend;
            output += " ";
            appenChar = false;
        }
        return output;
    }
}

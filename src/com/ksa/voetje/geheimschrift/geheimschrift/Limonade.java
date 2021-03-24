package com.ksa.voetje.geheimschrift.geheimschrift;

import java.util.Scanner;

public class Limonade {
    private String input;
    private String limonade;
    private String output = "";
    private String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String alfabet1;
    private String alfabet2;

    private Scanner keyboard = new Scanner(System.in);

    public Limonade(String limonade, String input) {
        this.limonade = limonade.trim();
        this.input = input;
    }

    public boolean limonadeChecken() {
        char letter = 'A';
        String limonade2 = limonade.toUpperCase();;
        int volledigeLengte;
        int lengteZonderLetter;
        while (letter != 'Z') {
            volledigeLengte = limonade2.length();
            lengteZonderLetter = limonade2.replaceAll(String.valueOf(letter), "").length();
            letter++;
            if (volledigeLengte > lengteZonderLetter + 1)
                return false;
        }
        return true;
    }

    public void limonadeSorteren() {
        char limonadeLetter;
        String limonade2 = limonade.toUpperCase();
        while (limonade2.length() > 0) {
            limonadeLetter = limonade2.charAt(0);
            limonade2 = limonade2.replace(limonadeLetter, ' ').trim();
            alfabet = alfabet.replaceAll(String.valueOf(limonadeLetter), "");
        }
        alfabet = limonade.toUpperCase() + alfabet;
        alfabet1 = alfabet.substring(0,13);
        alfabet2 = alfabet.substring(13, 26);
    }

    public String returnAlfabet() {
        limonadeSorteren();
        String alfabetOutput = alfabet1 + "\n" + alfabet2;
        System.out.println(alfabet1);
        System.out.println(alfabet2);
        return alfabetOutput;
    }

    public String getOutput() {
        String input2 = input.toUpperCase();
        int i = 0;
        char letter;
        int indexOfLetter;
        while (i < input2.length()) {
            letter = input2.charAt(i);
            indexOfLetter = alfabet1.indexOf(letter);
            if (indexOfLetter == -1) {
                indexOfLetter = alfabet2.indexOf(letter);
                if (indexOfLetter == -1) {
                }
                else
                    letter = alfabet1.charAt(indexOfLetter);
            }
            else {
                letter = alfabet2.charAt(indexOfLetter);
            }
            output = output + letter;
            i ++;
        }
        return output;
    }


}

package com.ksa.voetje.instellingen;

public class MorseInstellingen {
    public static int morseMaxCharacters = 10000;

    private static boolean specialeTekens = false;
    private static boolean leestekens = false;
    private static boolean onbekendeWaarden = true;

    public static void setMorseMaxCharacters(int i) {
        morseMaxCharacters = i;

        System.out.println("Max character limit voor morse is nu " + morseMaxCharacters);
    }

    public static int getMorseMaxCharacters() {
        return morseMaxCharacters;
    }

    public static boolean getSpecialeTekens() {
        return specialeTekens;
    }
    public static boolean getLeestekens() {
        return leestekens;
    }
    public static boolean getOnbekendeWaarden() {
        return onbekendeWaarden;
    }
    public static void setLeestekens() {
        if (leestekens)
            leestekens = false;
        else leestekens = true;
        System.out.println("Leestekens set to " + getLeestekens());
    }
    public static void setSpecialeTekens() {
        if (specialeTekens)
            specialeTekens = false;
        else specialeTekens = true;
        System.out.println("Speciale tekens set to " + getSpecialeTekens());
    }
    public static void setOnbekendeWaarden() {
        if (onbekendeWaarden)
            onbekendeWaarden = false;
        else onbekendeWaarden = true;
        System.out.println("Onbekende waarden set to " + getOnbekendeWaarden());
    }

    public static String[] getSettings() {
        return new String[]{String.valueOf(morseMaxCharacters)};
    }
}

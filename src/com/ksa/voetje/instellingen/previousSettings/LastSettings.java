package com.ksa.voetje.instellingen.previousSettings;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.methodes.fileSystem.CreateDirectory;

import java.io.*;

public class LastSettings implements Serializable {
    // 1 (settings) 1 (lastsettings) ...
    private static final long serialVersionUID = 1100000L;

    int theme = 2;
    int morseMaxChar = 10000;
    boolean[] readUpdates = new boolean[50];

    public LastSettings(int theme, int morseMaxChar) {
        this.theme = theme;
        this.morseMaxChar = morseMaxChar;
    }

    public LastSettings() {
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getMorseMaxChar() {
        return morseMaxChar;
    }

    public void setMorseMaxChar(int morseMaxChar) {
        this.morseMaxChar = morseMaxChar;
        System.out.println(this.morseMaxChar);
    }

    // TODO: als er geen savedSettings.txt is, dan alle updates als read zien tijdens serialisation
    // TODO: 2de webpagina waar de current version op staat en die vergelijken met de VERSION van dit programma!
    public boolean[] getReadUpdates () {
        return readUpdates;
    }

    public void setUpdateRead(int readUpdate) {
        readUpdates[readUpdate] = true;
    }

    public void serialize() {
        Voetje.getLogWindow().addToLog("Serializing settings...");
        System.out.println("theme = " + theme);
        System.out.println("morseMaxChar = " + morseMaxChar);
        /*
        System.out.println("readUpdates = ");
        for (int i = 0; i < readUpdates.length; i ++) {
            System.out.println("[" + i + "] " + readUpdates[i]);
        }
        */
        // Create stream & write object
        // URL resourceUrl = getClass().getResource("/com/ksa/voetje/savedFiles/savedSettings.txt");
        String url = System.getProperty("user.home") + "/Library/Application Support/com.ksa/voetje/savedFiles/savedSettings.txt";
        System.out.println("URL = " + url);

        String directoryPath = System.getProperty("user.home") + "/Library/Application Support/com.ksa/voetje/savedFiles";

        CreateDirectory.createDirectory(directoryPath);

        System.out.println("== FileOutputStream ==");
        FileOutputStream fout = null;
        System.out.println("FileOutputStream intitialised to be null.");
        try {
            System.out.println("Initialising FileOutputStream...");
            // fout = new FileOutputStream(getClass().getResource("/com/ksa/voetje/savedFiles/savedSettings.txt").toURI().toString());
            //fout = new FileOutputStream("src/com/ksa/voetje/savedFiles/savedSettings.txt");
            fout = new FileOutputStream(url);
            //fout = new FileOutputStream(file);
            System.out.println("FileOutputStream succesfully initialised.");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in serialize");
            e.printStackTrace();
        }

        System.out.println("== ObjectOutputStream ==");
        ObjectOutputStream out = null;
        System.out.println("ObjectOutputStream intitialised to be null.");
        try {
            System.out.println("Initialising ObjectOutputStream. ..");
            out = new ObjectOutputStream(fout);
            System.out.println("ObjectOutputStream succesfully initialised.");
        } catch (IOException ioException) {
            System.out.println("Couldn't initialise ObjectOutputStream");
            ioException.printStackTrace();
        }

        System.out.println("== Writing ==");
        assert out != null;
        try {
            System.out.println("Writing object...");
            out.writeObject(this);
            out.flush();
            // Close stream
            out.close();
            System.out.println("Writtten object.");
        } catch (IOException ioException) {
            System.out.println("Can't write object.");
            ioException.printStackTrace();
        }
        Voetje.getLogWindow().addToLog("Serialized settings.");
    }

    public Object[] deSerialize() {
        // TODO: change url depending on OS
        String url = System.getProperty("user.home") + "/Library/Application Support/com.ksa/voetje/savedFiles/savedSettings.txt";
        InputStream inputStream;
        try {
            System.out.println("inputSteam = new FileInputStream(url)");
            inputStream = new FileInputStream(url);
        } catch (FileNotFoundException e) {
            System.out.println("inputStream = default");
            inputStream = getClass().getResourceAsStream("/com/ksa/voetje/savedFiles/savedSettings.txt");
            e.printStackTrace();
        }
        if (url == null) {
            System.out.println("inputSteam = default");
            inputStream = getClass().getResourceAsStream("/com/ksa/voetje/savedFiles/savedSettings.txt");
        }
        // Deserializing
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(inputStream);
            // in = new ObjectInputStream(new FileInputStream("src/com/ksa/voetje/savedFiles/savedSettings.txt"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        LastSettings s = null;
        try {
            s = (LastSettings) in.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            // TODO: if InvalidClassException -> delete settings file (because that means the object has changed)
            ioException.printStackTrace();
        }

        // Saving settings to String
        Object[] settings = new Object[3];
        settings[0] = s.getTheme();
        Voetje.addToLog((String) this.getName(), "Settings 0 = " + settings[0]);
        settings[1] = s.getMorseMaxChar();
        Voetje.addToLog((String) this.getName(), "Settings 1 = " + settings[1]);
        settings[2] = s.getReadUpdates();
        readUpdates = (boolean[]) settings[2];
        // Debug
        Voetje.addToLog((String) this.getName(), "Settings 2 = " +  settings[2]);
        /*
        boolean[] readUpdates = s.getReadUpdates();
        for (int i = 0; i < readUpdates.length; i ++) {
            System.out.println("readUpdate [" + i + "]" + readUpdates[i]);
        }

         */


        // closing the stream
        try {
            in.close();
        } catch (IOException ioException) {
            Voetje.addToLog(this, "Couldn't close input stream.");
            ioException.printStackTrace();
        }

        return settings;
    }

    public String getName() {
        return "LastSettings";
    }
}

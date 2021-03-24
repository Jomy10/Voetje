package com.ksa.voetje.methodes.downladWebContent;

import com.ksa.voetje.Main;
import com.ksa.voetje.booter.Voetje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateFinder {
    private final String[] updates = new String[50];
    private int lastReadUpdate;

    public UpdateFinder() throws IOException {
        // Make a URL to the web page
        URL url = new URL("https://voetje-news.netlify.app");

        // Get the input stream through URL Connection
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

        // read each line and write to System.out
        boolean readNextLine = false;
        int i = 1;
        while ((line = br.readLine()) != null) {
            if (line.contains("<p>")) {
                readNextLine = true; // next line contains the update
                continue;
            }
            if (readNextLine) {
                System.out.println(line.trim());
                System.out.println(i);
                readNextLine = false;
                updates[i] = line.trim();
                i++;
            }
        }

        /*
        System.out.println("== Updates ==");
        for (int j = 0; j < updates.length; j++) {
            System.out.println("[" + j + "] " + updates[j]);
        }
         */

        // TODO: DOESN'T WORK!!!
        for (int j = 0; j < updates.length; j++)
            if (updates[j] != null)
                updates[j] = updates[j].replaceAll("\\n", "\n");
    }

    // TODO: pagina met alle updates
    public String[] getUpdates() {
        return updates;
    }


    public String getNewUpdate() {
        int unreadUpdateNumber = getUnreadUpdate();

        if (unreadUpdateNumber == -1) {
            Voetje.addToLog(this, "Kon geen nieuwe update vinden.");
            return null;
        }
        else {
            lastReadUpdate = unreadUpdateNumber;
            return updates[unreadUpdateNumber];
        }
    }

    private int getUnreadUpdate() {
        for (int i = 1; i < Main.getPreviousSettings().getReadUpdate().length; i++) {
            if (!Main.getPreviousSettings().getReadUpdate()[i]) {
                System.out.println(i + " is an unread update, returning i.");
                return i;
            }
        }
        return -1;
    }

    /**
     * @deprecated
     * @return ...
     */
    private boolean[] getReadUpdates() {
        System.out.println("== Getting read updates in UpdtateFinder ==");
        for (int i = 0; i < 50; i ++) {
            System.out.println("[" + i + "] " + Main.getPreviousSettings().getReadUpdate()[i]);
        }
        System.out.println("===========================================");
        return Main.getPreviousSettings().getReadUpdate();
    }

    public void setUpdateRead(int updateNumber) {
        Main.getPreviousSettings().setUpdateRead(updateNumber);
    }

    public void setLastUpdateRead() {
        Main.getPreviousSettings().setUpdateRead(lastReadUpdate);
    }
}

package com.ksa.voetje.methodes.fileSystem;

public class LastSelectedDirectory {
    private static String directory;

    public static String getLastSelectedDirectory() {
        return directory;
    }

    public static void setDirectory(String dir) {
        directory = dir;
    }
}

package com.ksa.voetje.methodes.fileSystem;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenFile {
    private String path;
    private File file;

    public OpenFile(String path) {
        this.path = path;
        file = new File(path);

    }

    public void openFile() throws IOException {
        Desktop.getDesktop().open(file);
    }
}

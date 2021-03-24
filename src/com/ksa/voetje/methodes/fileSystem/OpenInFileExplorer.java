package com.ksa.voetje.methodes.fileSystem;

import com.ksa.voetje.Main;
import javafx.application.HostServices;

// TODO: fix
public class OpenInFileExplorer {
    private static HostServices Host;
    private final String path;

    public OpenInFileExplorer(String path) {
        Host = Main.getHostServicesFromMain();
        this.path = path;
    }
    
    public void openPath() {
        Host.showDocument(path);
    }
}

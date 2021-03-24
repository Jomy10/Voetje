package com.ksa.voetje.geheimschrift.display.otherInterfaces.instellingen;

import com.ksa.voetje.booter.Voetje;
import com.ksa.voetje.instellingen.DebugInstellingen;
import com.ksa.voetje.opmaak.elements.Btn;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.interfaces.panes.Interface;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class DebugInstellingenInterface extends MethodScrollPane {
    private final Title debugTitle = new Title("Debug");
    private final Btn showDebug = new Btn("Toon log venster");
    private final Btn showGraphicsContext = new Btn("Toon graphics context");

    private final Interface displayInterface = new Interface(INTERFACE_SPACING,
            debugTitle, showDebug, showGraphicsContext);
    public DebugInstellingenInterface() {
        setContent(displayInterface);
        showDebug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Voetje.showLogWindow();
            }
        });
        showGraphicsContext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DebugInstellingen.showGraphicsWindow = true;
                Voetje.getLogWindow().addToLog("Graphics context will now show up when activated.");
            }
        });
    }
}

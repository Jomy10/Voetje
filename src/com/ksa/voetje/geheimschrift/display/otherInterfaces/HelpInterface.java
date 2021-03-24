package com.ksa.voetje.geheimschrift.display.otherInterfaces;

import com.ksa.voetje.opmaak.elements.HoofdTekstLabel;
import com.ksa.voetje.opmaak.elements.InstantHyperlink;
import com.ksa.voetje.opmaak.elements.Title;
import com.ksa.voetje.opmaak.elements.TussenTitle;
import com.ksa.voetje.opmaak.interfaces.panes.MethodScrollPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.ksa.voetje.geheimschrift.display.otherInterfaces.ProgramInfo.VERSION;

public class HelpInterface extends MethodScrollPane {
    // Elementen
    private static final Title titel = new Title("Help");
    private static final HoofdTekstLabel blank = new HoofdTekstLabel("\n");

    // Morse
    private static final TussenTitle morseTitel = new TussenTitle("Morse");
    private static final InstantHyperlink specialeTekensLink = new InstantHyperlink("Speciale tekens", "https://www.scoutsengidsenvlaanderen.be/technieken-databank/morse");

    // Suggesties
    private static final TussenTitle suggestiesTitel = new TussenTitle("Suggesties?");
    private static final HoofdTekstLabel suggestiesInfo = new HoofdTekstLabel("Heb je suggesties om de app nog beter te maken? Of heb je een probleem gevonden?\nLaat het me gerust weten:");
    private static final HoofdTekstLabel mail = new HoofdTekstLabel("<< Stuur me een mail. >>"); // TODO: ipv hyperlinks, tekst in een andere stijl

    // Info programma
    private static final HoofdTekstLabel author = new HoofdTekstLabel("Jonas Evereart");
    private static final HoofdTekstLabel version = new HoofdTekstLabel(VERSION);

    // Panes
    public static VBox helpInterface = new VBox(titel, morseTitel, specialeTekensLink, suggestiesTitel, suggestiesInfo, mail,blank, author, version);

    public HelpInterface() {
        super();
        setContent(helpInterface);
        setMailAction();
    }

    private void setMailAction() {
        final Desktop[] desktop = new Desktop[1];
        mail.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Desktop.isDesktopSupported()
                        && (desktop[0] = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                    URI mailto = null;
                    try {
                        mailto = new URI("mailto:jonas.vbs4@gmail.com?subject=Suggestie%20Voetje");
                        desktop[0].mail(mailto);
                    } catch (IOException | URISyntaxException e) {
                        mail.setText("jonas.vbs4@gmail.com");
                        e.printStackTrace();
                    }
                } else {
                    mail.setText("jonas.vbs4@gmail.com");
                    throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
                }
            }
        });
    }
}

package tochtTechnieken.display;

import com.ksa.voetje.geheimschrift.display.RootPane;
import com.ksa.voetje.geheimschrift.display.RootScene;
import com.ksa.voetje.opmaak.interfaces.panes.SidebarScrollPane;
import com.ksa.voetje.opmaak.themes.ThemeDisplaySystem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.ksa.voetje.opmaak.OpmaakConstants.SCENE_HEIGHT;
import static com.ksa.voetje.opmaak.OpmaakConstants.SCENE_WIDTH;

public class TochtTechniekenDisplaySystem extends Stage {
    // Scene and stage
    private final RootPane rootPane = new RootPane();
    private final RootScene rootScene = new RootScene(rootPane, SCENE_WIDTH, SCENE_HEIGHT);

    // Theme
    private ThemeDisplaySystem themeDisplaySystem;

    public TochtTechniekenDisplaySystem() {
        super();
        // Sidebar toevoegen
        rootPane.setLeft(sidebar);
        // TODO: initSidebar

        // Contents pane toevoegen
        rootPane.setCenter(interfacePane);
        // TODO: init

        // Stage //
        // stage parameters
        setMinWidth(450);
        setMinHeight(325);

        setTitle("Tochttechnieken");
        setScene(rootScene);

        // TODO: init displaysystem
    }

    // Display elements //
    // Sidebar //
    private final VBox sidebarPane = new VBox();
    private final SidebarScrollPane sidebar = new SidebarScrollPane(sidebarPane);

    // buttons

    // Contents //
    private final FlowPane interfacePane = new FlowPane();

    // Display System //

}

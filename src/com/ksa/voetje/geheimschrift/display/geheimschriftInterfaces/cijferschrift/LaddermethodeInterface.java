package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces.cijferschrift;

import com.ksa.voetje.methodes.imageMaker.shapeImage.LadderImageConstructor;
import com.ksa.voetje.opmaak.elements.Btn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class LaddermethodeInterface extends VBox {
    private CijferschriftInterface cijferInterface;
    private Btn saveImageBtn = new Btn("Bewaar afbeelding");

    public LaddermethodeInterface(CijferschriftInterface cijferschriftInterface) {
        super(INTERFACE_SPACING);
        this.cijferInterface = cijferschriftInterface;
        getChildren().add(saveImageBtn);
        setMargin(saveImageBtn, new Insets(5, 0,0,0));
        setButtonActions();
    }

    private void setButtonActions() {
        saveImageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LadderImageConstructor ladderImageConstructor = new LadderImageConstructor(cijferInterface.getInput());
                ladderImageConstructor.saveAsPng();
            }
        });
    }
}

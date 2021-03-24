package com.ksa.voetje.geheimschrift.display.geheimschriftInterfaces.cijferschrift;

import com.ksa.voetje.methodes.imageMaker.shapeImage.ChineesShapeImageConstructor;
import com.ksa.voetje.opmaak.elements.Btn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import static com.ksa.voetje.opmaak.OpmaakConstants.INTERFACE_SPACING;

public class ChineseCijfersInterface extends VBox {
    // elementen
    private CijferschriftInterface cijferInterface;
    private Btn saveImageBtn = new Btn("Bewaar afbeelding");

    public ChineseCijfersInterface(CijferschriftInterface cijferschriftInterface) {
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
                ChineesShapeImageConstructor chineesImageConstructor = new ChineesShapeImageConstructor(cijferInterface.getInput());
                chineesImageConstructor.saveAsPng();
            }
        });
    }


}

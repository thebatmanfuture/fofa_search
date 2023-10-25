package org.fofaviewer.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;
import org.fofaviewer.controllers.SetConfigDialogController;
import org.fofaviewer.controllers.SetUrlCombinationController;
import org.tinylog.Logger;
import java.io.IOException;

public class SetUrlCombination extends Dialog<ButtonType> {
    public SetUrlCombination(String title) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SetUrlCombination.fxml"));
            Pane pane = loader.load();
            this.setTitle(title);
            DialogPane dialogPane = this.getDialogPane();
            dialogPane.setContent(pane);
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            SetUrlCombinationController controller1 = loader.getController();
            controller1.setAction(dialogPane);
        }catch (IOException e){
            Logger.error(e);
        }
    }
}

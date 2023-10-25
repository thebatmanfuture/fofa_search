package org.fofaviewer.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.fofaviewer.controllers.MainController;

public class MainApp extends Application {


    private final String version = "1.0.1";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fofa_viewer.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Fofa_search v" + this.version+ " By 塔菲-魔改版");
        stage.show();
        stage.setMinWidth(1100);
        stage.setMinHeight(800);
//        stage.getIcons().add(new Image("images/ddddddddd.png"));
        MainController controller = loader.getController();
        Parameters p = this.getParameters();
        if(p.getRaw().size() == 2 && p.getRaw().get(0).equals("-f")){
            controller.openFile(p.getRaw().get(1));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

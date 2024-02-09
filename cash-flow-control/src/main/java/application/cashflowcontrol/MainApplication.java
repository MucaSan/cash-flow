package application.cashflowcontrol;
import controllers.cashflowcontrol.MenuController;
import generalVariables.cashflowcontrol.GlobalVariables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml.controllers.login/login.fxml"));
        scene = new Scene(fxmlLoader.load(), 873, 616);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
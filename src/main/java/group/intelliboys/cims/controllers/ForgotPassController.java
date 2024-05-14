package group.intelliboys.cims.controllers;

import group.intelliboys.cims.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class ForgotPassController {

    public void backToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.primaryStage.setScene(scene);
        App.primaryStage.centerOnScreen();
    }
}

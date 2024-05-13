package group.intelliboys.cims;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class App extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
        primaryStage.setTitle("Customer Information Management System");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("images/favicon.png")).toString()));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
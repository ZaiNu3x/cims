package group.intelliboys.cims.controllers;

import group.intelliboys.cims.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField middleNameField;

    @FXML
    private ComboBox<?> sexField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private Label ageField;

    @FXML
    private TextField addressField;

    @FXML
    private Pane profilePicView;


    public void backToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.primaryStage.setScene(scene);
        App.primaryStage.centerOnScreen();
    }
}

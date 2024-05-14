package group.intelliboys.cims.controllers;

import group.intelliboys.cims.App;
import group.intelliboys.cims.Util.Validator;
import group.intelliboys.cims.configs.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void loginClicked() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if(Validator.isUsernameValid(username)) {
            try {
                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                String loginQuery = "SELECT password FROM users WHERE username = '"+username+"';";
                ResultSet rs = stmt.executeQuery(loginQuery);

                if(rs.next()) {
                    String fetchedHashedPassword = rs.getString("password");

                    if(BCrypt.checkpw(password, fetchedHashedPassword)) {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/dashboard-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        App.primaryStage.setScene(scene);
                        App.primaryStage.centerOnScreen();
                    }
                    else System.out.println("Password not matches!");
                }
                else System.out.println("No Account was found!");

                conn.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else System.out.println("Invalid Input!");
    }

    public void forgotPasswordClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/forgot-password-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.primaryStage.setScene(scene);
        App.primaryStage.centerOnScreen();
    }

    public void registerClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registration-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.primaryStage.setScene(scene);
        App.primaryStage.centerOnScreen();
    }
}
package group.intelliboys.cims.controllers;

import group.intelliboys.cims.Util.Validator;
import group.intelliboys.cims.configs.Database;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
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
                        System.out.println("Password matches!");
                    }
                    else System.out.println("Password not matches!");
                }
                else System.out.println("No Account was found!");

                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("Invalid Input!");
    }
}
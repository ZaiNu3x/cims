package group.intelliboys.cims.controllers;

import group.intelliboys.cims.App;
import group.intelliboys.cims.Util.Validator;
import group.intelliboys.cims.configs.Database;
import group.intelliboys.cims.models.User;
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
import java.time.LocalDate;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void loginClicked() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!(username == null) || !(password == null)) {
            try {
                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                String loginQuery = "SELECT password FROM users WHERE username = '"+username+"';";
                ResultSet rs = stmt.executeQuery(loginQuery);

                if(rs.next()) {
                    String fetchedHashedPassword = rs.getString("password");

                    if(BCrypt.checkpw(password, fetchedHashedPassword)) {

                        String query = "SELECT * FROM users WHERE username = '"+username+"';";
                        rs = stmt.executeQuery(query);

                        if(rs.next()) {
                            App.currentLoggedInUser = new User();

                            App.currentLoggedInUser.setUsername(rs.getString("username"));
                            App.currentLoggedInUser.setPassword(rs.getString("password"));
                            App.currentLoggedInUser.setLastName(rs.getString("last_name"));
                            App.currentLoggedInUser.setFirstName(rs.getString("first_name"));
                            App.currentLoggedInUser.setMiddleName(rs.getString("middle_name"));
                            App.currentLoggedInUser.setSex(rs.getString("sex"));
                            App.currentLoggedInUser.setBirthDate(rs.getDate("birth_date").toLocalDate());
                            App.currentLoggedInUser.setAge(rs.getByte("age"));
                            App.currentLoggedInUser.setAddress(rs.getString("address"));
                            App.currentLoggedInUser.setEmail(rs.getString("email"));
                            App.currentLoggedInUser.setProfilePic(rs.getBytes("profile_pic"));
                        }

                        System.out.println(App.currentLoggedInUser.getUsername());

                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/dashboard-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        App.primaryStage.setScene(scene);
                        App.primaryStage.centerOnScreen();
                    }
                    else JOptionPane.showMessageDialog(null, "Password not matches!");
                }
                else JOptionPane.showMessageDialog(null, "No account was found!");

                conn.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else JOptionPane.showMessageDialog(null, "Invalid Input!");
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
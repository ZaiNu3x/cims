package group.intelliboys.cims.controllers;

import group.intelliboys.cims.App;
import group.intelliboys.cims.Util.Validator;
import group.intelliboys.cims.configs.Database;
import group.intelliboys.cims.models.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

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
    private ComboBox<String> sexField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private Label ageField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private Pane profilePicView;

    private InputStream is;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexField.getItems().addAll("Male", "Female");
    }

    public void birthDateChangedListener() {
        if (birthDateField.getValue() != null) {
            byte age = (byte) Period.between(birthDateField.getValue(), LocalDate.now()).getYears();
            ageField.setText(String.valueOf(age));
        }
    }

    public void profileImgChooser() {
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(App.primaryStage);

        try {

            is = new FileInputStream(file);

            BufferedImage bufferedImage = ImageIO.read(file);

            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            profilePicView.setBackground(new Background(
                    new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerClicked() throws Exception {
        User user = new User();

        user.setUsername(usernameField.getText());

        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            user.setPassword(new BCryptPasswordEncoder().encode(passwordField.getText()));
        } else JOptionPane.showMessageDialog(null, "Password not matches!");

        user.setLastName(lastNameField.getText());
        user.setFirstName(firstNameField.getText());
        user.setMiddleName(middleNameField.getText());
        user.setSex(sexField.getValue().toString());
        user.setBirthDate(birthDateField.getValue());
        byte age = (byte) Period.between(birthDateField.getValue(), LocalDate.now()).getYears();
        user.setAge(age);
        user.setAddress(addressField.getText());
        user.setEmail(emailField.getText());

        if (Validator.isValidRegistrationForm(user)) {
            try {
                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();

                String insertQuery = "INSERT INTO users (username, password, last_name, first_name, middle_name, sex, birth_date, age, address, email) " +
                        "VALUE ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getLastName() + "','" + user.getFirstName() + "','" + user.getMiddleName() + "','" + user.getSex() + "','" + user.getBirthDate() + "','"
                        + user.getAge() + "','" + user.getAddress() + "','" + user.getEmail() + "');";

                stmt.execute(insertQuery);

                PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET profile_pic = ? WHERE username = '" + user.getUsername() + "';");
                pstmt.setBlob(1, is);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registration Success!");
                backToLogin();

            } catch (Exception e) {
                throw new Exception(e);
            }
        } else JOptionPane.showMessageDialog(null, "Invalid Registration Form!");
    }

    public void backToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.primaryStage.setScene(scene);
        App.primaryStage.centerOnScreen();
    }

}

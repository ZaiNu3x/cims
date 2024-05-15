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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @FXML
    private Button registerBtn;

    private InputStream is;

    private boolean[] isFormValid = new boolean[11];

    public boolean checkFormIfValid() {

        boolean isValid = false;
        byte tmp = 0;

        for (boolean val : isFormValid) {
            if (!val) {
                tmp += 1;
                isValid = false;
            } else {
                isValid = true;
            }
        }

        if(tmp == 0) {
            return isValid;
        }
        else {
            return !isValid;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexField.getItems().addAll("Male", "Female");
        registerBtn.setDisable(true);

        usernameField.textProperty().addListener(observable -> {

            String value = usernameField.getText();

            if (value.length() < 8 || value.contains(" ") || Character.isDigit(value.charAt(0))) {
                usernameField.setTooltip(new Tooltip("""
                            Username length must 8 characters!
                            Does not contain whitspaces!
                            Does not start with number!
                        """));

                registerBtn.setDisable(true);
                usernameField.setStyle("-fx-border-color: red");

            } else {

                isFormValid[0] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                usernameField.setTooltip(null);
                usernameField.setStyle("-fx-border-color: green");
            }
        });

        passwordField.textProperty().addListener(observable -> {

            String value = passwordField.getText();

            if (value.contains(" ") || value.length() < 8) {
                passwordField.setTooltip(new Tooltip("""
                            Password length must 8 characters!
                            Does not contain whitspaces!
                        """));

                registerBtn.setDisable(true);
                passwordField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[1] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                passwordField.setTooltip(null);
                passwordField.setStyle("-fx-border-color: green");
            }
        });

        confirmPasswordField.textProperty().addListener(observable -> {

            String password = passwordField.getText();
            String confirmPass = confirmPasswordField.getText();

            if (!confirmPass.equals(password)) {
                confirmPasswordField.setTooltip(new Tooltip("""
                            Password does not matches!
                        """));

                registerBtn.setDisable(true);
                confirmPasswordField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[2] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                confirmPasswordField.setTooltip(null);
                confirmPasswordField.setStyle("-fx-border-color: green");
            }

        });

        lastNameField.textProperty().addListener(observable -> {
            String value = lastNameField.getText();

            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(value);

            if (value.length() < 3 || value.length() > 30 || matcher.find()) {
                lastNameField.setTooltip(new Tooltip("""
                            Lastname must have 3 and less than 30 characters and does
                            does contains number!
                        """));

                registerBtn.setDisable(true);
                lastNameField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[3] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                lastNameField.setTooltip(null);
                lastNameField.setStyle("-fx-border-color: green");
            }
        });

        firstNameField.textProperty().addListener(observable -> {
            String value = firstNameField.getText();

            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(value);

            if (value.length() < 3 || value.length() > 30 || matcher.find()) {
                firstNameField.setTooltip(new Tooltip("""
                            Firstname must have 3 and less than 30 characters and does
                            does contains number!
                        """));

                registerBtn.setDisable(true);
                firstNameField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[4] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                firstNameField.setTooltip(null);
                firstNameField.setStyle("-fx-border-color: green");
            }
        });

        middleNameField.textProperty().addListener(observable -> {
            String value = middleNameField.getText();

            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(value);

            if (value.length() < 3 || value.length() > 30 || matcher.find()) {
                middleNameField.setTooltip(new Tooltip("""
                            Middlename must have 3 and less than 30 characters and does
                            does contains number!
                        """));

                registerBtn.setDisable(true);
                middleNameField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[5] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                middleNameField.setTooltip(null);
                middleNameField.setStyle("-fx-border-color: green");
            }
        });

        birthDateField.valueProperty().addListener(observable -> {
            int years = Period.between(birthDateField.getValue(), LocalDate.now()).getYears();

            if (years < 0 || years > 120) {
                birthDateField.setTooltip(new Tooltip("""
                            Invalid birth date!
                        """));

                registerBtn.setDisable(true);
                birthDateField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[6] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                birthDateField.setTooltip(null);
                birthDateField.setStyle("-fx-border-color: green");
            }
        });

        ageField.textProperty().addListener(observable -> {
            int age = Integer.parseInt(ageField.getText());

            if (age < 0 || age > 120) {
                ageField.setTooltip(new Tooltip("""
                            Invalid Age!
                        """));

                registerBtn.setDisable(true);
                ageField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[7] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                ageField.setTooltip(null);
                ageField.setStyle("-fx-border-color: green");
            }
        });

        sexField.valueProperty().addListener(observable -> {
            String value = sexField.getValue();

            if (value == null) {
                sexField.setTooltip(new Tooltip("""
                            Invalid Gender!
                        """));

                registerBtn.setDisable(true);
                sexField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[8] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                ageField.setTooltip(null);
                ageField.setStyle("-fx-border-color: green");
            }
        });

        addressField.textProperty().addListener(observable -> {
            String value = addressField.getText();

            if (value.length() < 8 || value.length() > 255) {
                addressField.setTooltip(new Tooltip("""
                            Invalid Address!
                        """));

                registerBtn.setDisable(true);
                addressField.setStyle("-fx-border-color: red");
            } else {

                isFormValid[9] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                addressField.setTooltip(null);
                addressField.setStyle("-fx-border-color: green");
            }
        });

        emailField.textProperty().addListener(observable -> {
            String value = emailField.getText();

            if (value.endsWith("@yahoo.com") || value.endsWith("@gmail.com")) {

                isFormValid[10] = true;

                if (checkFormIfValid()) {
                    registerBtn.setDisable(false);
                }

                emailField.setTooltip(null);
                emailField.setStyle("-fx-border-color: green");
            } else {

                emailField.setTooltip(new Tooltip("""
                            Invalid Email address!
                        """));

                registerBtn.setDisable(true);
                emailField.setStyle("-fx-border-color: red");
            }
        });
    }

    public void birthDateChangedListener() {
        if (birthDateField.getValue() != null) {
            byte age = (byte) Period.between(birthDateField.getValue(), LocalDate.now()).getYears();
            ageField.setText(String.valueOf(age));
        }
    }

    public void usernameFieldTextChanged() {

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

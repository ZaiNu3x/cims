package group.intelliboys.cims.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerRegistrationFormController implements Initializable {

    @FXML
    private DialogPane dialogPane;

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
    private TextField contactField;

    @FXML
    private TextField addressField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexField.getItems().addAll("Male", "Female");

        Button button = (Button) dialogPane.lookupButton(ButtonType.OK);

        button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String sex = sexField.getValue();
                LocalDate birthDate = birthDateField.getValue();
                String contactNo = contactField.getText();
                String address = addressField.getText();

                String query = "INSERT INTO customers (last_name, first_name, middle_name, sex, birth_date, contactNo, address) VALUE " +
                        "('"+lastName+"','"+firstName+"','"+middleName+"','"+sex+"','"+birthDate+"','"+contactNo+"','"+address+"'";

                System.out.println(query);
            }
        });
    }
}

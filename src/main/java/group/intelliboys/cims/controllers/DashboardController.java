package group.intelliboys.cims.controllers;

import group.intelliboys.cims.App;
import group.intelliboys.cims.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private Circle profilePic;

    @FXML
    private Label userNameLbl;

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<Customer, String> customerLastName;

    @FXML
    private TableColumn<Customer, String> customerFirstName;

    @FXML
    private TableColumn<Customer, String> customerMiddleName;

    @FXML
    private TableColumn<Customer, String> customerAddress;

    @FXML
    private TableColumn<Customer, String> customerContact;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(App.currentLoggedInUser.getProfilePic());

            BufferedImage bufferedImage = ImageIO.read(bis);

            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            profilePic.setFill(new ImagePattern(image));


        } catch (Exception e) {
            e.printStackTrace();
        }

        userNameLbl.setText(App.currentLoggedInUser.getFirstName() + " " + App.currentLoggedInUser.getLastName().charAt(0) + ".");
    }

    public void showCustomersClicked() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        customerList.add(new Customer("Maraon", "Jerome", "Dela Peña", LocalDate.parse("2001-04-08"), (byte) 23, "PNR Site", "09123456789"));
        customerList.add(new Customer("Maraon", "Jerome", "Dela Peña", LocalDate.parse("2001-04-08"), (byte) 23, "PNR Site", "09123456789"));
        customerList.add(new Customer("Maraon", "Jerome", "Dela Peña", LocalDate.parse("2001-04-08"), (byte) 23, "PNR Site", "09123456789"));
        customerList.add(new Customer("Maraon", "Jerome", "Dela Peña", LocalDate.parse("2001-04-08"), (byte) 23, "PNR Site", "09123456789"));
        customerList.add(new Customer("Maraon", "Jerome", "Dela Peña", LocalDate.parse("2001-04-08"), (byte) 23, "PNR Site", "09123456789"));

        customerLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        customerFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        customerMiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        customersTable.setItems(customerList);
    }
}

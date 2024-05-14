package group.intelliboys.cims.controllers;

import group.intelliboys.cims.App;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private Circle profilePic;

    @FXML
    private Label userNameLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(App.currentLoggedInUser.getProfilePic());

            BufferedImage bufferedImage = ImageIO.read(bis);

            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            /*
            profilePic.setBackground(new Background(
                    new BackgroundImage(image, BackgroundRepeat.ROUND,
                            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true))));
             */

            profilePic.setFill(new ImagePattern(image));


        } catch (Exception e) {
            e.printStackTrace();
        }

        userNameLbl.setText(App.currentLoggedInUser.getFirstName()+" "+App.currentLoggedInUser.getLastName().charAt(0)+".");
    }
}

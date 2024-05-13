module group.intelliboys.cims {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.crypto;
    requires java.desktop;

    opens group.intelliboys.cims to javafx.fxml;
    exports group.intelliboys.cims;
    exports group.intelliboys.cims.controllers;
    opens group.intelliboys.cims.controllers to javafx.fxml;
}
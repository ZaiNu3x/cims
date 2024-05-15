module group.intelliboys.cims {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.crypto;
    requires java.desktop;
    requires javafx.swing;

    opens group.intelliboys.cims to javafx.fxml;
    opens group.intelliboys.cims.models to javafx.base;
    exports group.intelliboys.cims;
    exports group.intelliboys.cims.controllers;
    opens group.intelliboys.cims.controllers to javafx.fxml;
}
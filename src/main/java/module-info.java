module group.intelliboys.cims {
    requires javafx.controls;
    requires javafx.fxml;


    opens group.intelliboys.cims to javafx.fxml;
    exports group.intelliboys.cims;
}
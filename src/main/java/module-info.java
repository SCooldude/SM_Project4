module com.example.sm_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.sm_project4 to javafx.fxml;
    exports com.example.sm_project4;
}

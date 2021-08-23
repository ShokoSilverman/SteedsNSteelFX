module com.example.steedsnsteelfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.steedsnsteelfx to javafx.fxml;
    exports com.example.steedsnsteelfx.Controllers;
    opens com.example.steedsnsteelfx.Controllers to javafx.fxml;
}
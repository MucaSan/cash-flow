module com.example.cashflowcontrol {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    exports controllers.cashflowcontrol;
    opens controllers.cashflowcontrol to javafx.fxml;
    exports application.cashflowcontrol;
    opens application.cashflowcontrol to javafx.fxml;
}
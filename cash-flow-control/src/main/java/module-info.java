module com.example.cashflowcontrol {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.cashflowcontrol to javafx.fxml;
    exports com.example.cashflowcontrol;
}
module com.example.cashflowcontrol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.almasb.fxgl.all;
    exports controllers.cashflowcontrol;
    opens controllers.cashflowcontrol to javafx.fxml;
    exports application.cashflowcontrol;
    opens application.cashflowcontrol to javafx.fxml;
    exports generalPurposesClasses.cashflowcontrol;
    opens generalPurposesClasses.cashflowcontrol to javafx.fxml;
}
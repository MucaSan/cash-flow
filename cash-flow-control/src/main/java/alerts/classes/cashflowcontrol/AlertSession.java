package alerts.classes.cashflowcontrol;

import  interfaces.cashflowcontrol.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AlertSession implements AlertActionSucessful, AlertToBlank, AlertToDatabase {
    @Override
    public void alertSuccess() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Creation");
        alert.setHeaderText("Success!");
        alert.setContentText("Creation of the session has been done sucessfully!");
        alert.showAndWait();
    }

    @Override
    public void alertBlank() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Blank");
        alert.setHeaderText("Alert!");
        alert.setContentText("At least one textfield is blank!");
        alert.showAndWait();
    }

    @Override
    public void alertRecordAssociated() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ERROR!");
        alert.setContentText("There is already a session with that name!");
        alert.showAndWait();
    }

}


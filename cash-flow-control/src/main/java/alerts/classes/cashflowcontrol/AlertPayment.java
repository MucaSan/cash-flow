package alerts.classes.cashflowcontrol;

import interfaces.cashflowcontrol.AlertActionSucessful;
import interfaces.cashflowcontrol.AlertToBlank;
import interfaces.cashflowcontrol.AlertToDatabase;
import javafx.scene.control.Alert;

public class AlertPayment implements AlertActionSucessful, AlertToBlank, AlertToDatabase {

    @Override
    public void alertSuccess() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Creation");
        alert.setHeaderText("Success!");
        alert.setContentText("Creation of the payment type has been done sucessfully!");
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
        alert.setContentText("There is already a payment type with that name!");
        alert.showAndWait();
    }

}

package alerts.classes.cashflowcontrol;

import generalVariables.cashflowcontrol.GlobalVariables;
import  interfaces.cashflowcontrol.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Optional;

public class AlertSession implements AlertActionSucessful, AlertToBlank, AlertToDatabase, AlertYesNo{
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

    public void alertMessage(String name){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Are you sure you'd like to delete this record?",
                ButtonType.NO, ButtonType.YES);
        alert.setTitle("Deletion");
        alert.setContentText("By doing so, you also delete all the transactions with the given session name!");
        alert.setHeaderText("Would you like to delete this record?");
       Optional<ButtonType> buttonClicked = alert.showAndWait();
       try{
           if(buttonClicked.get() == ButtonType.YES){
                GlobalVariables.SQL = "DELETE TransactionDB from TransactionDB INNER JOIN SessionDB " +
                        "on TransactionDB.idSession = SessionDB.id where SessionDB.name = '" + name +  "' AND " +
                        "TransactionDB.userAssociated = '"  +  GlobalVariables.userLogged +"';";

               GlobalVariables.connection = GlobalVariables.database.getConnection();
               GlobalVariables.statement = GlobalVariables.connection.createStatement();
               GlobalVariables.statement.executeUpdate(GlobalVariables.SQL);
               GlobalVariables.SQL = "DELETE from SessionDB where userAssociated= '" + GlobalVariables.userLogged + "' AND" +
                       " name='" + name + "';";
               GlobalVariables.statement.executeUpdate(GlobalVariables.SQL);
           }
       } catch(SQLException e){
           e.printStackTrace();
           e.getCause();
       }

    }
}


package controllers.cashflowcontrol;

import alerts.classes.cashflowcontrol.AlertPayment;
import connection.cashflowcontrol.DatabaseConnection;
import generalPurposesClasses.cashflowcontrol.ChangeWindow;
import generalVariables.cashflowcontrol.GlobalVariables;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class PaymentController extends AlertPayment {
    private String nameAssociated = null;
    @FXML
    Button buttonCreate;
    @FXML
    Button buttonSave;
    @FXML
    Button buttonGoBack;
    @FXML
    TextField textName;
    @FXML
    TextArea textDescription;

    public void createButtonClick(MouseEvent event){
        if (textName.getText().isBlank() || textDescription.getText().isBlank()){
            alertBlank();
            return;
        }
        try{
            GlobalVariables.database = new DatabaseConnection();
            GlobalVariables.SQL = "SELECT * from PaymentDB where name='" + textName.getText() +  "' " + "" +
                    "AND userAssociated='" + GlobalVariables.userLogged+ "';";
            GlobalVariables.connection = GlobalVariables.database.getConnection();
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
            if(GlobalVariables.resultSet.next()){
                alertRecordAssociated();
                cleanTextFields();
            }
            else{
                GlobalVariables.SQL = "SELECT * from PaymentDB;";
                int temp = 1;
                try{
                    GlobalVariables.resultSet = GlobalVariables.statement.
                            executeQuery(GlobalVariables.SQL);
                    while(GlobalVariables.resultSet.next()){
                        temp+=1;
                    }
                    GlobalVariables.SQL = "INSERT INTO PaymentDB values ('"  + temp  + "', " +
                            "'" + textName.getText() +  "', '" + GlobalVariables.userLogged +  "'"
                            + ", '" + textDescription.getText()  +  "');";
                    GlobalVariables.statement.executeUpdate(GlobalVariables.SQL);
                    alertSuccess();
                    cleanTextFields();
                } catch(SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
                GlobalVariables.statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();

        }
    }
    public void cleanTextFields() {
        textName.setText("");
        textDescription.setText("");
    }
    public void displayName (String name){
        textName.setText(name);
    }
    public void displayDescription(String description){
        textDescription.setText(description);
    }
    public void saveButtonClick(MouseEvent event) throws IOException{
        try{
            GlobalVariables.SQL =
                    "SELECT * FROM SessionDB WHERE name = '" + textName.getText() +  "' AND " +
                            "userAssociated = '" + GlobalVariables.userLogged  + "' ;";
            GlobalVariables.connection = GlobalVariables.database.getConnection();
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
            GlobalVariables.resultSet = GlobalVariables.statement
                    .executeQuery(GlobalVariables.SQL);
            if (GlobalVariables.resultSet.next() && !(textName.getText().equals(nameAssociated))){
                alertRecordAssociated();
            } else{
                GlobalVariables.SQL =
                        "UPDATE SessionDB SET name = '" + textName.getText() +  "'" +
                                ", description = '" + textDescription.getText() +  "'" +
                                " WHERE name = '" + nameAssociated + "' AND userAssociated = '"
                                + GlobalVariables.userLogged + "';";
                GlobalVariables.statement.executeUpdate(GlobalVariables.SQL);
                alertSuccess();
                GlobalVariables.window = new ChangeWindow<MouseEvent>(event,"/fxml.controllers.session/sessionMenu.fxml");
                GlobalVariables.window.setNewWindowFromMouseClick(GlobalVariables.window.getActionMouse(),
                        GlobalVariables.window.getPathToFXMLFile());
            }
        } catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void buttonGoBackClick(MouseEvent event) throws IOException {
        GlobalVariables.window = new ChangeWindow<MouseEvent>(event,"/fxml.controllers.menu/menu.fxml");
        GlobalVariables.window.setNewWindowFromMouseClick(GlobalVariables.window.getActionMouse(),
                GlobalVariables.window.getPathToFXMLFile());
    }

    public void setNameAssociated(String nameAssociated){
        this.nameAssociated = nameAssociated;
    }
}

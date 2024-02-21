package controllers.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.ChangeWindow;
import generalPurposesClasses.cashflowcontrol.VerifyExistanceValue;
import generalVariables.cashflowcontrol.GlobalVariables;
import javafx.scene.control.Button;
import connection.cashflowcontrol.DatabaseConnection;
import interfaces.cashflowcontrol.CleanTextFields;
import javafx.scene.control.Alert;
import alerts.classes.cashflowcontrol.AlertSession;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class SessionController extends AlertSession implements CleanTextFields{
        String nameAssociated = null;
        @FXML
        Button buttonCreate;
        @FXML
        Button buttonSave;
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
                        GlobalVariables.SQL = "SELECT * from SessionDB where name='" + textName.getText() +  "' " + "" +
                                "AND userAssociated='" + GlobalVariables.userLogged+ "';";
                        GlobalVariables.connection = GlobalVariables.database.getConnection();
                        GlobalVariables.statement = GlobalVariables.connection.createStatement();
                        GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
                        if(GlobalVariables.resultSet.next()){
                                alertRecordAssociated();
                                cleanTextFields();
                        }
                        else{
                                GlobalVariables.SQL = "SELECT * from SessionDB;";
                                int temp = 0;
                                try{
                                        VerifyExistanceValue<Integer> verifyVariable = new VerifyExistanceValue<>();
                                        GlobalVariables.resultSet = GlobalVariables.statement.
                                                executeQuery(GlobalVariables.SQL);
                                        while(GlobalVariables.resultSet.next()){
                                                if (!(verifyVariable.hasRepeatedVariable("SessionDB","id",
                                                        GlobalVariables.resultSet.getInt(1)))){
                                                        temp+=1;
                                                }
                                        }
                                        GlobalVariables.SQL = "INSERT INTO SessionDB values ('"  + temp  + "', " +
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


}

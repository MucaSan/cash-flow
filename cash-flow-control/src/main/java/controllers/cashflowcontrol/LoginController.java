package controllers.cashflowcontrol;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import connection.cashflowcontrol.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.xml.transform.Result;

public class LoginController {
    @FXML
    private Button buttonLogin;
    @FXML
    private TextField textUsername;
    @FXML
    private TextField textPassword;
    @FXML
    private Label labelSign;
    @FXML
    private Label labelTest;

    public void loginClick(ActionEvent event){
        DatabaseConnection connection =  new DatabaseConnection();
        Connection connectToDatabase = connection.getConnection();
        String SQL = "SELECT * FROM costFlowControlDB.UsersDB where username='" + textUsername.getText() + "' AND password='"+  textPassword.getText()+  "' ;";
       try{
            Statement statement = connectToDatabase.createStatement();
           ResultSet  queryResult = statement.executeQuery(SQL);
           if(queryResult.next()){
                labelTest.setText("Logged!");
                labelTest.setVisible(true);
           }else{
               labelTest.setText("ERROR!");
               labelTest.setVisible(true);
           }
       }catch(SQLException e){
           e.printStackTrace();
           e.getCause();
       }
    }

}
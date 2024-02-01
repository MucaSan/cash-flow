package controllers.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.ChangeWindow;
import javafx.scene.control.Alert;
import generalVariables.cashflowcontrol.GlobalVariables;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import connection.cashflowcontrol.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    public void loginClick(ActionEvent event) throws IOException{
        // creating an object DatabaseConnection, instanced on the class DatabaseConnection
        GlobalVariables.database =  new DatabaseConnection();
        GlobalVariables.connection = GlobalVariables.database.getConnection();
       GlobalVariables.SQL = "SELECT * FROM costFlowControlDB.UsersDB where username='" + textUsername.getText() + "' AND password='"+  textPassword.getText()+  "' ;";
       try{
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
           GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
           if(GlobalVariables.resultSet.next()){
               GlobalVariables.userLogged = textUsername.getText();
                labelTest.setText("Logged!");
                labelTest.setVisible(true);
               GlobalVariables.window = new ChangeWindow<ActionEvent>(event,"/fxml.controllers.menu/menu.fxml");
               GlobalVariables.window.setNewWindowFromAction(GlobalVariables.window.getActionEvent(),
                       GlobalVariables.window.getPathToFXMLFile());
           }else{
               labelTest.setText("ERROR!");
               labelTest.setVisible(true);
           }
           GlobalVariables.statement.close();
       }catch(SQLException e){
           e.printStackTrace();
           e.getCause();
       }
    }
    public void signUpClick(MouseEvent event) throws IOException {
        try{
            // try to load up and instance a new scene, and set it to the stage variable
            GlobalVariables.window = new ChangeWindow<MouseEvent>(event,"/fxml.controllers.login/signin.fxml");
            GlobalVariables.window.setNewWindowFromMouseClick(GlobalVariables.window.getActionMouse(), GlobalVariables.window.getPathToFXMLFile());
        } catch(IOException e){
            // popping message to the user in the GUI.
           alertMessage("ERROR","Failure on executing the new stage",
                   String.format("Failure to open the Sign-up window, due to: %s", e.getMessage()));
        }

    }
    public void alertMessage(String title, String headerContent, String fixMessage){
        Alert  alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerContent);
        alert.setContentText(fixMessage);
        alert.showAndWait();
    }

}
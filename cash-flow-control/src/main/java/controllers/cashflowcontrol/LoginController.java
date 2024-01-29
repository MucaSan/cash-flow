package controllers.cashflowcontrol;
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
    private Scene scene;
    private Parent root;
    private Stage  stage;
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
        // creating an object DatabaseConnection, instanced on the class DatabaseConnection
        DatabaseConnection connection =  new DatabaseConnection();
        Connection connectToDatabase = connection.getConnection();
       GlobalVariables.SQL = "SELECT * FROM costFlowControlDB.UsersDB where username='" + textUsername.getText() + "' AND password='"+  textPassword.getText()+  "' ;";
       try{
            Statement statement = connectToDatabase.createStatement();
           ResultSet  queryResult = statement.executeQuery(GlobalVariables.SQL);
           if(queryResult.next()){
                labelTest.setText("Logged!");
                labelTest.setVisible(true);

           }else{
               labelTest.setText("ERROR!");
               labelTest.setVisible(true);
           }
           statement.close();
       }catch(SQLException e){
           e.printStackTrace();
           e.getCause();
       }
    }
    public void signUpClick(MouseEvent event) throws IOException {
        try{
            // try to load up and instance a new scene, and set it to the stage variable
            root = FXMLLoader.load(getClass().getResource("/fxml.controllers.login/signin.fxml"));
            scene =  new Scene(root);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
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
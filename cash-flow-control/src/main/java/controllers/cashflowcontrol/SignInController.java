package controllers.cashflowcontrol;
import interfaces.cashflowcontrol.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import generalVariables.cashflowcontrol.GlobalVariables;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import connection.cashflowcontrol.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SignInController implements AlertToPassword, AlertToBlank, AlertToDatabase, CleanTextFields, AlertActionSucessful {
        @FXML
        private TextField textUsername;
        @FXML
        private PasswordField textPassword;
        @FXML
        private PasswordField textRPassword;
        @FXML
        private Button buttonCreate;
        @FXML
        private CheckBox checkPassword;
        public void createAccountClick(MouseEvent event) {
               if (verifyIsBlank())
               {
                       alertBlank();
               }
               else if(!verifyPassword()){
                        alertPassword();
               }
               else{
                       DatabaseConnection databaseConnect = new DatabaseConnection();
                       Connection connectToDatabase = databaseConnect.getConnection();
                       GlobalVariables.SQL = "SELECT * FROM costFlowControlDB.UsersDB where username='" + textUsername.getText() + "';";
                      try{
                              Statement statement = connectToDatabase.createStatement();
                              ResultSet resultSet = statement.executeQuery(GlobalVariables.SQL);
                              if(resultSet.next()){
                                      alertRecordAssociated();

                              } else{
                                      try{
                                              GlobalVariables.SQL = "INSERT INTO costFlowControlDB.UsersDB values('"+textUsername.getText()+ "');";
                                              statement.executeUpdate(GlobalVariables.SQL);
                                              cleanTextFields();
                                              alertSuccess();
                                              statement.close();
                                      }catch(SQLException e){
                                              e.printStackTrace();
                                              e.getCause();
                                      }

                              }

                      }catch(SQLException e){
                              e.printStackTrace();
                              e.getCause();
                      }
               }

        }
        private boolean verifyPassword(){
                return textRPassword.getText().equals(textPassword.getText());
        }
        private boolean verifyIsBlank(){return (textPassword.getText().isBlank() && textUsername.getText().isBlank() && textRPassword.getText().isBlank());}
        public void alertBlank(){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Blank fields");
                alert.setHeaderText("ALERT!");
                alert.setContentText("Fill all the three textfields!");
                alert.showAndWait();
        }
        public void alertPassword(){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Passwords");
                alert.setHeaderText("ERROR!");
                alert.setContentText("Passwords aren't the same! Please, check them again!");
                alert.showAndWait();
        }
        public void alertSuccess(){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION;
                alert.setTitle("Account created");
                alert.setHeaderText("SUCCESS!");
                alert.setContentText("Account created sucessfully, and added into the database!");
                alert.showAndWait();
        }
        public void alertRecordAssociated(){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("DATABASE");
                alert.setHeaderText("INFORMATION!");
                alert.setContentText(String.format("Choose another username: %s" + "already exists into the database",
                        textUsername.getText()));
                alert.showAndWait();
        }

        @Override
        public void cleanTextFields() {
                textUsername.clear();
                textRPassword.clear();
                textPassword.clear();
        }
}

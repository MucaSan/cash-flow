package controllers.cashflowcontrol;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import connection.cashflowcontrol.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SignInController {
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
        public void createAccountClick(ActionEvent event) {
                if (verifyPassword()){return;}
                if (verifyIsBlank()){return;}
        }
        public boolean verifyPassword(){
                return textRPassword.getText().equals(textPassword.getText());
        }
        public boolean verifyIsBlank(){
                return (textPassword.getText().isBlank() && textUsername.getText().isBlank() && textRPassword.getText().isBlank());
        }

}

package controllers.cashflowcontrol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    public void loginClick(ActionEvent event){
        if (textUsername.getText().equals("admin") && textPassword.getText().equals("123")){
            System.out.println("Status of login: activated, user authenticated!");
            labelTest.setText("Done!");
            labelTest.setVisible(true);
        }
    }

}
package controllers.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.ChangeWindow;
import generalVariables.cashflowcontrol.GlobalVariables;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button buttonLogOut;
    @FXML
    private Label labelWelcome;
    @FXML
    private Button buttonSession;
    @FXML
    private Button buttonPayment;
    @FXML
    private Button buttonManage;

    public void buttonLogOutClick(MouseEvent event) throws IOException {
        GlobalVariables.window = new ChangeWindow<MouseEvent>(event,"/fxml.controllers.login/login.fxml");
        GlobalVariables.window.setNewWindowFromMouseClick(GlobalVariables.window.getActionMouse(),
                GlobalVariables.window.getPathToFXMLFile());
    }

    public void buttonSessionClick(MouseEvent event) throws IOException{
        GlobalVariables.window = new ChangeWindow<MouseEvent>(event,"/fxml.controllers.session/session.fxml");
        GlobalVariables.window.setNewWindowFromMouseClick(GlobalVariables.window.getActionMouse(),
                GlobalVariables.window.getPathToFXMLFile());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelWelcome.setText("Welcome "+ GlobalVariables.userLogged + "!");
        labelWelcome.setVisible(true);
    }
}

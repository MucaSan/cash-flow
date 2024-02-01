package controllers.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.ChangeWindow;
import generalVariables.cashflowcontrol.GlobalVariables;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MenuController {
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

    public void setNameWhenStageIsLoaded(ActionEvent event){
        event = new ActionEvent();
    }

}

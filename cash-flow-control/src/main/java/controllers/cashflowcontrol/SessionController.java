package controllers.cashflowcontrol;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class SessionController {
        @FXML
        Button buttonCreate;
        @FXML
        Button buttonGoBack;
        @FXML
        TextField textName;
        @FXML
        TextArea textDescription;

        public void createButtonClick(MouseEvent event){
                if (textName.getText().isBlank() || textDescription.getText().isBlank()){

                }
        }
}

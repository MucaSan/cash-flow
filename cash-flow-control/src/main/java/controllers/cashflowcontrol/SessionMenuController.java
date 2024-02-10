package controllers.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
public class SessionMenuController implements Initializable {
    @FXML
    private Image imgEdit;
    @FXML
    private Image imgDelete;
    @FXML
    private Image imgClear;
    @FXML
    private Button buttonCreate;
    @FXML
    private TextField textFilter;
    @FXML
    private TableView<Session> tableSession;
    @FXML
    private TableColumn<Session, Integer> colID;
    @FXML
    private TableColumn<Session, String> colName;
    @FXML
    private TableColumn<Session, Image> colAction;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

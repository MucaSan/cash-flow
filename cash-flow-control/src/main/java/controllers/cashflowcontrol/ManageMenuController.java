package controllers.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.Manage;
import alerts.classes.cashflowcontrol.AlertManage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.w3c.dom.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
public class ManageMenuController extends AlertManage implements Initializable{
    @FXML
    private TextField textName;
    @FXML
    private TextField textPrice;
    @FXML
    private TextField textStartDate;
    @FXML
    private TextField textEndDate;
    @FXML
    private TextField textSession;
    @FXML
    private TextField textPayment;
    @FXML
    private Button buttonGoBack;
    @FXML
    private Button buttonCreate;
    @FXML
    private Button buttonFilter;
    @FXML
    private Button buttonClear;
    @FXML
    private TableView<Manage> tableManage;
    @FXML
    private TableColumn<Manage, Integer> colID;
    @FXML
    private TableColumn<Manage, String > colName;
    @FXML
    private TableColumn<Manage, String> colSession;
    @FXML
    private TableColumn<Manage, String> colPayment;
    @FXML
    private TableColumn<Manage, LocalDateTime> colDate;
    @FXML
    private TableColumn<Manage, List<ImageView>> colAction;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

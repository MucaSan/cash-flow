package controllers.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class SessionMenuController implements Initializable {
    @FXML
    private ImageView imgEdit;
    @FXML
    private ImageView imgDelete;
    @FXML
    private ImageView imgClear;
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
    private TableColumn<Session, List<ImageView>> colAction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Session> listData = FXCollections.observableArrayList();
        List<ImageView> test = new ArrayList<>();
        test.add(imgDelete);
        test.add(imgEdit);
        listData.add(new Session(1, "test", test));
    }
}

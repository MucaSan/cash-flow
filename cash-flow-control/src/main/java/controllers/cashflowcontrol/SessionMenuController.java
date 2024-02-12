package controllers.cashflowcontrol;
import com.almasb.fxgl.entity.action.Action;
import generalPurposesClasses.cashflowcontrol.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
public class SessionMenuController implements Initializable {
    @FXML
    private AnchorPane root;
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
        for (int i = 0; i <= 2; i++){
            final int j = i;
            ImageView test = new ImageView(){
                {
                    setId(String.format("%d", j));
                }
                {
                    setImage(imgEdit.getImage());
                }
            };
            ImageView test2 = new ImageView(imgDelete.getImage());
            test.setFitHeight(25);
            test.setOnMouseClicked(mouseEvent -> System.out.println(tableSession.getItems().get(Integer.parseInt(test.getId())).getId()));
            test.setFitWidth(25);
            test2.setFitHeight(25);
            test2.setFitWidth(25);
            listData.add(new Session(i, "test",new HBox(test,test2)));
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("actions"));
        tableSession.setItems(listData);
    }
}

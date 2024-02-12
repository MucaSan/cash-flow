package controllers.cashflowcontrol;
import com.almasb.fxgl.entity.action.Action;
import connection.cashflowcontrol.DatabaseConnection;
import generalPurposesClasses.cashflowcontrol.Session;
import generalVariables.cashflowcontrol.GlobalVariables;
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
import java.sql.SQLException;
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
        try {
            GlobalVariables.SQL = "SELECT * FROM SessionDB where name= '" + GlobalVariables.userLogged + "';";
            GlobalVariables.connection = GlobalVariables.database.getConnection();
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
            GlobalVariables.nIterations = 0;
            while (GlobalVariables.resultSet.next()) {
                ImageView tempEdit = new ImageView() {
                    {
                        setId(String.format("edit%d", GlobalVariables.nIterations));
                        setImage(imgEdit.getImage());
                        setFitWidth(25);
                        setFitHeight(25);
                        setOnMouseClicked(mouseEvent -> System.out.println(tableSession.getItems()
                                .get(GlobalVariables.nIterations).getId()));
                    }

                };
                ImageView tempDelete = new ImageView() {
                    {
                        setId(String.format("delete%d", GlobalVariables.nIterations));
                        setImage(imgDelete.getImage());
                        setFitWidth(25);
                        setFitHeight(25);
                        setOnMouseClicked(mouseEvent -> System.out.println(tableSession.getItems()
                                .get(GlobalVariables.nIterations).getId()));
                    }
                };

                listData.add(new Session(GlobalVariables.nIterations, GlobalVariables.resultSet
                        .getNString(2),
                        new HBox(tempEdit, tempDelete)));
                GlobalVariables.nIterations+=1;
            }
        } catch (SQLException e) {
            e.getCause();
            e.printStackTrace();
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("actions"));
        tableSession.setItems(listData);
    }

}

package controllers.cashflowcontrol;
import alerts.classes.cashflowcontrol.AlertSession;
import com.almasb.fxgl.entity.action.Action;
import connection.cashflowcontrol.DatabaseConnection;
import generalPurposesClasses.cashflowcontrol.ChangeWindow;
import generalPurposesClasses.cashflowcontrol.Session;
import generalVariables.cashflowcontrol.GlobalVariables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
public class SessionMenuController extends AlertSession implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgEdit;
    @FXML
    private ImageView imgFilter;
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
            GlobalVariables.SQL = "SELECT * FROM SessionDB where userAssociated= '" + GlobalVariables.userLogged + "';";
            GlobalVariables.connection = GlobalVariables.database.getConnection();
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
            GlobalVariables.nIterations = 0;
            while (GlobalVariables.resultSet.next()) {
                String name = GlobalVariables.resultSet.getNString(2);
                String description = GlobalVariables.resultSet.getNString(4);
                ImageView tempEdit = new ImageView() {
                    {
                        setId(String.format("edit%d", GlobalVariables.nIterations));
                        setImage(imgEdit.getImage());
                        setFitWidth(25);
                        setFitHeight(25);
                        final int j = GlobalVariables.nIterations;
                        setOnMouseClicked(mouseEvent -> {
                            try{
                                FXMLLoader loader = new FXMLLoader(getClass()
                                        .getResource("/fxml.controllers.session/session.fxml"));
                                root = loader.load();
                                SessionController sessionController = loader.getController();
                                sessionController.displayName(name);
                                sessionController.displayDescription(description);
                                Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                               Scene scene = new Scene(root);
                               stage.setScene(scene);
                                sessionController.buttonSave.setVisible(true);
                                sessionController.buttonCreate.setVisible(false);
                                sessionController.buttonSave.setDisable(false);
                                sessionController.buttonCreate.setDisable(true);
                                sessionController.nameAssociated = name;
                            } catch (IOException e){
                                e.getCause();
                                e.printStackTrace();
                            }
                        });
                    }

                };
                ImageView tempDelete = new ImageView() {
                    {
                        setId(String.format("delete%d", GlobalVariables.nIterations));
                        setImage(imgDelete.getImage());
                        setFitWidth(25);
                        setFitHeight(25);
                        final int j = GlobalVariables.nIterations;
                        setOnMouseClicked(mouseEvent ->{
                            try{
                                alertMessage(name);
                                GlobalVariables.window = new ChangeWindow<MouseEvent>(mouseEvent,"/fxml.controllers.session/sessionMenu.fxml");
                                GlobalVariables.window.setNewWindowFromMouseClick(GlobalVariables.window.getActionMouse(),
                                        GlobalVariables.window.getPathToFXMLFile());
                            } catch(IOException e){
                                e.printStackTrace();
                                e.getCause();
                            }
                        });

                    }
                };
                GlobalVariables.nIterations+=1;
                listData.add(new Session(GlobalVariables.nIterations, GlobalVariables.resultSet
                        .getNString(2),
                        new HBox(tempEdit, tempDelete)));
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
    public void buttonCreateClick(MouseEvent event){
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/fxml.controllers.session/session.fxml"));
        try{
            Parent root1 = (Parent) fxmlLoader1.load();
            Stage stage = new Stage();
            stage.setTitle("CREATE SESSION");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
            e.getCause();
        }


    }
    public void buttonGoBackClick(MouseEvent event) throws IOException{
        GlobalVariables.window = new ChangeWindow<MouseEvent>(event,"/fxml.controllers.menu/menu.fxml");
        GlobalVariables.window.setNewWindowFromMouseClick(GlobalVariables.window.getActionMouse(),
                GlobalVariables.window.getPathToFXMLFile());
    }
    public void textFilterChange(InputMethodEvent event){
        System.out.println("Text has changed");
        System.out.println("We'll try to change the event, for now, we just need to debug this code and see where's the error");
    }
    public void clearFilterClick(MouseEvent event){
        // set an action that happens after the event filter element is clicked! in another words, adds only the
        // elements.
        textFilter.setText("");
    }
}

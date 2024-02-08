package controllers.cashflowcontrol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import connection.cashflowcontrol.DatabaseConnection;
import generalVariables.cashflowcontrol.GlobalVariables;
import javafx.scene.input.MouseEvent;
import alerts.classes.cashflowcontrol.AlertManage;

public class ManageController implements Initializable {
    @FXML
    private TextField   textName;
    @FXML
    private TextField   textSource;
    @FXML
    private TextField   textAmount;
    @FXML
    private ComboBox<String> comboSession;
    @FXML
    private List<String> listString;
    @FXML
    private ComboBox<String> comboPayment;
    private Button  buttonCreate;
    @FXML
    private Button buttonGoBack;

    public void buttonCreateClick(MouseEvent event){
        GlobalVariables.database = new DatabaseConnection();
        GlobalVariables.connection = GlobalVariables.database.getConnection();
//        GlobalVariables.SQL = "SELECT * FROM TransactionDB where '" + textName.getText() +  "'";
        try{
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(
                    GlobalVariables.SQL);
            if (GlobalVariables.resultSet.next()){
                alertRecordAssociated();
            }
        }catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GlobalVariables.database = new DatabaseConnection();
        GlobalVariables.connection = GlobalVariables.database.getConnection();
        GlobalVariables.SQL = "SELECT * FROM SessionDB where userAssociated = '" +  GlobalVariables.userLogged  +
                "' ;";
        try{
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
            ObservableList<String> sessionList = FXCollections.observableArrayList();
            while(GlobalVariables.resultSet.next()){
                sessionList.add(new String(GlobalVariables.resultSet.getString(2)));
            }
            comboSession.setItems(sessionList);
            ObservableList<String> paymentList = FXCollections.observableArrayList();
            GlobalVariables.SQL = "SELECT * FROM PaymentDB where userAssociated = '" +  GlobalVariables.userLogged  +
                    "' ;";
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
            while(GlobalVariables.resultSet.next()){
                paymentList.add(new String(GlobalVariables.resultSet.getString(2)));
            }
            comboPayment.setItems(paymentList);

        } catch(SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        comboPayment.setPromptText("Select a type of payment");
        comboSession.setPromptText("Select a session to partion");
    }
}

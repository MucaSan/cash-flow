package controllers.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.ChangeWindow;
import interfaces.cashflowcontrol.CleanTextFields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
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

public class ManageController extends AlertManage implements Initializable, CleanTextFields {
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
    @FXML
    private Button  buttonCreate;
    @FXML
    private Button buttonGoBack;
    public void buttonCreateClick(MouseEvent event){
        boolean expressionToEvaluate = (textName.getText().isBlank() || textSource.getText().isBlank()
        || textAmount.getText().isBlank() || comboSession.getValue().isBlank() ||
                comboPayment.getValue().isBlank());
        if (expressionToEvaluate){
            alertBlank();
            return;
        }
        GlobalVariables.database = new DatabaseConnection();
        GlobalVariables.connection = GlobalVariables.database.getConnection();
        GlobalVariables.SQL = "SELECT * FROM TransactionDB where '" + textName.getText() +  "';";
        try{
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(
                    GlobalVariables.SQL);
            if (GlobalVariables.resultSet.next()){
                alertRecordAssociated();
            }
            else{
                int temp = 1;
                int idSession = 0;
                int idPayment = 0;
                GlobalVariables.SQL = "SELECT * FROM TransactionDB;";
                GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(
                        GlobalVariables.SQL);
                while (GlobalVariables.resultSet.next()){
                    temp+=1;
                }
                GlobalVariables.SQL = "SELECT id FROM SessionDB where name = " +
                        "'"  +  comboSession.getValue()  + "';";
                GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(
                        GlobalVariables.SQL);
                if (GlobalVariables.resultSet.next()){
                    idSession = GlobalVariables.resultSet.getInt(1);
                }
                GlobalVariables.SQL = "SELECT id FROM PaymentDB where name = " +
                        "'"  +  comboPayment.getValue()  + "';";
                GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(
                        GlobalVariables.SQL);
                if (GlobalVariables.resultSet.next()){
                    idPayment = GlobalVariables.resultSet.getInt(1);
                }
                GlobalVariables.SQL = "INSERT INTO TransactionDB values('" + temp + "', " +
                        "'" + idSession  +  "', '" + idPayment +  "' , '" + textName.getText() + "'" +
                        ",'" + textSource.getText()  +  "', '" + textAmount.getText() + "', " +
                        "'"  + GlobalVariables.userLogged + "')";
                GlobalVariables.statement.executeUpdate(GlobalVariables.SQL);
                alertSuccess();
                cleanTextFields();
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
    public void cleanTextFields() {
        textName.setText("");
        textSource.setText("");
        textAmount.setText("");
        comboSession.setValue("");
        comboPayment.setValue("");
    }
    public void clickButtonGoBack(MouseEvent event) throws IOException{
        GlobalVariables.window = new ChangeWindow<MouseEvent>(event,"/fxml.controllers.menu/menu.fxml");
        GlobalVariables.window.setNewWindowFromMouseClick(GlobalVariables.window.getActionMouse(),
                GlobalVariables.window.getPathToFXMLFile());
    }

}

package controllers.cashflowcontrol;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import connection.cashflowcontrol.DatabaseConnection;
import generalVariables.cashflowcontrol.GlobalVariables;


public class ManageController implements Initializable {
    private TextField   textName;
    private TextField   textSource;
    private TextField   textAmount;
    private ComboBox<String> comboSession;
    private ComboBox<String> comboPayment;
    private Button  buttonCreate;
    private Button buttonGoBack;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GlobalVariables.database = new DatabaseConnection();
        GlobalVariables.connection = GlobalVariables.database.getConnection();
        GlobalVariables.SQL = "SELECT * FROM SessionDB where userAssociated = '" +  GlobalVariables.userLogged  +
                "' ;";
        try{
            GlobalVariables.statement = GlobalVariables.connection.createStatement();
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
            while(GlobalVariables.resultSet.next()){
                comboSession.getItems().add(GlobalVariables.resultSet.getString(1));
            }
            GlobalVariables.SQL = "SELECT * FROM PaymentDB where userAssociated = '" +  GlobalVariables.userLogged  +
                    "' ;";
            GlobalVariables.resultSet = GlobalVariables.statement.executeQuery(GlobalVariables.SQL);
            while(GlobalVariables.resultSet.next()){
                comboPayment.getItems().add(GlobalVariables.resultSet.getString(1));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }
}

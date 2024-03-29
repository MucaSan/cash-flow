package generalVariables.cashflowcontrol;
import generalPurposesClasses.cashflowcontrol.ChangeWindow;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connection.cashflowcontrol.DatabaseConnection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GlobalVariables {
    public static URL url;
    public static ResourceBundle resourceBundle;
    public static String SQL;
    public static String userLogged;
    public static Scene scene;
    public static Parent root;
    public static Stage stage;
    public static DatabaseConnection database;
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static ResultSetMetaData metaData;
    public static ChangeWindow window;
    public static int nIterations;
    public String getSQL(){
        return SQL;
    }


}

package generalPurposesClasses.cashflowcontrol;

import generalVariables.cashflowcontrol.GlobalVariables;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerifyExistanceValue<T> {
    private int idColumn = 0;
    private String SQL;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public boolean hasRepeatedVariable(String database, String nameVariable, T valueVariable)
            throws SQLException {
        if (valueVariable instanceof Integer) {
            idColumn = 1;
            SQL = "SELECT * FROM " + database + " WHERE userAssociated=" +
                    "'" + GlobalVariables.userLogged + "' AND " + nameVariable +
                    "= '" + valueVariable + "';";
            connection = GlobalVariables.database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
        }
        while (resultSet.next()) {
            if (resultSet.getInt(idColumn) + 1 != (Integer) valueVariable ) {
                return true;
            }
        }
        return false;
    }

    public String toString(T value) {
        try{
             if (value instanceof Integer) {
                 return Integer.toString((Integer) value);
                }
             } catch(Exception e) {
                e.printStackTrace();
                e.getCause();
             }
        return null;
        }

    }



package ru.kucherova.furniturefactory.model;

import ru.kucherova.furniturefactory.database.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    DataBase dataBase;
    public Auth(){
        this.dataBase = new DataBase();
    }
    public String getRole(String login, String password) throws SQLException {
        String query = "SELECT r.name FROM User u " +
                "INNER JOIN UserRole ur ON u.id = ur.user_id " +
                "INNER JOIN Role r ON ur.role_id = r.id " +
                "WHERE u.username = \""+ login +"\" AND u.password_hash = \""+ password +"\"";
        PreparedStatement statement = dataBase.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String role = resultSet.getString("name");
            resultSet.close();
            statement.close();
            return role;
        } else {
            throw new SQLException("No rows found");
        }
    }

}

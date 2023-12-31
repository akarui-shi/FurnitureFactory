package ru.kucherova.furniturefactory.model;

import ru.kucherova.furniturefactory.database.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private DataBase dataBase;

    public Store( DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public List<String> getAll() throws SQLException {
        Statement statement = dataBase.connection.createStatement();

        String componentQuery = "SELECT * FROM Store";
        ResultSet componentResult = statement.executeQuery(componentQuery);
        List<String> componentItems = new ArrayList<>();
        while (componentResult.next()) {
            String componentName = componentResult.getString("address");
            componentItems.add(componentName);
        }
        statement.close();
        return componentItems;
    }

    public void add(String type, Double cost) throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String query = "NSERT INTO Component (type, cost) " +
                "VALUES (" + type + ", "  + cost + "), ";
        statement.executeUpdate(query);
    }

    public void delite() throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String furnitureQuery = "DELETE FROM Furniture WHERE type = 'Chair';";
        int rowsAffected = statement.executeUpdate(furnitureQuery);
    }

    public List<String> getItemDataFromDatabase(DataBase dataBase,  String type) throws SQLException {
        String query = "SELECT name, fax_number FROM Store WHERE address = \"" + type + "\"";

        PreparedStatement statement = dataBase.connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String fax_number = resultSet.getString("fax_number");
            data.add(name);
            data.add(fax_number);
        }
        resultSet.close();
        statement.close();

        return data;
    }
}
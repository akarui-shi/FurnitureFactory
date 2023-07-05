package ru.kucherova.furniturefactory.model;

import ru.kucherova.furniturefactory.database.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Component {
    private DataBase dataBase;

    public Component( DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void add(int line_id, String type, String article, double price){
    }

    public List<String> getAll() throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String conponentQuery = "SELECT * FROM Component";
        ResultSet furnitureResult = statement.executeQuery(conponentQuery);
        List<String> furnitureItems = new ArrayList<>();
        while (furnitureResult.next()) {
            String furnitureName = furnitureResult.getString("type");
            furnitureItems.add(furnitureName);
        }
        statement.close();

        return furnitureItems;
    }

    public List<String> getFromComponent(String component) throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String furnitureQuery = "SELECT f.type FROM Furniture f " +
                "JOIN FurnitureComponent fc ON f.id = fc.furniture_id " +
                "JOIN Component c ON fc.component_id = c.id " +
                "WHERE c.type = "+ component + ";";

        ResultSet furnitureResult = statement.executeQuery(furnitureQuery);
        List<String> furnitureItems = new ArrayList<>();
        while (furnitureResult.next()) {
            String furnitureName = furnitureResult.getString("type");
            furnitureItems.add(furnitureName);
        }
        statement.close();

        return furnitureItems;
    }

    public void add(String line, String type, String article, Double price) throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        int lineQuery =  Integer.parseInt("SELECT id FROM Line WHERE name = "+ line +";");
        String query = "NSERT INTO Furniture (line_id, type, article, price) " +
                "VALUES (" + lineQuery + ", " + type + ", " + article + ", " + price + "), ";
        int rowsAffected = statement.executeUpdate(query);
    }

    public void delite() throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String furnitureQuery = "DELETE FROM Furniture WHERE type = 'Chair';";
        int rowsAffected = statement.executeUpdate(furnitureQuery);
    }

    public List<String> getItemDataFromDatabase(DataBase dataBase,  String type) throws SQLException {
        String query = "SELECT Furniture.type\n" +
                "FROM Furniture\n" +
                "INNER JOIN FurnitureComponent ON Furniture.id = FurnitureComponent.furniture_id\n" +
                "INNER JOIN Component ON FurnitureComponent.component_id = Component.id\n" +
                "WHERE Component.type = \"" + type + "\"";

        PreparedStatement statement = dataBase.connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            String furniture = resultSet.getString("type");
            data.add(furniture);
        }
        resultSet.close();
        statement.close();

        return data;
    }
}

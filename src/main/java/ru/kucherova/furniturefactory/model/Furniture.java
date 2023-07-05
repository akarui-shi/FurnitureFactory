package ru.kucherova.furniturefactory.model;

import ru.kucherova.furniturefactory.database.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Furniture {
    private DataBase dataBase;


    public Furniture( DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void add(int line_id, String type, String article, double price){
    }

    public List<String> getAll() throws SQLException {
        Statement statement = dataBase.connection.createStatement();

        String furnitureQuery = "SELECT * FROM Furniture";
        ResultSet furnitureResult = statement.executeQuery(furnitureQuery);
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

    public List<String>   getItemDataFromDatabase(DataBase dataBase, String type) throws SQLException {
        // Запрос для получения компонентов, линий и магазинов для заданного предмета мебели
        String query = "SELECT Component.type AS component, Line.name AS line, Store.address " +
                "FROM Furniture " +
                "JOIN Line ON Furniture.line_id = Line.id " +
                "JOIN FurnitureComponent ON Furniture.id = FurnitureComponent.furniture_id " +
                "JOIN Component ON FurnitureComponent.component_id = Component.id " +
                "JOIN FurnitureStore ON Furniture.id = FurnitureStore.furniture_id " +
                "JOIN Store ON FurnitureStore.store_id = Store.id " +
                "WHERE Furniture.type = \"" + type + "\"";

        PreparedStatement statement = dataBase.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<String> data = new ArrayList<>();

        Set<String> setComponent = new HashSet<>();
        Set<String> setLine = new HashSet<>();
        Set<String> setShop = new HashSet<>();
        while (resultSet.next()) {
            setComponent.add(resultSet.getString("component"));
            setLine.add(resultSet.getString("line"));
            setShop.add(resultSet.getString("address"));
        }
        String component = setComponent.toString().replace("[", "").replace("]", "");
        String line = setLine.toString().replace("[", "").replace("]", "");
        String shop = setShop.toString().replace("[", "").replace("]", "");

        data.add(component);
        data.add(line);
        data.add(shop);
        System.out.println(data);

        resultSet.close();
        statement.close();

        return data;
    }




}

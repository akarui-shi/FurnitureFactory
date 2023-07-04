package ru.kucherova.furniturefactory.model;

import ru.kucherova.furniturefactory.database.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Line {
    //private final ListView<String> furnitureList;

    private DataBase dataBase;


    public Line( DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void add(int line_id, String type, String article, double price){
    }

    public List<String> getAll() throws SQLException {
        Statement statement = dataBase.connection.createStatement();

        String furnitureQuery = "SELECT * FROM Line";
        ResultSet furnitureResult = statement.executeQuery(furnitureQuery);
        List<String> furnitureItems = new ArrayList<>();
        while (furnitureResult.next()) {
            String furnitureName = furnitureResult.getString("name");
            furnitureItems.add(furnitureName);
        }
        statement.close();

        return furnitureItems;
    }

    public List<String> getFromComponent(String component) throws SQLException {
        Statement statement = dataBase.connection.createStatement();
//        String furnitureQuery = "SELECT c.type, c.cost, fc.quantity " +
//                "FROM Furniture f JOIN FurnitureComponent fc ON f.id = fc.furniture_id " +
//                "JOIN Component c ON fc.component_id = c.id " +
//                "WHERE f.type = " + component + ";";
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

    public List<String>  getItemDataFromDatabase(DataBase dataBase) throws SQLException {

        // Запрос для получения компонентов, линии, заказов и магазинов для заданного предмета мебели
        String query = "SELECT Component.type AS component, Line.name AS line, Order.date, Store.address " +
                "FROM Furniture " +
                "JOIN Line ON Furniture.line_id = Line.id " +
                "JOIN FurnitureComponent ON Furniture.id = FurnitureComponent.furniture_id " +
                "JOIN Component ON FurnitureComponent.component_id = Component.id " +
                "JOIN OrderFurniture ON Furniture.id = OrderFurniture.furniture_id " +
                "JOIN `Order` ON OrderFurniture.order_id = `Order`.id " +
                "JOIN Store ON `Order`.store_id = Store.id " +
                "WHERE Furniture.type =" + this;

        PreparedStatement statement = dataBase.connection.prepareStatement(query);
        statement.setString(1, this.toString() );

        ResultSet resultSet = statement.executeQuery();

        List<String> data = new ArrayList<>();
        if (resultSet.next()) {
            String component = resultSet.getString("component");
            String line = resultSet.getString("line");
            String order = resultSet.getString("date");
            String shop = resultSet.getString("address");

            data.add(component);
            data.add(line);
            data.add(order);
            data.add(shop);
        }

        resultSet.close();
        statement.close();

        return data;
    }




}
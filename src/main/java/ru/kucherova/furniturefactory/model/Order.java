package ru.kucherova.furniturefactory.model;

import ru.kucherova.furniturefactory.database.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Order {

    Client client;
    Admin admin;
    private DataBase dataBase;


    public Order(DataBase dataBase, Client client) {
        this.dataBase = dataBase;
        this.client = client;
    }

    public Order(DataBase dataBase, Admin admin) {
        this.dataBase = dataBase;
        this.admin = admin;
    }

    public Order(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public List<String> getAll(String login) throws SQLException {
        Statement statement = dataBase.connection.createStatement();

        String componentQuery = "SELECT `Order`.*\n" +
                    "FROM `Order`\n" +
                    "JOIN `User` ON `Order`.user_id = `User`.id\n" +
                    "WHERE `User`.username = \"" + login + "\";";
        ResultSet componentResult = statement.executeQuery(componentQuery);
        List<String> componentItems = new ArrayList<>();
        while (componentResult.next()) {
            String componentName = componentResult.getString("name");
            componentItems.add(componentName);
        }
        statement.close();
        return componentItems;
    }

    public List<String> getAll() throws SQLException {
        Statement statement = dataBase.connection.createStatement();

        String componentQuery = "SELECT `Order`.*\n" +
                "FROM `Order`;";
        ResultSet componentResult = statement.executeQuery(componentQuery);
        List<String> componentItems = new ArrayList<>();
        while (componentResult.next()) {
            String componentName = componentResult.getString("name");
            componentItems.add(componentName);
        }
        statement.close();
        return componentItems;
    }

    public List<String> getFromComponent(String furniture) throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String componentQuery = "SELECT c.type, c.cost, fc.quantity " +
                "FROM Furniture f JOIN FurnitureComponent fc ON f.id = fc.furniture_id " +
                "JOIN Component c ON fc.component_id = c.id " +
                "WHERE f.type = " + furniture + ";";

        ResultSet componentResult = statement.executeQuery(componentQuery);
        List<String> furnitureItems = new ArrayList<>();
        while (componentResult.next()) {
            String furnitureName = componentResult.getString("type");
            furnitureItems.add(furnitureName);
        }
        statement.close();

        return furnitureItems;
    }

    public void add(int line_id, String type, String article, double price) throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String query = "INSERT INTO Order (line_id, type, article, price) " +
                "VALUES (" + line_id + ", '" + type + "', '" + article + "', " + price + ")";
        statement.executeUpdate(query);
        statement.close();
    }

    public void delite() throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String furnitureQuery = "DELETE FROM Furniture WHERE type = 'Chair';";
        int rowsAffected = statement.executeUpdate(furnitureQuery);
    }

    public List<String> getItemDataFromDatabase(DataBase dataBase,  String type) throws SQLException {
        // Запрос для получения компонентов, линии, заказов и магазинов для заданного предмета мебели
        String query = "SELECT `Order`.date, Store.name\n" +
                "FROM `Order`\n" +
                "JOIN `User` ON `Order`.user_id = `User`.id\n" +
                "JOIN Store ON `Order`.store_id = Store.id\n" +
                "WHERE `Order`.name = \"" + type + "\" AND `User`.username = \"" + client.login + "\"";
        PreparedStatement statement = dataBase.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            String date = resultSet.getString("date");
            data.add(date);
            String name = resultSet.getString("name");
            data.add(name);
        }

        resultSet.close();
        statement.close();

        return data;
    }

    public List<String> getItemDataFromDatabaseForAdmin(DataBase dataBase,  String type) throws SQLException {

        // Запрос для получения компонентов, линии, заказов и магазинов для заданного предмета мебели
        String query = "SELECT `Order`.date, Store.name\n" +
                "FROM `Order`\n" +
                "JOIN Store ON `Order`.store_id = Store.id\n" +
                "WHERE `Order`.name = \"" + type + "\";";

        //SELECT `Order`.date, Store.name FROM `Order` INNER JOIN `User` ON `Order`.user_id = `User`.id INNER JOIN Store ON `Order`.store_id = Store.id WHERE `Order`.`name` = "TP1-8309" AND `User`.`username` = "john_doe";

        PreparedStatement statement = dataBase.connection.prepareStatement(query);
        //statement.setString(1, this.toString() );

        ResultSet resultSet = statement.executeQuery();

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            String date = resultSet.getString("date");
            data.add(date);
            String name = resultSet.getString("name");
            data.add(name);
        }

        resultSet.close();
        statement.close();

        return data;
    }

    public List<String> getFurniture(DataBase dataBase,  String type) throws SQLException {

        // Запрос для получения компонентов, линии, заказов и магазинов для заданного предмета мебели
        String query = "SELECT Furniture.*\n" +
                "FROM Furniture\n" +
                "INNER JOIN OrderFurniture ON Furniture.id = OrderFurniture.furniture_id\n" +
                "INNER JOIN `Order` ON OrderFurniture.order_id = `Order`.id\n" +
                "WHERE `Order`.name = \"" + type + "\";";

        //SELECT `Order`.date, Store.name FROM `Order` INNER JOIN `User` ON `Order`.user_id = `User`.id INNER JOIN Store ON `Order`.store_id = Store.id WHERE `Order`.`name` = "TP1-8309" AND `User`.`username` = "john_doe";

        PreparedStatement statement = dataBase.connection.prepareStatement(query);
        //statement.setString(1, this.toString() );

        ResultSet resultSet = statement.executeQuery();

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            String date = resultSet.getString("type");
            data.add(date);
        }

        resultSet.close();
        statement.close();

        return data;
    }
}

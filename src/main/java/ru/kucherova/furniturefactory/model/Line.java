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

    public void add(String line) throws SQLException {
        Statement stmt = dataBase.connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS id FROM `Line`");
        int lineId = 0;
        while (rs.next()) {
            lineId = rs.getInt("id");
        }
//        PreparedStatement statement = dataBase.connection.prepareStatement("SELECT MAX(id) AS id FROM `Line`");
//        ResultSet resultSet = statement.executeQuery();
//        int lineId = 0;
//        while (resultSet.next()) {
//            lineId = resultSet.getInt("id");
//        }

        rs.close();
        stmt.close();

        lineId+=1;
        System.out.println(lineId);

        String query = "INSERT INTO Line (id, name) " +
                "VALUES (" + lineId + ", \"" + line + "\"); ";
        PreparedStatement stmt2 = dataBase.connection.prepareStatement(query);
        stmt2.executeUpdate();
        stmt2.close();

    }

    public void delite() throws SQLException {
        Statement statement = dataBase.connection.createStatement();
        String furnitureQuery = "DELETE FROM Furniture WHERE type = 'Chair';";
        int rowsAffected = statement.executeUpdate(furnitureQuery);
    }

    public List<String> getItemDataFromDatabase(DataBase dataBase,  String type) throws SQLException {

        // Запрос для получения компонентов, линии, заказов и магазинов для заданного предмета мебели
        String query = "SELECT Furniture.type\n" +
                "FROM Furniture\n" +
                "INNER JOIN Line ON Furniture.line_id = Line.id\n" +
                "WHERE Line.name = \"" + type + "\"";

        PreparedStatement statement = dataBase.connection.prepareStatement(query);
        //statement.setString(1, this.toString() );

        ResultSet resultSet = statement.executeQuery();

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            String furniture = resultSet.getString("type");
            data.add(furniture);
        }

        System.out.println(data);

        resultSet.close();
        statement.close();

        return data;
    }




}
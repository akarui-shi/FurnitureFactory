package ru.kucherova.furniturefactory.model;



import javafx.scene.control.ListView;
import ru.kucherova.furniturefactory.database.DataBase;

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

        String componentQuery = "SELECT * FROM Component";
        ResultSet componentResult = statement.executeQuery(componentQuery);
        List<String> componentItems = new ArrayList<>();
        while (componentResult.next()) {
            String componentName = componentResult.getString("type");
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



}

package ru.kucherova.furniturefactory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import ru.kucherova.furniturefactory.database.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
    DataBase dataBase;

    public String login;
    public String password;

    public ListView<String> furnitureList;
    public ListView<String> componentList;
    public ListView<String> lineList;
    public ListView<String> shopList;
    public ListView<String> orgerList;
    public Client(String login, String password) throws SQLException {
        dataBase = new DataBase();
        if (checkCredentials(login, password)){
            System.out.println("заебись");
        } else System.out.println("хуйня");
        this.login = login;
        this.password = password;

        // Создаем списки элементов
        furnitureList = new ListView<>();
        Furniture furniture = new Furniture(dataBase);
        ObservableList<String> furnitureItems = FXCollections.observableArrayList(
                furniture.getAll());
        furnitureList.setItems(furnitureItems);

        componentList = new ListView<>();
        Component component = new Component(dataBase);
        ObservableList<String> componentItems = FXCollections.observableArrayList(
                component.getAll());
        componentList.setItems(componentItems);

        lineList = new ListView<>();
        Line line = new Line(dataBase);
        ObservableList<String> lineItems = FXCollections.observableArrayList(
                line.getAll());
        lineList.setItems(lineItems);

        shopList = new ListView<>();
        Store store = new Store(dataBase);
        ObservableList<String> shopItems = FXCollections.observableArrayList(
                store.getAll());
        shopList.setItems(shopItems);

        orgerList = new ListView<>();
        Order order = new Order(dataBase, this);
        ObservableList<String> orderItems = FXCollections.observableArrayList(
                order.getAll());
        orgerList.setItems(orderItems);
    }

     private boolean checkCredentials(String login, String password) {
        // Проверяем, есть ли пользователь с заданным логином в базе данных
        String query = "SELECT COUNT(*) FROM `User` WHERE username = \""+ login + "\";" ;  //\""+ login + "\";
        try (PreparedStatement statement = dataBase.connection.prepareStatement(query)) {
            //statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count == 0) {
                        return false; // Пользователь не найден
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Проверяем, совпадает ли пароль с хранящимся в базе данных
        String passwordQuery = "SELECT password_hash FROM `User` WHERE username = \""+ login + "\";";
        try (PreparedStatement passwordStatement = dataBase.connection.prepareStatement(passwordQuery)) {
            //passwordStatement.setString(1, login);
            try (ResultSet passwordResultSet = passwordStatement.executeQuery()) {
                if (passwordResultSet.next()) {
                    String storedPassword = passwordResultSet.getString("password_hash");
                    if (password.equals(storedPassword)) {
                        return true; // Логин и пароль совпадают
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public DataBase getDataBase(){
        return this.dataBase;
    }
}

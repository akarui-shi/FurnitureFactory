package ru.kucherova.furniturefactory.model;

import ru.kucherova.furniturefactory.database.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import java.sql.SQLException;

public class Admin {
    DataBase dataBase;
    Order order;
    Line line;

    public ListView<String> furnitureList;
    public ListView<String> componentList;
    public ListView<String> lineList;
    public ListView<String> shopList;
    public ListView<String> orderList;

    public Admin() throws SQLException {
        dataBase = new DataBase();

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
        line = new Line(dataBase);
        ObservableList<String> listItems = FXCollections.observableArrayList(
                line.getAll());
        lineList.setItems(listItems);

        shopList = new ListView<>();
        Store store = new Store(dataBase);
        ObservableList<String> shopItems = FXCollections.observableArrayList(
                store.getAll());
        shopList.setItems(shopItems);

        orderList = new ListView<>();
        order = new Order(dataBase);
        ObservableList<String> orderItems = FXCollections.observableArrayList(
                 order.getAll());
        orderList.setItems(orderItems);

    }

    public void addLine(String name) throws SQLException {
        line.add(name);
    }

    public void refreshOrderList() throws SQLException {
        orderList.getItems().clear();
        ObservableList<String> orderItems = FXCollections.observableArrayList(
                order.getAll());
        orderList.setItems(orderItems);
    }

    public void refreshLineList() throws SQLException {
        lineList.getItems().clear();
        ObservableList<String> lineItems = FXCollections.observableArrayList(
                line.getAll());
        lineList.setItems(lineItems);
    }
    public DataBase getDataBase(){
        return this.dataBase;
    }

}
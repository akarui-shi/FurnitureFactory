package ru.kucherova.furniturefactory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import ru.kucherova.furniturefactory.database.DataBase;

import java.sql.SQLException;

public class Guest {
    DataBase dataBase;

    public ListView<String> furnitureList;
    public ListView<String> componentList;
    public ListView<String> lineList;
    public ListView<String> shopList;

    public Guest() throws SQLException {
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
        Line line = new Line(dataBase);
        ObservableList<String> orderItems = FXCollections.observableArrayList(
                line.getAll());
        lineList.setItems(orderItems);

        shopList = new ListView<>();
        Store store = new Store(dataBase);
        ObservableList<String> shopItems = FXCollections.observableArrayList(
                store.getAll());
        shopList.setItems(shopItems);

    }
     public DataBase getDataBase(){
        return this.dataBase;
     }
}

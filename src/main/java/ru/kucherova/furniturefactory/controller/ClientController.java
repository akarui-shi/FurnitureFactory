package ru.kucherova.furniturefactory.controller;

import ru.kucherova.furniturefactory.model.*;
import ru.kucherova.furniturefactory.view.*;

import java.sql.SQLException;
public class ClientController  {
    Client client;
    ClientScene clientScene;
    FurnitureScene furnitureScene;
    ComponentScene componentScene;

    LineScene lineScene;
    StoreScene storeScene;

    OrderScene orderScene;


    public ClientController(Client client, ClientScene clientScene){
        this.client = client;
        this.clientScene = clientScene;

        // Привязываем действия к списку Мебели
        client.furnitureList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.furnitureList.getSelectionModel().getSelectedItem();
                try {
                    Furniture furniture = new Furniture(client.getDataBase());
                    furnitureScene = new FurnitureScene(furniture);
                    furnitureScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        client.lineList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.lineList.getSelectionModel().getSelectedItem();
                try {
                    Line line = new Line(client.getDataBase());
                    lineScene = new LineScene(line);
                    lineScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        client.componentList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.componentList.getSelectionModel().getSelectedItem();
                try {
                    Component component = new Component(client.getDataBase());
                    componentScene = new ComponentScene(component);
                    componentScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        client.shopList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.shopList.getSelectionModel().getSelectedItem();
                try {
                    Store store = new Store(client.getDataBase());
                    storeScene = new StoreScene(store);
                    storeScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        client.orgerList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.orgerList.getSelectionModel().getSelectedItem();
                try {
                    Order order = new Order(client.getDataBase(), client);
                    orderScene = new OrderScene(order);
                    orderScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
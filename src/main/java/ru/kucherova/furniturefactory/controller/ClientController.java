package ru.kucherova.furniturefactory.controller;

import ru.kucherova.furniturefactory.model.Client;
import ru.kucherova.furniturefactory.model.Furniture;
import ru.kucherova.furniturefactory.view.ClientScene;
import ru.kucherova.furniturefactory.view.FurnitureScene;

import java.sql.SQLException;
public class ClientController  {
    Client client;
    ClientScene clientScene;
    FurnitureScene furnitureScene;


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
    }
}
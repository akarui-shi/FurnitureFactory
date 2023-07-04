package ru.kucherova.furniturefactory.controller;

import ru.kucherova.furniturefactory.model.*;
import ru.kucherova.furniturefactory.view.*;

import java.sql.SQLException;

public class GuestController {

    Guest guest;
    GuestScene guestScene;
    FurnitureScene furnitureScene;

    ComponentScene componentScene;

    LineScene lineScene;
    StoreScene storeScene;


    public GuestController(Guest guest, GuestScene guestScene){
        this.guest = guest;
        this.guestScene = guestScene;

        // Привязываем действия к списку Мебели
        guest.furnitureList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = guest.furnitureList.getSelectionModel().getSelectedItem();
                try {
                    Furniture furniture = new Furniture(guest.getDataBase());
                    furnitureScene = new FurnitureScene(furniture);
                    furnitureScene.showItemDetails(guest.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        guest.lineList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = guest.lineList.getSelectionModel().getSelectedItem();
                try {
                    Line line = new Line(guest.getDataBase());
                    lineScene = new LineScene(line);
                    lineScene.showItemDetails(guest.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        guest.componentList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = guest.componentList.getSelectionModel().getSelectedItem();
                try {
                    Component component = new Component(guest.getDataBase());
                    componentScene = new ComponentScene(component);
                    componentScene.showItemDetails(guest.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        guest.shopList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = guest.shopList.getSelectionModel().getSelectedItem();
                try {
                    Store store = new Store(guest.getDataBase());
                    storeScene = new StoreScene(store);
                    storeScene.showItemDetails(guest.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

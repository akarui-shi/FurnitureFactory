package ru.kucherova.furniturefactory.controller;

import ru.kucherova.furniturefactory.model.Furniture;
import ru.kucherova.furniturefactory.model.Guest;
import ru.kucherova.furniturefactory.model.Line;
import ru.kucherova.furniturefactory.view.FurnitureScene;
import ru.kucherova.furniturefactory.view.GuestScene;
import ru.kucherova.furniturefactory.view.LineScene;

import java.sql.SQLException;

public class GuestController {

    Guest guest;
    GuestScene guestScene;
    FurnitureScene furnitureScene;

    LineScene lineScene;


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
    }
}

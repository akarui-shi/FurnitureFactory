package ru.kucherova.furniturefactory.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import ru.kucherova.furniturefactory.database.DataBase;
import ru.kucherova.furniturefactory.model.*;
import ru.kucherova.furniturefactory.model.Component;
import ru.kucherova.furniturefactory.model.Order;
import ru.kucherova.furniturefactory.model.Store;


import java.sql.*;

public class GuestScene extends Application  { //линейки, мебель, компоненты, магазины

    Guest guest;

    public GuestScene(Guest guest){
        this.guest = guest;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Создаем графический интерфейс
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        // Создаем TabPane для переключения между списками
        TabPane tabPane = new TabPane();

        Tab lineTab = new Tab("Линии", guest.lineList);
        Tab furnitureTab = new Tab("Мебель", guest.furnitureList);
        Tab componentTab = new Tab("Компоненты", guest.componentList);
        Tab shopTab = new Tab("Магазины", guest.shopList);


        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // запретить закрытие вкладок

        tabPane.getTabs().addAll(lineTab, furnitureTab, componentTab, shopTab);
        furnitureTab.setClosable(false);

        root.getChildren().addAll(tabPane);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(String.valueOf(Guest.class.getResource("furniture.css"))); //получение стиля
        primaryStage.setScene(scene);
        primaryStage.setTitle("Furniture Factory");
        //primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

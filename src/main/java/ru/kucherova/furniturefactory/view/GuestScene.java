package ru.kucherova.furniturefactory.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import ru.kucherova.furniturefactory.FurnitureApp;
import ru.kucherova.furniturefactory.model.*;

import java.sql.SQLException;

public class GuestScene extends Application  {

    Guest guest;
    public GuestScene() throws SQLException {
        this.guest = new Guest();
    }

    public GuestScene(Guest guest){
        this.guest = guest;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setStyle("-fx-background-color: #F5F5F5;"); // Фоновый цвет контейнера

        TabPane tabPane = new TabPane();

        Tab lineTab = new Tab("Линии", guest.lineList);
        Tab furnitureTab = new Tab("Мебель", guest.furnitureList);
        Tab componentTab = new Tab("Компоненты", guest.componentList);
        Tab shopTab = new Tab("Магазины", guest.shopList);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // запретить закрытие вкладок

        tabPane.getTabs().addAll(lineTab, furnitureTab, componentTab, shopTab);
        furnitureTab.setClosable(false);

        root.getChildren().addAll(tabPane);

        Scene scene = new Scene(root, 800, 500);

        scene.getStylesheets().add(String.valueOf(FurnitureApp.class.getResource("furniture.css"))); //получение стиля
        primaryStage.setScene(scene);
        primaryStage.setTitle("Furniture Factory");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

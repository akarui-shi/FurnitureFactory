package ru.kucherova.furniturefactory.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.FurnitureApp;
import ru.kucherova.furniturefactory.model.Client;

import java.util.concurrent.atomic.AtomicReference;

public class ClientScene extends Application {

    public Client client;
    public Button addOrderButton;
    Tab orderTab;

    public Button profileButton;

    public ClientScene(Client client){
        this.client = client;
    }

    @Override
    public void start(Stage primaryStage) {
        AtomicReference<VBox> root = new AtomicReference<>(new VBox());
        root.get().setPadding(new Insets(10));
        root.get().setSpacing(10);

        TabPane tabPane = new TabPane();

        Tab lineTab = new Tab("Линии", client.lineList);
        Tab furnitureTab = new Tab("Мебель", client.furnitureList);
        Tab componentTab = new Tab("Компоненты", client.componentList);
        Tab shopTab = new Tab("Магазины", client.shopList);
        orderTab = new Tab("Ваши заказы", client.orgerList);

        addOrderButton = new Button("Добавить новый заказ");
        orderTab.setContent(new VBox(client.orgerList, addOrderButton));

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // запретить закрытие вкладок

        tabPane.getTabs().addAll(lineTab, furnitureTab, componentTab, shopTab, orderTab);
        furnitureTab.setClosable(false);

        profileButton = new Button("Профиль");

        root.get().getChildren().addAll(profileButton, tabPane);

        Scene scene = new Scene(root.get(), 800, 600);
        scene.getStylesheets().add(String.valueOf(FurnitureApp.class.getResource("furniture.css"))); //получение стиля
        primaryStage.setScene(scene);
        primaryStage.setTitle("Furniture Factory");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
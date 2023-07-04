package ru.kucherova.furniturefactory.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.model.Client;
import ru.kucherova.furniturefactory.model.Guest;

public class ClientScene extends Application { //линейки, мебель, компоненты, магазины

    Client client;

    public ClientScene(Client client){
        this.client = client;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Создаем графический интерфейс
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        // Создаем TabPane для переключения между списками
        TabPane tabPane = new TabPane();

        Tab lineTab = new Tab("Линии", client.lineList);
        Tab furnitureTab = new Tab("Мебель", client.furnitureList);
        Tab componentTab = new Tab("Компоненты", client.componentList);
        Tab shopTab = new Tab("Магазины", client.shopList);
        Tab orderTab = new Tab("Ваши заказы", client.orgerList);

        Button addOrderButton = new Button("Добавить новый заказ");
        orderTab.setContent(new VBox(client.orgerList, addOrderButton));
        addOrderButton.setOnAction(event -> {
            // Ваш код для добавления заказа в базу данных
            // Например:
            String newOrder = "Новый заказ";
            client.orgerList.getItems().add(newOrder);
        });


        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // запретить закрытие вкладок

        tabPane.getTabs().addAll(lineTab, furnitureTab, componentTab, shopTab, orderTab);
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
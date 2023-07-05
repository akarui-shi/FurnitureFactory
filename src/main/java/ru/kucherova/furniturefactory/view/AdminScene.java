package ru.kucherova.furniturefactory.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.FurnitureApp;
import ru.kucherova.furniturefactory.model.Admin;
import ru.kucherova.furniturefactory.model.Guest;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class AdminScene extends Application { //линейки, мебель, компоненты, магазины, все заказы
    public Admin admin;
    public Button addOrderButton;
    public Button addLineButton;
    public Button addFurnitureButton;
    public Button addComponentButton;
    public Button addShopButton;

    public MenuItem deleteFurniture;
    public MenuItem deleteLine;
    public MenuItem deleteComponent;
    public MenuItem deleteStore;
    Tab orderTab;

    public AdminScene(Admin admin){
        this.admin = admin;
    }

    @Override
    public void start(Stage primaryStage) {
        // Создаем графический интерфейс
        AtomicReference<VBox> root = new AtomicReference<>(new VBox());
        root.get().setPadding(new Insets(10));
        root.get().setSpacing(10);

        // Создаем TabPane для переключения между списками
        TabPane tabPane = new TabPane();

        Tab lineTab = new Tab("Линии", admin.lineList);
        Tab furnitureTab = new Tab("Мебель", admin.furnitureList);
        Tab componentTab = new Tab("Компоненты", admin.componentList);
        Tab shopTab = new Tab("Магазины", admin.shopList);
        orderTab = new Tab("Все заказы", admin.orderList);

        addLineButton = new Button("Добавить новую линию");
        addLineButton.setOnAction(event -> {
            // Обработчик нажатия кнопки "Добавить новую линию"
            Stage dialog = new Stage(); // Создаем диалоговое окно
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            VBox dialogVBox = new VBox();
            HBox dialogHBox = new HBox();
            dialogVBox.setPadding(new Insets(10));
            dialogVBox.setSpacing(10);
            Label newLineLabel = new Label("Новая линия:");
            TextField newLineName = new TextField();
            Button addButton = new Button("Добавить");
            addButton.setOnAction(addEvent -> {
                // Обработчик нажатия кнопки "Добавить"
                String name = newLineName.getText();
                try {
                    admin.addLine(name); // Добавляем новую линию в базу данных
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //admin.saveData(); // Сохраняем изменения
                try {
                    admin.refreshLineList(); // Обновляем таблицу
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                dialog.close(); // Закрываем диалоговое окно
            });
            Button cancelButton = new Button("Отмена");
            cancelButton.setOnAction(cancelEvent -> dialog.close()); // Обработчик нажатия кнопки "Отмена"
            dialogHBox.getChildren().addAll(addButton, cancelButton);
            dialogVBox.getChildren().addAll(newLineLabel, newLineName, dialogHBox);
            Scene dialogScene = new Scene(dialogVBox);
            dialog.setScene(dialogScene);
            dialog.showAndWait(); // Отображаем диалоговое окно и ждем, пока пользователь закроет его
        });
        lineTab.setContent(new VBox(admin.lineList, addLineButton));

        addFurnitureButton = new Button("Добавить новую мебель");
        furnitureTab.setContent(new VBox(admin.furnitureList, addFurnitureButton));

        addComponentButton = new Button("Добавить новый компонент");
        componentTab.setContent(new VBox(admin.componentList, addComponentButton));

        addShopButton = new Button("Добавить новый компонент");
        shopTab.setContent(new VBox(admin.shopList, addShopButton));

        addOrderButton = new Button("Добавить новый заказ");
        orderTab.setContent(new VBox(admin.orderList, addOrderButton));

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // запретить закрытие вкладок

        tabPane.getTabs().addAll(lineTab, furnitureTab, componentTab, shopTab, orderTab);
        furnitureTab.setClosable(false);

        root.get().getChildren().addAll(tabPane);

        ContextMenu LineMenu = new ContextMenu();
        deleteLine = new MenuItem("Удалить");
        LineMenu.getItems().add(deleteLine);
        admin.lineList.setContextMenu(LineMenu);

        ContextMenu FurnitureMenu = new ContextMenu();
        deleteFurniture = new MenuItem("Удалить");
        FurnitureMenu.getItems().add(deleteFurniture);
        admin.furnitureList.setContextMenu(FurnitureMenu);

        ContextMenu ComponentMenu = new ContextMenu();
        deleteComponent = new MenuItem("Удалить");
        ComponentMenu.getItems().add(deleteComponent);
        admin.componentList.setContextMenu(ComponentMenu);

        ContextMenu StoreMenu = new ContextMenu();
        deleteStore = new MenuItem("Удалить");
        StoreMenu.getItems().add(deleteStore);
        admin.shopList.setContextMenu(StoreMenu);

        Scene scene = new Scene(root.get(), 800, 500);
        scene.getStylesheets().add(String.valueOf(FurnitureApp.class.getResource("furniture.css"))); //получение стиля
        primaryStage.setScene(scene);
        primaryStage.setTitle("Furniture Factory");
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}
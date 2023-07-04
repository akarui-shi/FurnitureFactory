package ru.kucherova.furniturefactory.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.database.DataBase;
import ru.kucherova.furniturefactory.model.Furniture;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class FurnitureScene {
    Furniture furniture;

    public FurnitureScene(Furniture furniture){
        this.furniture = furniture;
    }


//    public void showItemDetails(DataBase dataBase , String item) throws SQLException {
//        // Создаем окно для отображения данных элемента Мебели
//        Stage itemStage = new Stage();
//        itemStage.setTitle(item);
//
//        // Создаем таблицу для отображения данных элемента Мебели
//        TableView<String> table = new TableView<>();
//
//        TableColumn<String, String> componentColumn = new TableColumn<>("Компонент");
//        TableColumn<String, String> lineColumn = new TableColumn<>("Линия");
//        TableColumn<String, String> orderColumn = new TableColumn<>("Заказ");
//        TableColumn<String, String> shopColumn = new TableColumn<>("Магазин");
//
//        table.getColumns().addAll(componentColumn, lineColumn, orderColumn, shopColumn);
//
//        // Получаем данные для отображения в таблице
//        ObservableList<String> itemData = furniture.getItemDataFromDatabase(dataBase);
//
//        table.setItems(itemData);
//
//        VBox vbox = new VBox();
//        vbox.setPadding(new Insets(10));
//        vbox.setSpacing(10);
//
//        vbox.getChildren().addAll(table);
//
//        Scene itemScene = new Scene(vbox, 800, 600);
//        itemStage.setScene(itemScene);
//        itemStage.show();
//    }


    public void showItemDetails(DataBase dataBase, String item) throws SQLException {
        // Создаем окно для отображения данных элемента Мебели
        Stage itemStage = new Stage();
        itemStage.setTitle(item);

        // Создаем контейнер VBox для отображения данных элемента Мебели
        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);

        // Получаем данные для отображения
        List<String> itemData = furniture.getItemDataFromDatabase(dataBase, item);

        // Создаем метки для отображения данных
        Label componentLabel = new Label("Компоненты: ");
        Label lineLabel = new Label("Линия: ");
        //Label orderLabel = new Label("Заказы: ");
        Label shopLabel = new Label("Магазины: ");

        // Создаем текстовые поля для отображения данных
        Text componentText = new Text(itemData.get(0));
        Text lineText = new Text(itemData.get(1));
        //Text orderText = new Text(itemData.get(2));
        Text shopText = new Text(itemData.get(2));

        // Устанавливаем стили для меток и текстовых полей
        componentLabel.setStyle("-fx-font-weight: bold;");
        lineLabel.setStyle("-fx-font-weight: bold;");
        shopLabel.setStyle("-fx-font-weight: bold;");
        componentText.setStyle("-fx-font-size: 14;");
        lineText.setStyle("-fx-font-size: 14;");
        shopText.setStyle("-fx-font-size: 14;");

        // Добавляем метки и текстовые поля в контейнер
        container.getChildren().addAll(componentLabel, componentText, lineLabel, lineText,  shopLabel, shopText);

        // Создаем сцену и устанавливаем ее в окно
        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }





}

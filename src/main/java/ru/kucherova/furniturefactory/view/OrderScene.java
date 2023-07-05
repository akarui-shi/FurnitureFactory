package ru.kucherova.furniturefactory.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.database.DataBase;
import ru.kucherova.furniturefactory.model.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderScene {
    public Order order;

    public OrderScene(Order order){

        this.order = order;
    }

    public void showItemDetails(DataBase dataBase, String item) throws SQLException {
        Stage itemStage = new Stage();
        itemStage.setTitle(item);

        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);

        List<String> itemData = order.getItemDataFromDatabase(dataBase, item);

        Label dataLabel = new Label("Дата: \n");
        Label nameLabel = new Label("Название магазина: \n");
        Label furnityreLabel = new Label("Компоненты заказа: \n");

        Text dataText = new Text(itemData.get(0));
        Text nameText = new Text(itemData.get(1));
        Text furnityreText = new Text(order.getFurniture(dataBase, item).toString().replaceAll(", ", System.lineSeparator()).replace("[", "").replace("]", ""));

        dataLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dataText.setFont(Font.font(14));
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nameText.setFont(Font.font(14));
        furnityreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        furnityreText.setFont(Font.font(14));

        container.getChildren().addAll(dataLabel, dataText, nameLabel, nameText, furnityreLabel, furnityreText);

        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

    public void showItemDetailsForAdmin(DataBase dataBase, String item) throws SQLException {
        Stage itemStage = new Stage();
        itemStage.setTitle(item);

        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);

        List<String> itemData = order.getItemDataFromDatabaseForAdmin(dataBase, item);

        Label dataLabel = new Label("Дата: \n");
        Label nameLabel = new Label("Название магазина: \n");
        Label furnityreLabel = new Label("Компоненты заказа: \n");

        Text dataText = new Text(itemData.get(0));
        Text nameText = new Text(itemData.get(1));
        Text furnityreText = new Text(order.getFurniture(dataBase, item).toString().replaceAll(", ", System.lineSeparator()).replace("[", "").replace("]", ""));

        dataLabel.setStyle("-fx-font-weight: bold;");
        dataText.setStyle("-fx-font-size: 14;");
        nameLabel.setStyle("-fx-font-weight: bold;");
        nameText.setStyle("-fx-font-size: 14;");
        furnityreLabel.setStyle("-fx-font-weight: bold;");
        furnityreText.setStyle("-fx-font-size: 14;");

        container.getChildren().addAll(dataLabel, dataText, nameLabel, nameText, furnityreLabel, furnityreText);

        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

}
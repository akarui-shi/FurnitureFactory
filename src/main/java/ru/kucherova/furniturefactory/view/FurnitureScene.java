package ru.kucherova.furniturefactory.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    public void showItemDetails(DataBase dataBase, String item) throws SQLException {
        Stage itemStage = new Stage();
        itemStage.setTitle(item);

        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);

        List<String> itemData = furniture.getItemDataFromDatabase(dataBase, item);

        Label componentLabel = new Label("Компоненты: ");
        Label lineLabel = new Label("Линия: ");
        Label shopLabel = new Label("Магазины: ");

        Text componentText = new Text(itemData.get(0));
        Text lineText = new Text(itemData.get(1));
        Text shopText = new Text(itemData.get(2));

        componentLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        componentText.setFont(Font.font(14));
        lineLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lineText.setFont(Font.font(14));
        shopLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        shopText.setFont(Font.font(14));

        container.getChildren().addAll(componentLabel, componentText, lineLabel, lineText,  shopLabel, shopText);

        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

}

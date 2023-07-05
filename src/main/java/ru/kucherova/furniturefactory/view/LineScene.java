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
import ru.kucherova.furniturefactory.model.Line;

import java.sql.SQLException;
import java.util.List;

public class LineScene {
    Line line;

    public LineScene(Line line){
        this.line = line;
    }

    public void showItemDetails(DataBase dataBase, String item) throws SQLException {
        Stage itemStage = new Stage();
        itemStage.setTitle(item);

        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);

        List<String> itemData = line.getItemDataFromDatabase(dataBase, item);

        Label furnitureLabel = new Label("Мебель: \n");

        Text furnitureText = new Text(itemData.toString().replaceAll(", ", System.lineSeparator()).replace("[", "").replace("]", ""));

        furnitureLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        furnitureText.setFont(Font.font(14));

        container.getChildren().addAll(furnitureLabel, furnitureText);

        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

}

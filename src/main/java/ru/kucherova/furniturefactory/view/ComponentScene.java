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
import ru.kucherova.furniturefactory.model.Component;

import java.sql.SQLException;
import java.util.List;

public class ComponentScene {
    Component component;

    public ComponentScene(Component component){
        this.component = component;
    }

    public void showItemDetails(DataBase dataBase, String item) throws SQLException {
        Stage itemStage = new Stage();
        itemStage.setTitle(item);

        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);

        List<String> itemData = component.getItemDataFromDatabase(dataBase, item);

        Label componentLabel = new Label("Входит в: \n");

        Text componentText = new Text(itemData.toString().replaceAll(", ", System.lineSeparator()).replace("[", "").replace("]", ""));

        componentLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        componentText.setFont(Font.font(14));

        container.getChildren().addAll(componentLabel, componentText);

        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

}
package ru.kucherova.furniturefactory.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
        // Создаем окно для отображения данных элемента Мебели
        Stage itemStage = new Stage();
        itemStage.setTitle(item);

        // Создаем контейнер VBox для отображения данных элемента Мебели
        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);

        // Получаем данные для отображения
        List<String> itemData = line.getItemDataFromDatabase(dataBase, item);

        // Создаем метки для отображения данных
        Label furnitureLabel = new Label("Мебель: \n");
        //Label lineLabel = new Label("Линия: ");
        //Label orderLabel = new Label("Заказы: ");
        //Label shopLabel = new Label("Магазины: ");

        // Создаем текстовые поля для отображения данных
        Text furnitureText = new Text(itemData.toString().replaceAll(", ", System.lineSeparator()).replace("[", "").replace("]", ""));
        //Text orderText = new Text(itemData.get(2));


        // Устанавливаем стили для меток и текстовых полей
        furnitureLabel.setStyle("-fx-font-weight: bold;");
        //lineLabel.setStyle("-fx-font-weight: bold;");
        //shopLabel.setStyle("-fx-font-weight: bold;");
        //componentText.setStyle("-fx-font-size: 14;");
        furnitureText.setStyle("-fx-font-size: 14;");
        //shopText.setStyle("-fx-font-size: 14;");

        // Добавляем метки и текстовые поля в контейнер
        container.getChildren().addAll(furnitureLabel, furnitureText);

        // Создаем сцену и устанавливаем ее в окно
        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

}

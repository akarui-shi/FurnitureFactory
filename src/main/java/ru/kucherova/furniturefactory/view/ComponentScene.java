package ru.kucherova.furniturefactory.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.database.DataBase;
import ru.kucherova.furniturefactory.model.Component;
import ru.kucherova.furniturefactory.model.Line;

import java.sql.SQLException;
import java.util.List;

public class ComponentScene {
    Component component;

    public ComponentScene(Component component){
        this.component = component;
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
        List<String> itemData = component.getItemDataFromDatabase(dataBase, item);

        // Создаем метки для отображения данных
        Label componentLabel = new Label("Входит в: \n");

        // Создаем текстовые поля для отображения данных
        Text componentText = new Text(itemData.toString().replaceAll(", ", System.lineSeparator()).replace("[", "").replace("]", ""));

        // Устанавливаем стили для меток и текстовых полей
        componentLabel.setStyle("-fx-font-weight: bold;");
        componentText.setStyle("-fx-font-size: 14;");

        // Добавляем метки и текстовые поля в контейнер
        container.getChildren().addAll(componentLabel, componentText);

        // Создаем сцену и устанавливаем ее в окно
        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

}
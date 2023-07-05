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
import ru.kucherova.furniturefactory.model.Store;

import java.sql.SQLException;
import java.util.List;

public class StoreScene {
    Store store;

    public StoreScene(Store store){
        this.store = store;
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
        List<String> itemData = store.getItemDataFromDatabase(dataBase, item);

        // Создаем метки для отображения данных
        Label nameLabel = new Label("Навзание: \n");
        Label faxLabel = new Label("Номер: ");
        Label furnitureLabel = new Label("Мебель в наличии: \n");

        // Создаем текстовые поля для отображения данных
        Text nameText = new Text(itemData.get(0));
        Text faxText = new Text(itemData.get(1));
        //Text  furnitureText = new Text(store.getFromFurniture());

        // Устанавливаем стили для меток и текстовых полей
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nameText.setFont(Font.font(14));
        faxLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        faxText.setFont(Font.font(14));


        // Добавляем метки и текстовые поля в контейнер
        container.getChildren().addAll(nameLabel, nameText, faxLabel, faxText);

        // Создаем сцену и устанавливаем ее в окно
        Scene itemScene = new Scene(container, 400, 300);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

}
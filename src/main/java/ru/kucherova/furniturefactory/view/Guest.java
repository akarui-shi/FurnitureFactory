package ru.kucherova.furniturefactory.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import ru.kucherova.furniturefactory.database.DataBase;
import ru.kucherova.furniturefactory.model.Furniture;

import java.sql.*;

public class Guest extends Application  {
    Furniture furniture;
    private ListView<String> furnitureList;
    private ListView<String> componentList;
    private ListView<String> orderList;
    private ListView<String> shopList;


    @Override
    public void start(Stage primaryStage) throws Exception {

        DataBase dataBase = new DataBase();

        // Создаем списки элементов
        furnitureList = new ListView<>();
        furniture = new Furniture(dataBase);
        ObservableList<String> furnitureItems = FXCollections.observableArrayList(
                furniture.getAll());
        furnitureList.setItems(furnitureItems);

        componentList = new ListView<>();
        ObservableList<String> componentItems = FXCollections.observableArrayList(
                "Дерево", "Металл", "Пластик", "Ткань");
        componentList.setItems(componentItems);

        orderList = new ListView<>();
        ObservableList<String> orderItems = FXCollections.observableArrayList(
                "Заказ №1", "Заказ №2", "Заказ №3", "Заказ №4");
        orderList.setItems(orderItems);

        shopList = new ListView<>();
        ObservableList<String> shopItems = FXCollections.observableArrayList(
                "IKEA", "Ashley Furniture", "Target", "Home Depot");
        shopList.setItems(shopItems);

        // Привязываем действия к списку Мебели
        furnitureList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = furnitureList.getSelectionModel().getSelectedItem();
                try {
                    showItemDetails(selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Создаем графический интерфейс
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        // Создаем TabPane для переключения между списками
        TabPane tabPane = new TabPane();

        Tab furnitureTab = new Tab("Мебель", furnitureList);
        //furnitureTab.setClosable(false);
        Tab componentTab = new Tab("Компоненты", componentList);
        //componentTab.setClosable(false);
        Tab orderTab = new Tab("Заказы", orderList);
        //orderTab.setClosable(false);
        Tab shopTab = new Tab("Магазины", shopList);
        //shopTab.setClosable(false);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // запретить закрытие вкладок

        tabPane.getTabs().addAll(furnitureTab, componentTab, orderTab, shopTab);
        furnitureTab.setClosable(false);

        root.getChildren().addAll(tabPane);

        Scene scene = new Scene(root, 800, 600);


        scene.getStylesheets().add(String.valueOf(Guest.class.getResource("furniture.css")));

        primaryStage.setScene(scene);

        primaryStage.setTitle("Furniture App");
        //primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    private void showItemDetails(String item) throws SQLException {
        // Создаем окно для отображения данных элемента Мебели
        Stage itemStage = new Stage();
        itemStage.setTitle(item);

        // Создаем таблицу для отображения данных элемента Мебели
        TableView<String> table = new TableView<>();

        TableColumn<String, String> componentColumn = new TableColumn<>("Компонент");
        TableColumn<String, String> orderColumn = new TableColumn<>("Заказ");
        TableColumn<String, String> shopColumn = new TableColumn<>("Магазин");

        table.getColumns().addAll(componentColumn, orderColumn, shopColumn);

// Получаем данные для отображения в таблице
        ObservableList<String> itemData = getItemData(item);

        table.setItems(itemData);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        vbox.getChildren().addAll(table);

        Scene itemScene = new Scene(vbox, 400, 400);
        itemStage.setScene(itemScene);
        itemStage.show();
    }

    private ObservableList<String> getItemData(String item) throws SQLException {
        // Получаем данные для таблицы из базы данных или другого источника данных
        // В данном случае мы просто генерируем случайные данные
        String url = "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2272_factory";
        String username = "std_2272_factory";
        String password = "21022004";

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Component.type, Order.date , Store.address FROM Component JOIN FurnitureComponent ON Component.id = FurnitureComponent.component_id JOIN Furniture ON FurnitureComponent.furniture_id = Furniture.id JOIN OrderFurniture ON Furniture.id = OrderFurniture.furniture_id JOIN Order ON OrderFurniture.order_id = Order.id JOIN Store ON Order.store_id = Store.id;");

        ObservableList<String> data = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String component = resultSet.getString("component");
            String order = resultSet.getString("order");
            String shop = resultSet.getString("shop");
            data.add(component + "\t" + order + "\t" + shop);
        }
        resultSet.close();
        statement.close();
        connection.close();



//        ObservableList<String> data = FXCollections.observableArrayList();
//        for (int i = 0; i < 5; i++) {
//            String component = getRandomItem(componentList);
//            String order = getRandomItem(orderList);
//            String shop = getRandomItem(shopList);
//            data.add(component + "\t" + order + "\t" + shop);
//        }
        return data;
    }

    private String getRandomItem(ListView<String> list) {
        // Возвращает случайный элемент из ListView
        int index = (int) (Math.random() * list.getItems().size());
        return list.getItems().get(index);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
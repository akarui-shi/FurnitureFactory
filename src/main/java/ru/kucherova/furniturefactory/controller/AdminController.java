package ru.kucherova.furniturefactory.controller;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.model.*;
import ru.kucherova.furniturefactory.view.*;

import java.sql.*;
import java.util.Optional;

public class AdminController  {
    Admin admin;
    AdminScene adminScene;
    FurnitureScene furnitureScene;
    ComponentScene componentScene;
    LineScene lineScene;
    StoreScene storeScene;
    OrderScene orderScene;

    public AdminController(Admin admin, AdminScene adminScene){
        this.admin = admin;
        this.adminScene =  adminScene;

        // Привязываем действия к списку Мебели
        admin.furnitureList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = admin.furnitureList.getSelectionModel().getSelectedItem();
                try {
                    Furniture furniture = new Furniture(admin.getDataBase());
                    furnitureScene = new FurnitureScene(furniture);
                    furnitureScene.showItemDetails(admin.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        admin.lineList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = admin.lineList.getSelectionModel().getSelectedItem();
                try {
                    Line line = new Line(admin.getDataBase());
                    lineScene = new LineScene(line);
                    lineScene.showItemDetails(admin.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        admin.componentList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = admin.componentList.getSelectionModel().getSelectedItem();
                try {
                    Component component = new Component(admin.getDataBase());
                    componentScene = new ComponentScene(component);
                    componentScene.showItemDetails(admin.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        admin.shopList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = admin.shopList.getSelectionModel().getSelectedItem();
                try {
                    Store store = new Store(admin.getDataBase());
                    storeScene = new StoreScene(store);
                    storeScene.showItemDetails(admin.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        admin.orderList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = admin.orderList.getSelectionModel().getSelectedItem();
                try {
                    Order order = new Order(admin.getDataBase(), admin);
                    orderScene = new OrderScene(order);
                    orderScene.showItemDetailsForAdmin(admin.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        adminScene.deleteFurniture.setOnAction(event -> {
            String selectedItem = admin.furnitureList.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление мебели");
            alert.setHeaderText("Вы уверены, что хотите удалить эту мебель: " + selectedItem + "?");
            ButtonType deleteButton = new ButtonType("Удалить", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(deleteButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == deleteButton) {
                //Furniture furnitureToDelete = new Furniture(dataBase, selectedItem);
//                try {
//                    furnitureToDelete.delete();
//                    refreshFurnitureList();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                System.out.println("ахуеть");
            }
        });

        adminScene.deleteLine.setOnAction(event -> {
            String selectedItem = admin.lineList.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление мебели");
            alert.setHeaderText("Вы уверены, что хотите удалить эту линию: " + selectedItem + "?");
            ButtonType deleteButton = new ButtonType("Удалить", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(deleteButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == deleteButton) {
                //Furniture furnitureToDelete = new Furniture(dataBase, selectedItem);
//                try {
//                    furnitureToDelete.delete();
//                    refreshFurnitureList();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                System.out.println("ахуеть");
            }
        });

        adminScene.deleteComponent.setOnAction(event -> {
            String selectedItem = admin.componentList.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление мебели");
            alert.setHeaderText("Вы уверены, что хотите удалить этот компонент: " + selectedItem + "?");
            ButtonType deleteButton = new ButtonType("Удалить", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(deleteButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == deleteButton) {
                //Furniture furnitureToDelete = new Furniture(dataBase, selectedItem);
//                try {
//                    furnitureToDelete.delete();
//                    refreshFurnitureList();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                System.out.println("ахуеть");
            }
        });

        adminScene.deleteStore.setOnAction(event -> {
            String selectedItem = admin.shopList.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление мебели");
            alert.setHeaderText("Вы уверены, что хотите удалить этот магазин: " + selectedItem + "?");
            ButtonType deleteButton = new ButtonType("Удалить", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(deleteButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == deleteButton) {
                //Furniture furnitureToDelete = new Furniture(dataBase, selectedItem);
//                try {
//                    furnitureToDelete.delete();
//                    refreshFurnitureList();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                System.out.println("ахуеть");
            }
        });

        adminScene.addOrderButton.setOnAction(event -> showAddOrderDialog());
    }

    private void showAddOrderDialog() {
        try {

            // Создание диалогового окна для создания нового заказа
            Stage dialog = new Stage();

            BorderPane root = new BorderPane();
            dialog.setScene(new Scene(root, 500, 400));

            Label title = new Label("Добавление нового заказа");
            title.setAlignment(Pos.CENTER);
            BorderPane.setAlignment(title, Pos.CENTER);
            BorderPane.setMargin(title, new Insets(20));
            root.setTop(title);

            // Получение списка мебели для выбора
            ListView<CheckBox> furnitureCheckBoxList = new ListView<>();
            ObservableList<CheckBox> checkBoxList = furnitureCheckBoxList.getItems();

            Statement stmt = admin.getDataBase().connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, type, article FROM Furniture");

            while (rs.next()) {
                int id = rs.getInt(1);
                String type = rs.getString(2);
                String article = rs.getString(3);
                CheckBox checkBox = new CheckBox(String.format("%s [Артикул: %s]", type, article));
                checkBox.setId(Integer.toString(id));
                checkBoxList.add(checkBox);
            }

            rs.close();
            stmt.close();

            VBox checkboxBox = new VBox(furnitureCheckBoxList);
            checkboxBox.setAlignment(Pos.TOP_CENTER);
            checkboxBox.setPadding(new Insets(20));
            checkboxBox.setSpacing(10);
            root.setCenter(checkboxBox);

            // Создание кнопки для добавления заказа в базу данных
            Button addButton = new Button("Добавить заказ");
            addButton.setOnAction(event -> {
                try {
                    // Получение выбранных пользователем предметов мебели
                    ObservableList<CheckBox> checkedItems = furnitureCheckBoxList.getItems();
                    StringBuilder furnitureIds = new StringBuilder();

                    for (CheckBox checkBox : checkedItems) {
                        if (checkBox.isSelected()) {
                            if (furnitureIds.length() > 0) {
                                furnitureIds.append(",");
                            }
                            furnitureIds.append(checkBox.getId());
                        }
                    }

                    PreparedStatement stmt3 = admin.getDataBase().connection.prepareStatement("SELECT MAX(id) AS id FROM `Order`");
                    ResultSet rs2 = stmt3.executeQuery();
                    int orderId = 0;
                    while (rs2.next()) {
                        orderId = rs2.getInt("id");
                    }

                    rs2.close();
                    stmt3.close();

                    orderId +=1;
                    System.out.println(orderId);
                    // Создание нового заказа
                    PreparedStatement stmt2 = admin.getDataBase().connection.prepareStatement("INSERT INTO `Order` (id , date, store_id, name, user_id) VALUES (?, ? , ?, CONCAT('TP1-', FLOOR(RAND()*8999+1000)) , ?)");
                    stmt2.setInt(1, orderId);
                    stmt2.setDate(2, new Date(System.currentTimeMillis()));
                    stmt2.setInt(3, 1);
                    stmt2.setInt(4, 1);

                    stmt2.executeUpdate();
                    stmt2.close();

                    // Добавление мебели в заказ
                    String[] furnitureIdList = furnitureIds.toString().split(",");
                    for (String furnitureId : furnitureIdList) {
                        PreparedStatement stmt4 = admin.getDataBase().connection.prepareStatement("INSERT INTO OrderFurniture (order_id, furniture_id, quantity) VALUES (?, ?, ?)");
                        stmt4.setInt(1, orderId);
                        stmt4.setInt(2, Integer.parseInt(furnitureId));
                        stmt4.setInt(3, 1);
                        stmt4.executeUpdate();
                        stmt4.close();
                    }

                    // Обновление списка заказов
                    adminScene.admin.refreshOrderList();

                    // Закрытие диалогового окна
                    dialog.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            root.setBottom(addButton);
            BorderPane.setAlignment(addButton, Pos.CENTER);
            BorderPane.setMargin(addButton, new Insets(20));

            // Отображение диалогового окна
            dialog.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

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

public class ClientController  {
    Client client;
    ClientScene clientScene;
    FurnitureScene furnitureScene;
    ComponentScene componentScene;
    LineScene lineScene;
    StoreScene storeScene;
    OrderScene orderScene;

    public ClientController(Client client, ClientScene clientScene){
        this.client = client;
        this.clientScene = clientScene;

        // Привязываем действия к списку Мебели
        client.furnitureList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.furnitureList.getSelectionModel().getSelectedItem();
                try {
                    Furniture furniture = new Furniture(client.getDataBase());
                    furnitureScene = new FurnitureScene(furniture);
                    furnitureScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        client.lineList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.lineList.getSelectionModel().getSelectedItem();
                try {
                    Line line = new Line(client.getDataBase());
                    lineScene = new LineScene(line);
                    lineScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        client.componentList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.componentList.getSelectionModel().getSelectedItem();
                try {
                    Component component = new Component(client.getDataBase());
                    componentScene = new ComponentScene(component);
                    componentScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        client.shopList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.shopList.getSelectionModel().getSelectedItem();
                try {
                    Store store = new Store(client.getDataBase());
                    storeScene = new StoreScene(store);
                    storeScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        client.orgerList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = client.orgerList.getSelectionModel().getSelectedItem();
                try {
                    Order order = new Order(client.getDataBase(), client);
                    orderScene = new OrderScene(order);
                    orderScene.showItemDetails(client.getDataBase(), selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        clientScene.addOrderButton.setOnAction(event -> showAddOrderDialog());
        clientScene.profileButton.setOnAction(e -> showProfile());
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

            Statement stmt = client.getDataBase().connection.createStatement();
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

                    PreparedStatement stmt3 = client.getDataBase().connection.prepareStatement("SELECT MAX(id) AS id FROM `Order`");
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
                    PreparedStatement stmt2 = client.getDataBase().connection.prepareStatement("INSERT INTO `Order` (id , date, store_id, name, user_id) VALUES (?, ? , ?, CONCAT('TP1-', FLOOR(RAND()*8999+1000)) , ?)");
                    stmt2.setInt(1, orderId);
                    stmt2.setDate(2, new Date(System.currentTimeMillis()));
                    stmt2.setInt(3, 1);
                    stmt2.setInt(4, 1);

                    stmt2.executeUpdate();
                    stmt2.close();

                    // Добавление мебели в заказ
                    String[] furnitureIdList = furnitureIds.toString().split(",");
                    for (String furnitureId : furnitureIdList) {
                        PreparedStatement stmt4 = client.getDataBase().connection.prepareStatement("INSERT INTO OrderFurniture (order_id, furniture_id, quantity) VALUES (?, ?, ?)");
                        stmt4.setInt(1, orderId);
                        stmt4.setInt(2, Integer.parseInt(furnitureId));
                        stmt4.setInt(3, 1);
                        stmt4.executeUpdate();
                        stmt4.close();
                    }

                    // Обновление списка заказов
                    clientScene.client.refreshOrderList();

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

    private void showProfile() {
        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Профиль пользователя");
        dialog.setHeaderText("Данные пользователя");

        ButtonType changeButton = new ButtonType("Изменить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(changeButton, ButtonType.CANCEL);

        // Создаем текстовые поля для отображения данных пользователя
        TextField loginField = new TextField(client.login);
        loginField.setEditable(false);
        PasswordField passwordField = new PasswordField();
        passwordField.setText(client.password);
        passwordField.setEditable(false);

        // Создаем кнопку "Изменить"
        Button changePasswordButton = new Button("Изменить пароль");
        changePasswordButton.setOnAction(e -> {
            passwordField.setEditable(true);
            passwordField.requestFocus();
        });

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Логин:"),
                loginField,
                new Label("Пароль:"),
                passwordField,
                changePasswordButton
        );

        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == changeButton) {
                //return new User(currentUser.getId(), currentUser.getLogin(), passwordField.getText());
            }
            return null;
        });

        // При закрытии диалога сохраняем изменения
        dialog.setOnCloseRequest(e -> {
            Client updatedUser = dialog.getResult();
            System.out.println("ВАУ");
//            if (updatedUser != null) {
//                try {
//                    currentUser.setPassword(updatedUser.getPassword());
//                    // Сохраняем изменения в базе данных
//                    currentUser.update();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
        });

        dialog.showAndWait();
    }



}
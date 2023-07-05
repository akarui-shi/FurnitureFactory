package ru.kucherova.furniturefactory.controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.view.AuthScene;
import ru.kucherova.furniturefactory.model.Admin;
import ru.kucherova.furniturefactory.model.Auth;
import ru.kucherova.furniturefactory.model.Client;
import ru.kucherova.furniturefactory.model.Guest;
import ru.kucherova.furniturefactory.view.AdminScene;
import ru.kucherova.furniturefactory.view.ClientScene;
import ru.kucherova.furniturefactory.view.GuestScene;

import java.sql.SQLException;

public class AuthController {
    Auth auth;
    AuthScene authScene;
    public AuthController(Auth auth, AuthScene authScene) {
        this.auth = auth;
        this.authScene = authScene;
        authScene.guestButton.setOnAction(event -> {
            Guest guest = null;
            try {
                guest = new Guest();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            GuestScene guestScene = new GuestScene(guest);
            try {
                guestScene.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            GuestController guestController = new GuestController(guest, guestScene);

            authScene.mainWindow.close();
        });

        authScene.loginButton.setOnAction(event -> {
            String login = authScene.loginField.getText();
            String password = authScene.passwordField.getText();
            try {
                if (auth.getRole(login, password).equals("Клиент")) {
                    Client client = null;
                    try {
                        client = new Client(login, password);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    ClientScene clientScene = new ClientScene(client);
                    try {
                        clientScene.start(new Stage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    ClientController clientController = new ClientController(client, clientScene);

                    authScene.mainWindow.close();
                } else if (auth.getRole(login, password).equals("Aдминистратор")){
                    Admin admin = new Admin();
                    AdminScene adminScene = new AdminScene(admin);
                    adminScene.start(new Stage());
                    AdminController adminController = new AdminController(admin, adminScene);

                    authScene.mainWindow.close();
                }else System.out.println("Ошибка добавлния");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        authScene.registrationButton.setOnAction(event -> {
            String username = authScene.newLoginField.getText();
            String password = authScene.newPasswordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка регистрации");
                alert.setHeaderText(null);
                alert.setContentText("Пожалуйста, введите логин и пароль");
                alert.showAndWait();
                return;
            }

            try {
                Client newClient = new Client(username, password);
                newClient.save();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Регистрация");
                alert.setHeaderText(null);
                alert.setContentText("Пользователь успешно зарегистрирован");
                alert.showAndWait();

                authScene.newLoginField.setText("");
                authScene.newPasswordField.setText("");

                ClientScene clientScene = new ClientScene(newClient);
                clientScene.start(new Stage());
                ClientController clientController = new ClientController(newClient, clientScene);

                authScene.mainWindow.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Вывод сообщения об ошибке
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка регистрации");
                alert.setHeaderText(null);
                alert.setContentText("Произошла ошибка при регистрации пользователя");
                alert.showAndWait();
            }
        });
    }

}

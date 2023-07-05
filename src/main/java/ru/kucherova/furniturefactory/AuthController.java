package ru.kucherova.furniturefactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.controller.ClientController;
import ru.kucherova.furniturefactory.controller.GuestController;
import ru.kucherova.furniturefactory.model.Client;
import ru.kucherova.furniturefactory.model.Guest;
import ru.kucherova.furniturefactory.view.ClientScene;
import ru.kucherova.furniturefactory.view.GuestScene;

import java.sql.SQLException;

public class AuthController {
    AuthController(Auth auth){
        auth.guestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {
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
                // Закрываем окно авторизации
                auth.mainWindow.close();
            }
        });

        auth.loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                String login = auth.loginField.getText();
                String password = auth.passwordField.getText();
                System.out.println(login);
                System.out.println(password);

                Client client = null;
                try {
                    client = new Client(login, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                ClientScene clientScene = new ClientScene(client);
                try {
                    clientScene.start(new Stage()); // блять
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                ClientController clientController = new ClientController(client, clientScene);

                auth.mainWindow.close();
            }

        });

    }
}

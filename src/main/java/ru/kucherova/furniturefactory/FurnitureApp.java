package ru.kucherova.furniturefactory;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.controller.AuthController;
import ru.kucherova.furniturefactory.model.Auth;
import ru.kucherova.furniturefactory.view.AuthScene;


public class FurnitureApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Auth auth = new Auth();
        AuthScene authScene = new AuthScene(auth);
        authScene.start(primaryStage);
        AuthController authController = new AuthController(auth, authScene);
    }
}

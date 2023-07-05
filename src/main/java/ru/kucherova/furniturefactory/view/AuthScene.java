package ru.kucherova.furniturefactory.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ru.kucherova.furniturefactory.model.Auth;

public class AuthScene extends Application {
    public Stage mainWindow;
    public Button guestButton;
    public Button loginButton;
    public Button registrationButton;
    public TextField loginField;
    public PasswordField passwordField;

    public TextField newLoginField;
    public PasswordField newPasswordField;
    Auth auth;

    public AuthScene(Auth auth){
        this.auth = auth;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Authorization");
        mainWindow.setResizable(false);

        // Создаем окно авторизации на основе сетки GridPane
        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setHgap(10);
        loginLayout.setVgap(10);
        loginLayout.setPadding(new Insets(25, 25, 25, 25));
        loginLayout.setStyle("-fx-background-color: #F5F5F5;");


        // Добавляем элементы в окно авторизации
        Label loginLabel = new Label("Login:");
        loginLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        loginLabel.setTextFill(Color.web("#2F4F4F"));
        loginLayout.add(loginLabel, 0, 1);

        loginField = new TextField();
        loginField.setPromptText("Enter your login");
        loginLayout.add(loginField, 1, 1);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        passwordLabel.setTextFill(Color.web("#2F4F4F"));
        loginLayout.add(passwordLabel, 0, 2);

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        loginLayout.add(passwordField, 1, 2);

        // Создаем кнопку "Войти как гость" и добавляем в окно авторизации
        guestButton = new Button("Войти как гость");
        guestButton.setStyle("-fx-background-color: #F5F5F5; -fx-text-fill: #2F4F4F;");
        guestButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setMargin(guestButton, new Insets(10, 0, 0, 0));
        loginLayout.add(guestButton, 1, 4);

        loginButton = new Button("Log in");
        loginButton.setStyle("-fx-background-color: #2F4F4F; -fx-text-fill: #FFFFFF;");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(loginButton);
        loginLayout.add(hbBtn, 1, 5);

        // Создаем окно регистрации на основе VBox
        VBox registrationLayout = new VBox(10);
        registrationLayout.setAlignment(Pos.CENTER);
        registrationLayout.setPadding(new Insets(25, 25, 25, 25));
        registrationLayout.setStyle("-fx-background-color: #F5F5F5;");

        // Добавляем элементы в окно регистрации
        Label registrationLabel = new Label("Registration:");
        registrationLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        registrationLabel.setTextFill(Color.web("#2F4F4F"));
        Label newLoginLabel = new Label("New login:");
        newLoginField = new TextField();
        newLoginField.setPromptText("Enter new login");
        Label newPasswordLabel = new Label("New password:");
        newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Enter new password");
        Label confirmPasswordLabel = new Label("Confirm password:");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm your password");

        registrationButton = new Button("Register");
        registrationButton.setStyle("-fx-background-color: #2F4F4F; -fx-text-fill: #FFFFFF;");
        registrationLayout.getChildren().addAll(registrationLabel, newLoginLabel, newLoginField, newPasswordLabel, newPasswordField, confirmPasswordLabel, confirmPasswordField, registrationButton);

        // Создаем главное окно и добавляем на него вкладки для авторизации и регистрации
        TabPane mainLayout = new TabPane();
        mainLayout.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab loginTab = new Tab("Login");
        loginTab.setContent(loginLayout);

        mainLayout.getTabs().add(loginTab);

        Tab registrationTab = new Tab("Registration");
        registrationTab.setContent(registrationLayout);

        mainLayout.getTabs().add(registrationTab);

        Scene mainScene = new Scene(mainLayout, 400, 400);
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

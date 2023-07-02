module ru.kucherova.furniturefactory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires org.controlsfx.controls;
    //requires org.kordamp.bootstrapfx.core;


    opens ru.kucherova.furniturefactory to javafx.fxml;
    exports ru.kucherova.furniturefactory.model;
    exports ru.kucherova.furniturefactory.controller;
    exports ru.kucherova.furniturefactory.view;
    exports ru.kucherova.furniturefactory.database;
    exports ru.kucherova.furniturefactory;
}
package ru.kucherova.furniturefactory.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    public Connection connection;

    public DataBase(){
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2272_factory",
                    "std_2272_factory", "21022004");
            //refreshData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
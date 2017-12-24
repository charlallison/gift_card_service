package com.calvin.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Dao {

    private Connection connection;

    Dao() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gift_card_db", "root", "root");
    }

    Connection getConnection() {
        return connection;
    }


}

package main.java.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection createConnection() throws SQLException {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:sqlite:db:sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexao;
    }

}

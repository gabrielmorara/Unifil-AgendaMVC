package main.java.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static void createtables(Connection conexao) {
        try {
            conexao = DriverManager.getConnection("jdbc:sqlite:db:sqlite");
            Statement stmt = conexao.createStatement();
            String table_groups = "CREATE TABLE IF NOT EXISTS groups ( " +
                    "group_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT )";
            String table_contacts = "CREATE TABLE IF NOT EXISTS contacts  ( " +
                    "contact_id INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    "first_name TEXT, " +
                    "last_name TEXT," +
                    "email TEXT)";
            String table_phones = "CREATE TABLE IF NOT EXISTS phones ( " +
                    "phone_id INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    "phone TEXT)";
            String contact_phone = "CREATE TABLE IF NOT EXISTS contact_phone  ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "contact_id INTEGER," +
                    "phone_id INTEGER, " +
                    "FOREIGN KEY (phone_id) REFERENCES phones (phone_id)," +
                    "FOREIGN KEY (contact_id) REFERENCES contacts (contact_id))";
            String contact_groups = "CREATE TABLE IF NOT EXISTS contact_groups  ( " +
                    "id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                    "group_id INTEGER," +
                    "contact_id INTEGER, " +
                    "FOREIGN KEY (group_id) REFERENCES groups (group_id)," +
                    "FOREIGN KEY (contact_id) REFERENCES contacts (contact_id))";
            stmt.execute(table_groups);
            stmt.execute(table_contacts);
            stmt.execute(table_phones);
            stmt.execute(contact_groups);
            stmt.execute(contact_phone);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

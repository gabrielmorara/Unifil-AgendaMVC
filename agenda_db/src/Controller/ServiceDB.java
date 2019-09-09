package Controller;

import Models.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDB {

    public static void insertTables(Connection conexao, String query) {
        try {
            Statement stmt = conexao.createStatement();
            stmt.execute(query);
        } catch (
                SQLException e) {
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

    public static String insertContact(String firstName, String lastName, String email) {
        return "insert into contacts(first_name, last_name ,email) values ('" + firstName + "',  '" + lastName + "', '" + email + "' )";
    }

    public static String insertPhone(String phone) {
        return "insert into phones(phone) values ('" + phone + ")";
    }

    public static String insertGroup(String name) {
        return "insert into groups(name) values ('" + name + ")";
    }

    public static String getAllcontacts() {
        return "select * from contacts";
    }

    public static String getContactbyId(int id) {
        return "select * from contacts where contact_id " + " = " + id;
    }

    public static String getContactByName(String name) {
        return "select * from contacts where name " + " = " + name;
    }

    public static String selectDB(String query) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db:sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Contact> list = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(new Contact(resultSet.getInt("contact_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email")));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list.toString());
        return "result";
    }
}

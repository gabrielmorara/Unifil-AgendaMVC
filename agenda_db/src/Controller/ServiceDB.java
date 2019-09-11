package Controller;

import Models.Contact;
import Models.Groups;
import Models.Phones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDB {

    public static int insertTables(Connection conexao, String query) {
        int id = 0;
        try {
            PreparedStatement ps = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            System.out.println("Id gerado pelo insert foi " + id);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static String insertContact(String firstName, String lastName, String email) {
        return "insert into contacts(first_name, last_name ,email) values ('" + firstName + "',  '" + lastName + "', '" + email + "' )";
    }

    public static String insertPhone(String phone) {
        return "insert into phones(phone) values ('" + phone + "')";
    }

    public static String insertContactPhone(int contactId, int phoneId) {
        return "insert into contact_phone(contact_id, phone_id) values ('" + contactId + "', '" + phoneId + "')";
    }

    public static String insertGroup(String name) {
        return "insert into groups(name) values ('" + name + ")";
    }

    public static String insertGroupsContact(int group_id, int contactId) {
        return "insert into contact_groups(group_id , contact_id) values ('" + group_id + "',  '" + contactId + "')";
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

    public static String getAllGroups() {
        return "select * from groups";
    }

    public static String getPhoneById(int id) {
        return "select * from phones where phone_id " + " = " + id;
    }

    public static List<Contact> selectDBContact(String query, Connection conn) {
        List<Contact> list = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(new Contact(resultSet.getInt("contact_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Phones> selectDBPhones(String query, Connection conn) {
        List<Phones> list = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(new Phones(resultSet.getInt("phone_id"),
                        resultSet.getString("phone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Groups> selectDBCGroups(String query, Connection conn) {
        List<Groups> list = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(new Groups(resultSet.getInt("group_id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // adicionar extensao do sqlite
    // e database na versão professional do intelij
    public static String separador = ",";

    public static void createtables(Connection conexao) {
        try {
            Statement stmt = conexao.createStatement();
            String table_groups = "CREATE TABLE groups ( " +
                    "group_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT )";
            String table_contacts = "CREATE TABLE contacts ( " +
                    "contact_id INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    "first_name TEXT, " +
                    "last_name TEXT," +
                    "email TEXT)";
            String table_phones = "CREATE TABLE phones ( " +
                    "phone_id INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    "phone TEXT)";
            String contact_phone = "CREATE TABLE contact_phone ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "contact_id INTEGER," +
                    "phone_id INTEGER, " +
                    "FOREIGN KEY (phone_id) REFERENCES phones (phone_id)," +
                    "FOREIGN KEY (contact_id) REFERENCES contacts (contact_id))";
            String contact_groups = "CREATE TABLE contact_groups ( " +
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

    private static Connection createConnection() throws SQLException {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:sqlite:db:sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexao;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("===========");
//        createtables(createConnection());
//        insertTables(createConnection(), insertContact("teste", "teste", "teste"));
//        System.out.println(selectDB(getAllcontacts()));
        System.out.println(selectDB(getAllcontacts()));
    }
}


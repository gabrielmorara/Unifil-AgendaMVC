import java.sql.*;

public class Main {
    // adicionar extensao do sqlite
    // e database na versão professional do intelij
    public static void connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db:sqlite");
            Statement stmt = conn.createStatement();
            String table_groups = "CREATE TABLE groups ( " +
                    "group_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT )";
            String table_contacts = "CREATE TABLE contacts ( " +
                    "contact_id INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    "first_name TEXT, " +
                    "last_name TEXT," +
                    "email TEXT UNIQUE)";
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String selectDB(String query) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db:sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = "";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                result += resultSet;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Es, World!");
        connect();
    }
}


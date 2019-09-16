package Controller;

import Models.Contact;
import Models.Phones;

import java.sql.Connection;
import java.util.List;

import static Controller.ServiceDB.*;

public class PhoneController {

    public static int inserirTelefone(Connection connection, String telefoneAdd){
        return ServiceDB.insertTables(connection, ServiceDB.insertPhone(telefoneAdd));
    }

    public static List<Phones> getAllTelefone(Connection connection) {
        return selectDBPhones(getAllTelefones(), connection);
    }
}

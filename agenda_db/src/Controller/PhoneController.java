package Controller;

import Models.Contact;
import Models.Phones;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static Controller.ServiceDB.*;

public class PhoneController {

    private static Scanner scanner = new Scanner(System.in);

    public static int inserirTelefone(Connection connection){
        System.out.println("Digite o telefone :");
        String telefoneAdd = scanner.next();
        return ServiceDB.insertTables(connection, ServiceDB.insertPhone(telefoneAdd));
    }

    public static List<Phones> getAllTelefone(Connection connection) {
        return selectDBPhones(getAllTelefones(), connection);
    }

    public static void removerTelefone(Connection connection){

    }

}

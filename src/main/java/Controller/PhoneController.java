package main.java.Controller;


import main.java.Models.Phones;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static main.java.Controller.ServiceDB.getAllTelefones;
import static main.java.Controller.ServiceDB.selectDBPhones;


public class PhoneController {

    private static Scanner scanner = new Scanner(System.in);

    public static int inserirTelefone(Connection connection) {
        System.out.println("Digite o telefone :");
        String telefoneAdd = scanner.next();
        return ServiceDB.insertTables(connection, ServiceDB.insertPhone(telefoneAdd));
    }

    public static List<Phones> getAllTelefone(Connection connection) {
        return selectDBPhones(getAllTelefones(), connection);
    }

    public static void removerTelefone(Connection connection) {
        System.out.println(selectDBPhones(getAllTelefones(), connection));
        System.out.println("Digite o id do telefone para remover : ");
        int id_contato = scanner.nextInt();
        ServiceDB.delete("delete from contact_phone where phone_id = " + id_contato + ";",
                connection);
        ServiceDB.delete("delete from phones where phone_id = " + id_contato + ";",
                connection);
    }

}

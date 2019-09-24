package Controller;

import Models.Contact;
import Models.Groups;
import Models.Phones;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static Controller.GroupController.getAllgrop;
import static Controller.PhoneController.getAllTelefone;
import static Controller.ServiceDB.*;

public class ContactController {
    private static Scanner scanner = new Scanner(System.in);

    public static int inserirContato(Connection connection) {
        System.out.println("Digite o nome :");
        String firstNome = scanner.next();
        System.out.println("Digite o sobrenome :");
        String lastName = scanner.next();
        System.out.println("Digite o email :");
        String email = scanner.next();
        return ServiceDB.insertTables(connection, insertContact(firstNome, lastName, email));
    }

    public static void vincularContatoTelefone(Connection connection) {
        getAllContacts(connection);
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println("Digite o id do telefone : ");
        System.out.println(getAllTelefone(connection));
        int id_phone = scanner.nextInt();
        ServiceDB.insertTables(connection, ServiceDB.insertContactPhone(id_contato, id_phone));
    }

    public static void vincularContatoTelefone(Connection connection, int id_contact, int id_telefone) {
        ServiceDB.insertTables(connection, ServiceDB.insertContactPhone(id_contact, id_telefone));
    }

    public static void vincularContatoGrupo(Connection connection, int id_group, int id_contact) {
        ServiceDB.insertTables(connection, ServiceDB.insertContactPhone(id_contact, id_group));
    }

    public static void vincularContatoGrupo(Connection connection) {
        getAllContacts(connection);
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println("Digite o id do grupo : ");
        System.out.println(getAllgrop(connection));
        int id_grupo = scanner.nextInt();
        ServiceDB.insertTables(connection, ServiceDB.insertGroupsContact(id_grupo, id_contato));
    }

    public static void desvincularContatoTelefone(Connection connection) {
        getAllContacts(connection);
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println("Digite o id do telefone : ");
        System.out.println(ServiceDB.selectDBPhones(getTelefoneByContactId(String.valueOf(id_contato)), connection));
        int id_telefone = scanner.nextInt();
        ServiceDB.delete("delete from contact_phone where contact_id = " + id_contato + " and " + " phone_id = " + id_telefone + ";",
                connection);
    }

    public static void desvincularContatoGrupo(Connection connection) {
        getAllContacts(connection);
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println("Digite o id do Grupo : ");
        System.out.println(ServiceDB.selectDBGroups(getGrupoByContactId(String.valueOf(id_contato)), connection));
        int id_telefone = scanner.nextInt();
        ServiceDB.delete("delete from contact_groups where contact_id = " + id_contato + " and " + " group_id = " + id_telefone + ";",
                connection);
    }

    public static void getAllContacts(Connection connection) {
        System.out.format("%-6s | %-20s | %-12s | %-20s  \n", "ID", "NOME", "TELEFONES", "GRUPOS");
        System.out.println("-----------------------------------------------|");
        for (var objeto : selectDBContact(getAllcontacts(), connection)) {
            List<Phones> listTelefones = selectDBPhones(getTelefoneByContactId(String.valueOf(objeto.getContact_id())), connection);
            List<Groups> listGroups = selectDBGroups(getGrupoByContactId(String.valueOf(objeto.getContact_id())), connection);
            String nomeSobrenome = objeto.getFirstName() + " " + objeto.getLastName();
            System.out.format("%-6s | %-20s | %-12s | %-20s  \n", objeto.getContact_id(), nomeSobrenome, listTelefones, listGroups);
        }
    }

    public static void getContactById(Connection connection) {
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println(ServiceDB.selectDBContact(getContactbyId(id_contato), connection));
    }

    public static void getContactByName(Connection connection) {
        System.out.println("Digite o nome :");
        String firstNome = scanner.next();
        System.out.println(ServiceDB.selectDBContact("select * from contacts where first_name " + " = " + firstNome + " ;"
                , connection));
    }

    public static void removerContact(Connection connection){
        getAllContacts(connection);
        System.out.println("Digite o id do contato para remover : ");
        int id_contato = scanner.nextInt();
        ServiceDB.delete("delete from contact_phone where contact_id = " + id_contato + ";",
                connection);
        ServiceDB.delete("delete from contact_groups where contact_id = " + id_contato + ";",
                connection);
        ServiceDB.delete("delete from contacts where contact_id = " + id_contato + ";",
                connection);
    }

}

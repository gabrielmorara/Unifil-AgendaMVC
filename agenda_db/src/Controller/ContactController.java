package Controller;

import Models.Contact;

import java.sql.Connection;
import java.util.List;

import static Controller.ServiceDB.*;

public class ContactController {

    public static int inserirContato(Connection connection, String firstNome, String lastName, String email) {
        return ServiceDB.insertTables(connection, insertContact(firstNome, lastName, email));
    }

    public static void vincularContatoTelefone(Connection connection, int id_contact, int id_telefone) {
        ServiceDB.insertTables(connection, ServiceDB.insertContactPhone(id_contact, id_telefone));
    }

    public static void vincularContatoGrupo(Connection connection, int id_group, int id_contact) {
        ServiceDB.insertTables(connection, ServiceDB.insertGroupsContact(id_group, id_contact));
    }

    public static void getAllContacts(Connection connection) {
        for (var objeto : selectDBContact(getAllcontacts(), connection)) {
            System.out.println(objeto.toString());
            System.out.println(selectDBPhones(getTelefoneByContactId(String.valueOf(objeto.getContact_id())), connection));
            System.out.println(selectDBGroups(getGrupoByContactId(String.valueOf(objeto.getContact_id())), connection));
        }
    }



    public static void listContatos() {
        List<Contact> contatos = ContactController.getAll();

        System.out.format("%-6s | %-20s | %-12s   |\n", "ID", "NOME", "TELEFONE");
        System.out.println("-----------------------------------------------|");

        for (Contact contact : contatos) {
            for (Phone phone : contact.getPhones()) {
                String telefoneFormatado = String.format("(%s) %s", phone.getPhone().substring(0, 2),
                        phone.getPhone().substring(2, phone.getPhone().length()));

                if (phone.getPhone().length() == 10) {
                    telefoneFormatado += " ";
                }

                System.out.format("%-6s | %-20s | %-12s |\n", contact.getContactId(), contact.getFirstName() + " " + contact.getLastName(), telefoneFormatado);
            }
        }
    }
}

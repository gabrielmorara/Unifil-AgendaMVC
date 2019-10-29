package View;

import Controller.ContactController;
import Controller.GroupController;
import Controller.PhoneController;
import Controller.ServiceDB;
import Models.Contact;
import Models.Groups;
import Models.Phones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Controller.ServiceDB.*;
import static Controller.ServiceDB.getContactbyId;

public class Main {

    private static String separador = ",";
    private static Scanner scanner = new Scanner(System.in);
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) throws SQLException {
        emf = Persistence.createEntityManagerFactory("agenda-db");
        em = emf.createEntityManager();
        int opcao = 0;
        while (opcao != 99) {
            getMenu();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    inserirContato();
                    break;
                case 2:
                    inserirTelefone();
                    break;
                case 3:
                    inserirGrupo();
                    break;
                case 4:
                    vincularContatoTelefone();
                    break;
                case 5:
                    desvincularContatoTelefone();
                    break;
                case 6:
                    vincularContatoGrupo();
                    break;
                case 7:
                    desvincularContatoGrupo();
                    break;
                case 8:
                    getAllContacts();
                    break;
                case 9:
                    getAllTelefone();
                    break;
                case 10:
                    getAllGroups();
                    break;
                case 11:
                    getContactById();
                    break;
//                case 12:
//                    getContactByName(connection);
//                    break;
//                case 14:
//                    removerContact(connection);
//                    break;
//                case 15:
//                    removerTelefone(connection);
//                    break;
//                case 16:
//                    removerGroup(connection);
//                    break;
//                case 17:
//                    System.out.println(getAllgrop(connection));
//                    break;
                default:
                    System.out.println("Opção Invalida!");
            }
        }
    }

    private static void getMenu() {
        System.out.println("1 - Inserir Contato");
        System.out.println("2 - Inserir Telefone");
        System.out.println("3 - Inserir Grupo");
        System.out.println("4 - Vincular Telefone com Contato");
        System.out.println("5 - Desvincular Telefone com Contato");
        System.out.println("6 - Vincular Contato com Grupo");
        System.out.println("7 - Desvincular Contato com Grupo");
        System.out.println("8 - Listar todos os Contatos");
        System.out.println("9 - Listar todos os Telefones");
        System.out.println("10 - Listar todos os Grupos");
        System.out.println("11 - Buscar contato por Id");
        System.out.println("12 - Buscar contato por Nome");
        System.out.println("14 - Remover Contato");
        System.out.println("15 - Remover Telefone");
        System.out.println("16 - Remover Grupo");
        System.out.println("17 - Listar Contatos por Grupo");
        System.out.println("99 - Encerrar o programa");
    }

    public static void inserirContato() {
        System.out.println("Digite o nome :");
        String firstName = scanner.next();
        System.out.println("Digite o sobrenome :");
        String lastName = scanner.next();
        System.out.println("Digite o email :");
        String email = scanner.next();
        List<Phones> phones = new ArrayList<>();
        int aux;
        do {
            phones.add(inserirTelefone());
            System.out.println("1 - Cadastrar mais telefone");
            System.out.println("2 - Prosseguir");
            aux = scanner.nextInt();
        } while (aux == 1);
        System.out.println("1 - Vincular a grupo existente");
        System.out.println("2 - Cadastrar novo grupo e vincular");
        System.out.println("3 - Finalizar sem grupo");
        aux = scanner.nextInt();
        List<Groups> groups = new ArrayList<>();
        if (aux == 1) {
            do {
                System.out.println(GroupController.getAllGroups(em));
                System.out.println("Digite o id do grupo");
                int id_group = scanner.nextInt();
                groups.add(GroupController.getGroupByID(id_group, em));
                aux = scanner.nextInt();
                System.out.println("1 - Vincular a mais Grupo");
                System.out.println("2 - Finalizar");
            } while (aux == 1);
        }
        if (aux == 2) {
            groups.add(inserirGrupo());
        }
        ContactController.insertContact(firstName, lastName, email, phones, groups, em);
        System.out.println("Contato cadastrado com sucesso!");
    }

    public static void vincularContatoTelefone() {
        getAllContacts();
        System.out.println("Digite o Id do contato: ");
        int id_contato = scanner.nextInt();
        getAllTelefone();
        System.out.println("Digite o Id do telefone: ");
        int id_phone = scanner.nextInt();
        ContactController.updatePhones(id_contato, id_phone, em, 0);
    }

    public static void vincularContatoGrupo() {
        getAllContacts();
        System.out.println("Digite o Id do contato: ");
        int id_contato = scanner.nextInt();
        getAllGroups();
        System.out.println("Digite o Id do grupo : ");
        int id_grupo = scanner.nextInt();
        ContactController.updateGroups(id_contato, id_grupo, em, 0);
    }

    public static void desvincularContatoTelefone() {
        getAllContacts();
        System.out.println("Digite o Id do contato: ");
        int id_contato = scanner.nextInt();
        getAllTelefone();
        System.out.println("Digite o Id do telefone: ");
        int id_phone = scanner.nextInt();
        ContactController.updatePhones(id_contato, id_phone, em, 1);
    }

    public static void desvincularContatoGrupo() {
        getAllContacts();
        System.out.println("Digite o Id do contato: ");
        int id_contato = scanner.nextInt();
        getAllGroups();
        System.out.println("Digite o Id do grupo : ");
        int id_grupo = scanner.nextInt();
        ContactController.updateGroups(id_contato, id_grupo, em, 1);
    }

    public static void getAllContacts() {
        System.out.format("%-6s | %-20s | %-12s | %-20s  \n", "ID", "NOME", "TELEFONES", "GRUPOS");
        System.out.println("-----------------------------------------------|");
        List<Contact> results = ContactController.getAllContact(em);
        for (var objeto : results) {
            String nomeSobrenome = objeto.getFirstName() + " " + objeto.getLastName();
            System.out.format("%-6s | %-20s | %-12s | %-20s  \n", objeto.getId(), nomeSobrenome, objeto.getPhonesList(), objeto.getGroupsList());
        }
    }

    public static void getContactById() {
        System.out.println("Digite o Id do contato: ");
        int id_contato = scanner.nextInt();
        Contact contact = ContactController.getContactByID(id_contato, em);
        System.out.format("%-6s | %-20s | %-12s | %-20s  \n", "ID", "NOME", "TELEFONES", "GRUPOS");
        String nomeSobrenome = contact.getFirstName() + " " + contact.getLastName();
        System.out.format("%-6s | %-20s | %-12s | %-20s  \n", contact.getId(), nomeSobrenome, contact.getPhonesList(), contact.getGroupsList());
    }

    public static void getContactByName(Connection connection) {
        System.out.println("Digite o nome :");
        String firstNome = scanner.next();
        System.out.println(ServiceDB.selectDBContact("select * from contacts where first_name " + " = " + firstNome + " ;"
                , connection));
    }

    public static void removerContact(Connection connection) {
        System.out.println("Digite o id do contato para remover : ");
        int id_contato = scanner.nextInt();
        ServiceDB.delete("delete from contact_phone where contact_id = " + id_contato + ";",
                connection);
        ServiceDB.delete("delete from contact_groups where contact_id = " + id_contato + ";",
                connection);
        ServiceDB.delete("delete from contacts where contact_id = " + id_contato + ";",
                connection);
    }

    public static Groups inserirGrupo() {
        System.out.println("Digit o nome do grupo: ");
        String nome_grupo = scanner.next();
        return GroupController.insertGroup(nome_grupo, em);
    }

    public static void getAllGroups() {
        System.out.format("%-6s | %-20s \n", "ID", "NOME");
        System.out.println("-----------------------------------------------|");
        List<Groups> results = GroupController.getAllGroups(em);
        for (var objeto : results) {
            System.out.format("%-6s | %-20s \n", objeto.getId(), objeto.getName());
        }
    }

    public static void removerGroup(Connection connection) {
        System.out.println(selectDBGroups(ServiceDB.getAllGroups(), connection));
        System.out.println("Digite o id do grupo para remover : ");
        int id_contato = scanner.nextInt();
        ServiceDB.delete("delete from contact_groups where contact_id = " + id_contato + ";",
                connection);
        ServiceDB.delete("delete from groups where group_id = " + id_contato + ";",
                connection);
    }

    public static Phones inserirTelefone() {
        System.out.println("Digite o telefone :");
        String telefoneAdd = scanner.next();
        return PhoneController.insertPhone(telefoneAdd, em);
    }

    public static void getAllTelefone() {
        System.out.format("%-6s | %-20s \n", "ID", "TELEFONE");
        System.out.println("-----------------------------------------------|");
        List<Phones> results = PhoneController.getAllPhones(em);
        for (var objeto : results) {
            System.out.format("%-6s | %-20s \n", objeto.getId(), objeto.getPhone());
        }
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


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

import static Controller.GroupController.*;
import static Controller.PhoneController.*;
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

                    int id_contact = inserirContato();
//                    System.out.println(id_contact);
//                    int aux;
//                    do {
//                        int id_telefone = inserirTelefone();
//                        vincularContatoTelefone(id_contact, id_telefone);
//                        System.out.println("1 - Cadastrar mais telefone");
//                        System.out.println("2 - Prosseguir");
//                        aux = scanner.nextInt();
//                    } while (aux == 1);
//                    System.out.println("1 - Vincular a grupo existente");
//                    System.out.println("2 - Cadastrar novo grupo e vincular");
//                    System.out.println("3 - Finalizar sem grupo");
//                    aux = scanner.nextInt();
//                    if (aux == 1) {
//                        do {
//                            System.out.println(selectDBContact(getAllGroups(), connection).toString());
//                            System.out.println("Digite o id do groupo");
//                            int id_group = scanner.nextInt();
//                            vincularContatoGrupo(connection, id_group, id_contact);
//                            aux = scanner.nextInt();
//                            System.out.println("1 - Vincular a mais Grupo");
//                            System.out.println("2 - Finalizar");
//                        } while (aux == 1);
//                    }
//                    if (aux == 2) {
//                        int id_grupo = inserirGrupo(connection);
//                        vincularContatoGrupo(connection, id_grupo, id_contact);
//                    }
//                    System.out.println("Contato cadastrado com sucesso!");
//                    System.out.println(ServiceDB.selectDBContact(ServiceDB.getContactbyId(id_contact), connection));
                    break;
//                case 2:
//                    inserirTelefone(connection);
//                    break;
//                case 3:
//                    inserirGrupo(connection);
//                    break;
//                case 4:
//                    vincularContatoTelefone(connection);
//                    break;
//                case 5:
//                    desvincularContatoTelefone(connection);
//                    break;
//                case 6:
//                    vincularContatoGrupo(connection);
//                    break;
//                case 7:
//                    desvincularContatoGrupo(connection);
//                    break;
                case 8:
                    getAllContacts();
                    break;
//                case 9:
//                    System.out.println(getAllTelefone(connection));
//                    break;
//                case 10:
//                    System.out.println(getAllgrop(connection));
//                    break;
//                case 11:
//                    getContactById(connection);
//                    break;
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
//                default:
//                    System.out.println("Opção Invalida!");
//            }
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


    public static int inserirContato() {
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
        System.out.println("Contato cadastrado com sucesso!");
//        System.out.println(ServiceDB.selectDBContact(ServiceDB.getContactbyId(id_contact), connection));

//        if () {
//            System.out.println("Contato cadastrado com sucesso!");
//        } else {
//            System.out.println("Nao foi possivel cadastrar o contato");
//        }

        return ContactController.insertContact(firstName, lastName, email, phones, groups, em);
    }

    public static void vincularContatoTelefone(Connection connection) {
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println("Digite o id do telefone : ");
        System.out.println(getAllTelefone(connection));
        int id_phone = scanner.nextInt();
        ServiceDB.insertTables(connection, ServiceDB.insertContactPhone(id_contato, id_phone));
    }

    public static void vincularContatoTelefone(int id_contact, int id_telefone) {
//        ServiceDB.insertTables(, ServiceDB.insertContactPhone(id_contact, id_telefone));
    }

    public static void vincularContatoGrupo(Connection connection, int id_group, int id_contact) {
        ServiceDB.insertTables(connection, ServiceDB.insertContactPhone(id_contact, id_group));
    }

    public static void vincularContatoGrupo(Connection connection) {
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println("Digite o id do grupo : ");
        System.out.println(getAllgrop(connection));
        int id_grupo = scanner.nextInt();
        ServiceDB.insertTables(connection, ServiceDB.insertGroupsContact(id_grupo, id_contato));
    }

    public static void desvincularContatoTelefone(Connection connection) {
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println("Digite o id do telefone : ");
        System.out.println(ServiceDB.selectDBPhones(getTelefoneByContactId(String.valueOf(id_contato)), connection));
        int id_telefone = scanner.nextInt();
        ServiceDB.delete("delete from contact_phone where contact_id = " + id_contato + " and " + " phone_id = " + id_telefone + ";",
                connection);
    }

    public static void desvincularContatoGrupo(Connection connection) {
        System.out.println("Digite o id do contato : ");
        int id_contato = scanner.nextInt();
        System.out.println("Digite o id do Grupo : ");
        System.out.println(ServiceDB.selectDBGroups(getGrupoByContactId(String.valueOf(id_contato)), connection));
        int id_telefone = scanner.nextInt();
        ServiceDB.delete("delete from contact_groups where contact_id = " + id_contato + " and " + " group_id = " + id_telefone + ";",
                connection);
    }

    public static void getAllContacts() {
        System.out.format("%-6s | %-20s | %-12s | %-20s  \n", "ID", "NOME", "TELEFONES", "GRUPOS");
        System.out.println("-----------------------------------------------|");
        List<Contact> results = ContactController.getAllContact(em);
        for (var objeto : results) {
//            List<Phones> listTelefones = selectDBPhones(getTelefoneByContactId(String.valueOf(objeto.getId())), connection);
//            List<Groups> listGroups = selectDBGroups(getGrupoByContactId(String.valueOf(objeto.getId())), connection);
            String nomeSobrenome = objeto.getFirstName() + " " + objeto.getLastName();
            System.out.format("%-6s | %-20s | %-12s | %-20s  \n", objeto.getId(), nomeSobrenome);
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

    public static List<Groups> getAllgrop(Connection connection) {
        return selectDBGroups(getAllGroups(), connection);
    }

    public static void removerGroup(Connection connection) {
        System.out.println(selectDBGroups(getAllGroups(), connection));
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


package View;

import Controller.ConnectionDB;
import Controller.ServiceDB;
import Models.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

import static Controller.ContactController.*;
import static Controller.CreateTables.createtables;
import static Controller.GroupController.*;
import static Controller.PhoneController.*;
import static Controller.ServiceDB.getAllGroups;
import static Controller.ServiceDB.selectDBContact;


public class Main {
    public static String separador = ",";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
//        Connection connection = ConnectionDB.createConnection();
//        createtables(connection);
//        int opcao = 0;
//        while (opcao != 99) {
//            getMenu();
//            opcao = scanner.nextInt();
//            switch (opcao) {
//                case 1:
//                    int id_contact = inserirContato(connection);
//                    int aux;
//                    do {
//                        int id_telefone = inserirTelefone(connection);
//                        vincularContatoTelefone(connection, id_contact, id_telefone);
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
//                    break;
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
//                case 8:
//                    getAllContacts(connection);
//                    break;
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
//        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("agenda-db");
        EntityManager em = emf.createEntityManager();

        try {
            Contact contact = new Contact();
            contact.setFirstName("firstName");
            contact.setLastName("lastName");
            contact.setEmail("email");
            em.getTransaction().begin();
            em.persist(contact);
            em.getTransaction().commit();
            TypedQuery<Contact> query = em.createQuery(
                    "SELECT c FROM Contact AS c", Contact.class
            );
            List<Contact> results = query.getResultList();
            System.out.println(results);

        } catch (Exception ex) {
            // Deu pau
            System.out.println("deu pau " + ex.getMessage());
        } finally {
            em.close();
            emf.close();
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
}


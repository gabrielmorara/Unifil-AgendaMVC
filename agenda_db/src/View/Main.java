package View;

import Controller.ConnectionDB;
import Controller.ServiceDB;
import Models.Phones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Controller.ContactController.*;
import static Controller.CreateTables.createtables;
import static Controller.GroupController.inserirGrupo;
import static Controller.PhoneController.getAllTelefone;
import static Controller.PhoneController.inserirTelefone;
import static Controller.ServiceDB.*;

public class Main {
    public static String separador = ",";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionDB.createConnection();
        int opcao = 0;
        while (opcao != 9) {
            getMenu();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome :");
                    String firstNome = scanner.next();
                    System.out.println("Digite o sobrenome :");
                    String lastName = scanner.next();
                    System.out.println("Digite o email :");
                    String email = scanner.next();
                    int id_contact = inserirContato(connection, firstNome, lastName, email);
                    int aux;
                    do {
                        System.out.println("Digite o telefone :");
                        String telefoneAdd = scanner.next();
                        int id_telefone = inserirTelefone(connection, telefoneAdd);
                        vincularContatoTelefone(connection, id_contact, id_telefone);
                        System.out.println("1 - Cadastrar mais telefone");
                        System.out.println("2 - Prosseguir");
                        aux = scanner.nextInt();
                    } while (aux == 1);
                    System.out.println("1 - Vincular a grupo existente");
                    System.out.println("2 - Cadastrar novo grupo");
                    System.out.println("3 - Finalizar sem grupo");
                    aux = scanner.nextInt();
                    if (aux == 1) {
                        do {
                            System.out.println(selectDBContact(getAllGroups(), connection).toString());
                            System.out.println("Digite o id do groupo");
                            int id_group = scanner.nextInt();
                            vincularContatoGrupo(connection, id_group, id_contact);
                            aux = scanner.nextInt();
                            System.out.println("1 - Vincular a mais Grupo");
                            System.out.println("2 - Finalizar");
                        } while (aux == 1);
                    }
                    if (aux == 2) {
                        System.out.println("Digit o nome do grupo: ");
                        String nome_grupo = scanner.next();
                        inserirGrupo(connection, nome_grupo);
                    }
                    System.out.println("Contato cadastrado com sucesso!");
                    System.out.println(ServiceDB.selectDBContact(ServiceDB.getContactbyId(id_contact), connection));
                    break;
                case 2:
                    System.out.println("Digite o telefone :");
                    String telefoneAdd = scanner.next();
                    int id_telefone = inserirTelefone(connection, telefoneAdd);
                    break;
                case 3:
                    System.out.println("Digite o nome do grupo : ");
                    String nome_grupo = scanner.next();
                    inserirGrupo(connection, nome_grupo);
                    break;
                case 4:
//                    System.out.println(getAllContacts(connection));
                    System.out.println("Digite o id do contato : ");
                    int id_contato = scanner.nextInt();
                    System.out.println("Digite o id do telefone : ");
                    System.out.println(getAllTelefone(connection));
                    int id_phone = scanner.nextInt();
                    vincularContatoTelefone(connection, id_contato, id_phone);
                    break;
                case 5:
//                    System.out.println(getAllContacts(connection));
                    System.out.println("Digite o id do contato : ");
                    id_contato = scanner.nextInt();
                    System.out.println("Digite o id do grupo : ");
//                    System.out.println(getAllContacts(connection));
                    int id_grupo = scanner.nextInt();
                    vincularContatoGrupo(connection, id_grupo, id_contato);
                    break;
                case 6:
                    getAllContacts(connection);
                    break;
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
        System.out.println("5 - Vincular Contato com Grupo");
        System.out.println("6 - Listar Todos Contatos");
        System.out.println("7 - Buscar contato por Id");
        System.out.println("8 - Buscar contato por Nome");
        System.out.println("9 - Listar todos os Telefones");
        System.out.println("10 - Listar todos os Grupos");
        System.out.println("99 - Encerrar o programa");
    }
}


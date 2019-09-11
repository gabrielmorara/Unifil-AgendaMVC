package View;

import Controller.ConnectionDB;
import Controller.ServiceDB;
import Models.Phones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Controller.ServiceDB.*;

public class Main {
    // adicionar extensao do sqlite
    // e database na versão professional do intelij
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
                    List<Phones> telefones = new ArrayList<>();
                    System.out.println("Digite o nome :");
                    String firstNome = scanner.next();
                    System.out.println("Digite o sobrenome :");
                    String lastName = scanner.next();
                    System.out.println("Digite o email :");
                    String email = scanner.next();
                    int id_contact = ServiceDB.insertTables(connection, insertContact(firstNome, lastName, email));
                    int aux;
                    do {
                        System.out.println("Digite o telefone :");
                        String telefoneAdd = scanner.next();
                        int id_telefone = ServiceDB.insertTables(connection, ServiceDB.insertPhone(telefoneAdd));
                        ServiceDB.insertTables(connection, ServiceDB.insertContactPhone(id_contact, id_telefone));
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
                            System.out.println("Digiti os id dos groups");
                            int id_group = scanner.nextInt();
                            ServiceDB.insertTables(connection, ServiceDB.insertContactPhone(id_group, id_contact));
                            aux = scanner.nextInt();
                            System.out.println("1 - Vincular a mais Grupo");
                            System.out.println("2 - Finalizar");
                        } while (aux == 1);

                    }
                    if(aux == 2){
                        System.out.println("Digit o nome do grupo: ");
                        String nome_grupo = scanner.next();
                        ServiceDB.insertTables(connection, ServiceDB.insertGroup(nome_grupo));
                    }
                    System.out.println("Contato cadastrado com sucesso!");
                    System.out.println(ServiceDB.selectDBContact(ServiceDB.getContactbyId(id_contact), connection));
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Digite o nome do grupo :");
                    String grupoName = scanner.next();
                    ServiceDB.insertTables(connection, ServiceDB.insertGroup(grupoName));
                    break;
                case 4:
                    System.out.println(ServiceDB.selectDBContact(ServiceDB.getAllcontacts(), connection).toString());
                    break;
                case 5:
                    System.out.println("Digite o Id :");
                    int IdAgenda = scanner.nextInt();
                    System.out.println(ServiceDB.selectDBContact(getContactbyId(IdAgenda), connection).toString());
                    break;
                case 6:
                    System.out.println("Digite o nome :");
                    String findNome = scanner.next();
                    System.out.println(ServiceDB.selectDBContact(getContactByName(findNome), connection).toString());
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Opção Invalida!");
            }
        }
    }

    private static void getMenu() {
        System.out.println("1 - Inserir Contato");
        System.out.println("2 - Adicionar Telefone");
        System.out.println("3 - Adcionar Grupo");
        System.out.println("4 - Listar Todos Contatos");
        System.out.println("5 - Buscar contato por Id");
        System.out.println("6 - Buscar contato por Nome");
        System.out.println("4 - Inserir Contatos no grupo");
        System.out.println("9 - Encerrar o programa");
    }
}


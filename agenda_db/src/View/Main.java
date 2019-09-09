package View;

import Controller.ConnectionDB;
import Controller.ServiceDB;
import java.sql.*;
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
                    System.out.println("Digite o nome :");
                    String firstNome = scanner.next();
                    System.out.println("Digite o sobrenome :");
                    String lastName = scanner.next();
                    System.out.println("Digite o email :");
                    String email = scanner.next();
                    ServiceDB.insertTables(connection, insertContact(firstNome, lastName, email));
                    break;
                case 2:
                    System.out.println("Digite o telefone :");
                    String telefone = scanner.next();
                    ServiceDB.insertTables(connection, ServiceDB.insertPhone(telefone));
                    break;
                case 3:
                    System.out.println("Digite o nome do grupo :");
                    String grupoName = scanner.next();
                    ServiceDB.insertTables(connection, ServiceDB.insertGroup(grupoName));
                    break;
                case 4:
                    System.out.println(ServiceDB.selectDB(getAllcontacts()));
                    break;
                case 5:
                    System.out.println("Digite o Id :");
                    int IdAgenda = scanner.nextInt();
                    System.out.println(ServiceDB.selectDB(getContactbyId(IdAgenda)));
                    break;
                case 6:
                    System.out.println("Digite o nome :");
                    String findNome = scanner.next();
                    System.out.println(ServiceDB.selectDB(getContactByName(findNome)));
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
        System.out.println("2 - Inserir Phone");
        System.out.println("3 - Inserir Grupo");
        System.out.println("4 - Listar Contatos");
        System.out.println("5 - Buscar contato por Id");
        System.out.println("6 - Buscar contato por Nome");
        System.out.println("9 - Encerrar o programa");
    }
}


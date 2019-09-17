package Controller;

import Models.Groups;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static Controller.ServiceDB.*;

public class GroupController {
    private static Scanner scanner = new Scanner(System.in);

    public static int inserirGrupo(Connection connection){
        System.out.println("Digit o nome do grupo: ");
        String nome_grupo = scanner.next();
        return ServiceDB.insertTables(connection, ServiceDB.insertGroup(nome_grupo));
    }

    public static List<Groups> getAllgrop(Connection connection) {
        return selectDBGroups(getAllGroups(), connection);
    }

    public static void removerGroup(Connection connection){

    }

}

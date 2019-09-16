package Controller;

import Models.Groups;

import java.sql.Connection;
import java.util.List;

import static Controller.ServiceDB.*;

public class GroupController {

    public static int inserirGrupo(Connection connection, String nome_grupo){
        return ServiceDB.insertTables(connection, ServiceDB.insertGroup(nome_grupo));
    }

    public static List<Groups> getAllContacts(Connection connection) {
        return selectDBGroups(getAllGroups(), connection);
    }
}

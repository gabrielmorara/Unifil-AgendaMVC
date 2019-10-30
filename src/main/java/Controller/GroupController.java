package Controller;

import Models.Contact;
import Models.Groups;
import com.sun.xml.bind.v2.model.core.ID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupController {

    public static Groups insertGroup(String name, EntityManager em) {
        Groups groups = new Groups();
        try {
            groups.setName(name);
            em.getTransaction().begin();
            em.persist(groups);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            throw ex;
        }
        return groups;
    }

    public static List<Groups> getAllGroups(EntityManager em) {
        TypedQuery<Groups> query = null;
        try {
            query = em.createQuery(
                    "SELECT c FROM Groups AS c", Groups.class
            );
            List<Groups> results = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        assert query != null;
        return query.getResultList();
    }

    public static Groups getGroupByID(int id, EntityManager em) {
        Groups group = null;
        try {
            group = em.find(Groups.class, id);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return group;
    }

    public static void removeGroup(int id, EntityManager em) {
        Groups group = null;
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(Groups.class, id));
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}

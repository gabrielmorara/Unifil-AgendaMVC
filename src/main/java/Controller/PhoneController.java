package Controller;

import Models.Contact;
import Models.Groups;
import Models.Phones;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PhoneController {

    public static Phones insertPhone(String number, EntityManager em) {
        Phones phone = new Phones();
        try {
            phone.setPhone(number);
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            throw ex;
        }
        return phone;
    }

    public static List<Phones> getAllPhones(EntityManager em) {
        TypedQuery<Phones> query = null;
        try {
            query = em.createQuery(
                    "SELECT c FROM Phones AS c", Phones.class
            );
            List<Phones> results = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        assert query != null;
        return query.getResultList();
    }

    public static Phones getPhoneByID(int id, EntityManager em) {
        Phones phones = null;
        try {
            phones = em.find(Phones.class, id);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return phones;
    }

    public static void removePhone(int id, EntityManager em) {
        Phones phone = null;
        try {
            phone = em.find(Phones.class, id);
            em.remove(phone);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}

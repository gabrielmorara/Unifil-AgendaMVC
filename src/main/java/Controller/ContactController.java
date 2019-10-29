package Controller;

import Models.Contact;
import Models.Groups;
import Models.Phones;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ContactController {

    public static int insertContact(String firstName, String lastName, String email, List<Phones> phones, List<Groups> groups, EntityManager em) {
        Contact contact = new Contact();
        try {
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setEmail(email);
            contact.setPhonesList(phones);
            contact.setGroupsList(groups);
            em.getTransaction().begin();
            em.persist(contact);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            throw ex;
        }
        return contact.getId();
    }

    public static List<Contact> getAllContact(EntityManager em) {
        TypedQuery<Contact> query = null;
        try {
            query = em.createQuery(
                    "SELECT c FROM Contact AS c", Contact.class
            );
            List<Contact> results = query.getResultList();

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        assert query != null;
        return query.getResultList();
    }

    public static Contact getContactByID(int id, EntityManager em) {
        Contact contact = null;
        try {
            contact = em.find(Contact.class, id);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return contact;
    }

    public static void updatePhones(int id_contato, int id_phone, EntityManager em, int opt) {
        Contact contact = em.find(Contact.class, id_contato);
        try {
            List<Phones> phones = contact.getPhonesList();
            if (opt == 0) {
                phones.add(PhoneController.getPhoneByID(id_phone, em));
            } else {
                if (opt == 1) {
                    phones.remove(PhoneController.getPhoneByID(id_phone, em));
                }
            }
            contact.setPhonesList(phones);
            em.getTransaction().begin();
            em.merge(contact);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            throw ex;
        }
    }

    public static void updateGroups(int id_contato, int id_grupo, EntityManager em, int opt) {
        Contact contact = em.find(Contact.class, id_contato);
        try {
            List<Groups> groups = contact.getGroupsList();
            if (opt == 0) {
                groups.add(GroupController.getGroupByID(id_grupo, em));
            } else {
                if (opt == 1) {
                    groups.remove(GroupController.getGroupByID(id_grupo, em));
                }
            }
            contact.setGroupsList(groups);
            em.getTransaction().begin();
            em.merge(contact);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            throw ex;
        }
    }
}

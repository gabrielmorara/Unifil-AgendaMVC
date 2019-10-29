package Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Phones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String phone;

    @ManyToMany(mappedBy = "phonesList")
    private Set<Contact> listContacts = new HashSet<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Contact> getListContacts() {
        return listContacts;
    }

    public void setListContacts(Set<Contact> listContacts) {
        this.listContacts = listContacts;
    }

    public Phones() {

    }

    public Phones(int phone_id, String phone) {
        this.id = phone_id;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int phone_id) {
        this.id = phone_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Phones{" +
                "phone_id=" + id +
                ", phone='" + phone + '\'' +
                '}';
    }
}

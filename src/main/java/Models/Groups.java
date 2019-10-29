package Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "groupsList")
    private Set<Contact> listContacts = new HashSet<>();

    public Groups(int group_id, String name) {
        this.id = group_id;
        this.name = name;
    }

    public Groups() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Contact> getListContacts() {
        return listContacts;
    }

    public void setListContacts(Set<Contact> listContacts) {
        this.listContacts = listContacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "group_id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

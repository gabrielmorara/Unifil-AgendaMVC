package Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Groups {

    @Id
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "groupsList")
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

    public Groups(int group_id, String name) {
        this.id = group_id;
        this.name = name;
    }

    public Groups() {

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

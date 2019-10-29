package Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "ContactsGroups", joinColumns = @JoinColumn(name = "Contact_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Group_Id", referencedColumnName = "id"))
    private List<Groups> groupsList;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "ContactsPhones", joinColumns = @JoinColumn(name = "Contact_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Phone_Id", referencedColumnName = "id"))
    private List<Phones> phonesList;

    public Contact() {

    }

    public Contact(int contactId){
        this.id = contactId;
    }

    public Contact(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    public List<Phones> getPhonesList() {
        return phonesList;
    }

    public void setPhonesList(List<Phones> phonesList) {
        this.phonesList = phonesList;
    }

    @Override
    public String toString() {
        return "Models.Contact{" +
                "contact_id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

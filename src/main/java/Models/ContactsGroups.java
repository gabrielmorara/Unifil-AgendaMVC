package Models;

public class ContactsGroups {
    private int id;
    private String group_id;
    private int contact_id;

    public ContactsGroups(int id, String group_id, int contact_id) {
        this.id = id;
        this.group_id = group_id;
        this.contact_id = contact_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    @Override
    public String toString() {
        return "main.java.Models.ContactsGroups{" +
                "id=" + id +
                ", group_id='" + group_id + '\'' +
                ", contact_id=" + contact_id +
                '}';
    }
}

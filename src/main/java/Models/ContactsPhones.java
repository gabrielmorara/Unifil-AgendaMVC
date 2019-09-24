package main.java.Models;

public class ContactsPhones {
    private int id;
    private int contact_id;
    private int phone_id;

    public ContactsPhones(int id, int contact_id, int phone_id) {
        this.id = id;
        this.contact_id = contact_id;
        this.phone_id = phone_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    @Override
    public String toString() {
        return "Models.ContactsPhones{" +
                "id=" + id +
                ", contact_id=" + contact_id +
                ", phone_id=" + phone_id +
                '}';
    }
}

public class Contact {
    private int contact_id;
    private String firstName;

    @Override
    public String toString() {
        return "Contact{" +
                "contact_id=" + contact_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private String lastName;
    private String email;

    public Contact(int id, String firstName, String lastName, String email) {
        this.contact_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
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
}

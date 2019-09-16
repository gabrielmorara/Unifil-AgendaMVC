package Models;

public class Phones {
    @Override
    public String toString() {
        return "Phones{" +
                "phone_id=" + phone_id +
                ", phone='" + phone + '\'' +
                '}';
    }

    private int phone_id;
    private String phone;

    public Phones(int phone_id, String phone) {
        this.phone_id = phone_id;
        this.phone = phone;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

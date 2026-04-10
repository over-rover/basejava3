package webapp.model;

public class Contact {
    String contact;

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return contact;
    }
}
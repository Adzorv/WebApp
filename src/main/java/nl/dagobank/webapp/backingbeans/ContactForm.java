package nl.dagobank.webapp.backingbeans;

public class ContactForm {
    private String firstName;
    private String prefix;
    private String lastName;
    private String email;
    private String phoneNumber;

    public ContactForm() {
        this(null, null, null, null, null);
    }

    public ContactForm(String firstName, String prefix, String lastName, String email, String phoneNumber) {
        super();
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

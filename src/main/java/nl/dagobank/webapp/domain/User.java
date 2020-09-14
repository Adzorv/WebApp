package nl.dagobank.webapp.domain;
import javax.persistence.*;
import java.util.Objects;


@Entity
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Embedded
    private FullName fullName;
    @Embedded
    private Address address;
    @Embedded
    private ContactDetails contactDetails;
    @Embedded
/*    @Column(unique = true, nullable = false)*/
    private PersonalDetails personalDetails;
    @Embedded
    private InlogCredentials inlogCredentials;

    public User(){}

    public User(FullName fullName, Address address, ContactDetails contactDetails, PersonalDetails personalDetails, InlogCredentials inlogCredentials) {
        this.fullName = fullName;
        this.address = address;
        this.contactDetails = contactDetails;
        this.personalDetails = personalDetails;
        this.inlogCredentials = inlogCredentials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return inlogCredentials.getUserName().equals(user.inlogCredentials.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(inlogCredentials.getUserName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FullName getUserFullName() {
        return fullName;
    }

    public void setUserFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Address getUserAddress() {
        return address;
    }

    public void setUserAddress(Address address) {
        this.address = address;
    }

    public ContactDetails getUserContactDetails() {
        return contactDetails;
    }

    public void setUserContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public PersonalDetails getUserPersonalDetails() {
        return personalDetails;
    }

    public void setUserPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public InlogCredentials getUserInlogCredentials() {
        return inlogCredentials;
    }

    public void setUserInlogCredentials(InlogCredentials inlogCredentials) {
        this.inlogCredentials = inlogCredentials;
    }
}

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

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName( FullName fullName) {
        this.fullName = fullName;
    }

    public Address getUserAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public InlogCredentials getInlogCredentials() {
        return inlogCredentials;
    }

    public void setInlogCredentials(InlogCredentials inlogCredentials) {
        this.inlogCredentials = inlogCredentials;
    }
}

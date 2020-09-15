package nl.dagobank.webapp.domain;

import javax.persistence.Entity;

@Entity
public class Employee extends User {

    private String role;

    public Employee() {
        super();
    }

    public Employee(FullName fullName, Address address, ContactDetails contactDetails, InlogCredentials inlogCredentials, PersonalDetails personalDetails, String role) {
        super(fullName, address, contactDetails, personalDetails, inlogCredentials);


    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }
}

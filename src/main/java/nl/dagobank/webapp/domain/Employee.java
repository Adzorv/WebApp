package nl.dagobank.webapp.domain;

import javax.persistence.Entity;

@Entity
public class Employee extends User {

    private String role;

    public Employee() {
        super();
    }

    public Employee(UserFullName userFullName, UserAddress userAddress, UserContactDetails userContactDetails, UserInlogCredentials userInlogCredentials, UserPersonalDetails userPersonalDetails, String role) {
        super(userFullName, userAddress, userContactDetails, userPersonalDetails, userInlogCredentials);


    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }
}

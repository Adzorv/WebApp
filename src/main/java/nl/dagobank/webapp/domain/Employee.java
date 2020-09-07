package nl.dagobank.webapp.domain;

import javax.persistence.Entity;

@Entity
public class Employee extends User {

    private String role;

    public Employee() {
        super();
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }
}

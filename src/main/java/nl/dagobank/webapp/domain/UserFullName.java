package nl.dagobank.webapp.domain;

import javax.persistence.Embeddable;

@Embeddable
public class UserFullName {
    private String firstName;
    private String prefix;
    private String lastName;

    public UserFullName(String firstName, String prefix, String lastName) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
    }

    public UserFullName() {

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

    @Override
    public String toString() {
        return String.format(firstName + " " + prefix + " " + lastName);
    }
}

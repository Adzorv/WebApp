package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.RegistrationForm;

public class EmployeeFactory extends CustomerFactory{
    private String role;

    public EmployeeFactory(RegistrationForm registrationForm, String userName, String password, String role) {
        super(registrationForm, userName, password);
        this.role = role;
    }

    public EmployeeFactory(RegistrationForm registrationForm, String userName, String password) {
        super(registrationForm, userName, password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

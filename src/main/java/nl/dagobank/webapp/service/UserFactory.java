package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.domain.*;

public class UserFactory {
    FullName fullName;
    Address address;
    ContactDetails contactDetails;
    PersonalDetails personalDetails;
    InlogCredentials inlogCredentials;


    public UserFactory(RegistrationForm registrationForm, String userName, String password) {
        fullName = new FullName(registrationForm.getFirstName(), registrationForm.getPrefix(), registrationForm.getLastName());
        address = new Address(registrationForm.getStreetName(), registrationForm.getHouseNumber(), registrationForm.getHouseNumberAnnex(),
                registrationForm.getPostCode(), registrationForm.getCity());
        contactDetails = new ContactDetails(registrationForm.getPhoneNumber(), registrationForm.getEmail());
        personalDetails = new PersonalDetails(registrationForm.getBirthDate(), registrationForm.getBsn());
        inlogCredentials = new InlogCredentials(userName, password);

    }

    public Customer createCustomer() {
        return new Customer(fullName, address, contactDetails, inlogCredentials, personalDetails);
    }

    public Employee createEmployee(String role) {
        return new Employee(fullName, address, contactDetails, inlogCredentials, personalDetails, role);

    }

}





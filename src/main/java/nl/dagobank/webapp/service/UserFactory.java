package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.domain.*;

import java.util.Map;

public class UserFactory {
    UserFullName userFullName;
    UserAddress userAddress;
    UserContactDetails userContactDetails;
    UserPersonalDetails userPersonalDetails;
    UserInlogCredentials userInlogCredentials;


    public UserFactory(RegistrationForm registrationForm, String userName, String password) {
        userFullName = new UserFullName(registrationForm.getFirstName(), registrationForm.getPrefix(), registrationForm.getFirstName());
        userAddress = new UserAddress(registrationForm.getStreetName(), registrationForm.getHouseNumber(), registrationForm.getHouseNumberAnnex(),
                registrationForm.getPostCode(), registrationForm.getCity());
        userContactDetails = new UserContactDetails(registrationForm.getPhoneNumber(), registrationForm.getEmail());
        userPersonalDetails = new UserPersonalDetails(registrationForm.getBirthDate(), registrationForm.getBsn());
        userInlogCredentials = new UserInlogCredentials(userName, password);

    }

    public Customer createCustomer() {
        return new Customer(userFullName, userAddress, userContactDetails, userInlogCredentials, userPersonalDetails);
    }

    public Employee createEmployee(String role) {
        return new Employee(userFullName, userAddress, userContactDetails, userInlogCredentials, userPersonalDetails, role);

    }

}





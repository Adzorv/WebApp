package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.domain.*;


public class CustomerFactory {

    private UserFullName userFullName;
    private UserAddress userAddress;
    private UserContactDetails userContactDetails;
    private UserInlogCredentials userInlogCredentials;
    private UserPersonalDetails userPersonalDetails;


    public CustomerFactory(RegistrationForm registrationForm, String userName, String password) {
        this.userFullName = new UserFullName(registrationForm.getFirstName(), registrationForm.getPrefix(), registrationForm.getLastName());
        this.userAddress = new UserAddress(registrationForm.getStreetName(), registrationForm.getHouseNumber(), registrationForm.getHouseNumberAnnex(),
                registrationForm.getPostCode(), registrationForm.getCity());
        this.userContactDetails = new UserContactDetails(registrationForm.getPhoneNumber(), registrationForm.getEmail());
        this.userInlogCredentials = new UserInlogCredentials(userName, password);
        this.userPersonalDetails = new UserPersonalDetails(registrationForm.getBirthDate(), registrationForm.getBsn());
    }
//todo: make static en geef alle attributen mee
    public Customer create() {
        return new Customer(userFullName, userAddress, userContactDetails, userInlogCredentials, userPersonalDetails);
    }

}
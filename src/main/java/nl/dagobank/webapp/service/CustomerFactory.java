package nl.dagobank.webapp.service;

import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.domain.*;

import java.time.LocalDate;


public class CustomerFactory {

   private String firstName;
   private String prefix;
   private String lastName;
   private String streetName;
   private int houseNumber;
   private String houseNumberAnnex;
   private String postCode;
   private String city;
   private String phoneNumber;
   private String email;
   private LocalDate birthDate;
   private int bsn;
   private String userName;
   private String password;


    //todo: make static en geef alle attributen mee
    public CustomerFactory(RegistrationForm registrationForm, String userName, String password) {
       this.firstName  = registrationForm.getFirstName();
       this.prefix = registrationForm.getPrefix();
       this.lastName = registrationForm.getLastName();
       this.streetName = registrationForm.getStreetName();
       this.houseNumber = registrationForm.getHouseNumber();
       this.houseNumberAnnex = registrationForm.getHouseNumberAnnex();
       this.postCode = registrationForm.getPostCode();
       this.city = registrationForm.getCity();
       this.phoneNumber = registrationForm.getPhoneNumber();
       this.email = registrationForm.getEmail();
       this.birthDate = registrationForm.getBirthDate();
       this.bsn = registrationForm.getBsn();
       this.userName = userName;
       this.phoneNumber = password;


    }

    public Customer create() {
        UserFullName userFullName = new UserFullName(firstName, prefix, lastName);
        UserAddress userAddress = new UserAddress(streetName, houseNumber, houseNumberAnnex, postCode, city);
        UserContactDetails userContactDetails = new UserContactDetails(phoneNumber, email);
        UserInlogCredentials userInlogCredentials = new UserInlogCredentials(userName, password);
        UserPersonalDetails userPersonalDetails = new UserPersonalDetails(birthDate, bsn);

        return new Customer(userFullName, userAddress, userContactDetails, userInlogCredentials, userPersonalDetails);
    }

}
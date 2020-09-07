package nl.dagobank.webapp.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Customer extends User {

    public Customer(UserFullName userFullName, UserAddress userAddress, UserContactDetails userContactDetails, UserInlogCredentials userInlogCredentials, LocalDate birthDate, int bsn) {
        super(userFullName.getFirstName(), userFullName.getPrefix(), userFullName.getLastName(), userContactDetails.getPhoneNumber(),
                userAddress.getStreetName(), userAddress.getHouseNumber(), userAddress.getHouseNumberAnnex(), userAddress.getPostCode(), userAddress.getCity(),
                userContactDetails.getEmail(), birthDate, bsn, userInlogCredentials.getUserName(), userInlogCredentials.getPassword());
    }

    public Customer() {

    }
}

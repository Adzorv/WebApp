package nl.dagobank.webapp.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class
Customer extends User {

    public Customer(UserFullName userFullName, UserAddress userAddress, UserContactDetails userContactDetails, UserInlogCredentials userInlogCredentials, UserPersonalDetails userPersonalDetails) {
        super(userFullName, userAddress, userContactDetails, userPersonalDetails, userInlogCredentials);
    }

    public Customer() {
    }


}

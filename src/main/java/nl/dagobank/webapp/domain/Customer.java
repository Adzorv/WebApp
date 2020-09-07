package nl.dagobank.webapp.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Customer extends User {

    public Customer(UserFullName userFullName, UserAddress userAddress, UserContactDetails userContactDetails, UserInlogCredentials userInlogCredentials, LocalDate birthDate, int bsn) {
        super(userFullName, userAddress, userContactDetails, userInlogCredentials, birthDate, bsn);
    }

    public Customer() {
    }
}

package nl.dagobank.webapp.service;

import nl.dagobank.webapp.domain.*;

public interface UserConstructor<T> {
        T create(UserFullName userFullName, UserAddress userAddress, UserContactDetails userContactDetails,  UserInlogCredentials userInlogCredentials,UserPersonalDetails userPersonalDetails);

}

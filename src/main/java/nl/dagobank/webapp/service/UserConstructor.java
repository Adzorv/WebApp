package nl.dagobank.webapp.service;

import nl.dagobank.webapp.domain.*;

public interface UserConstructor<T> {
        T create(FullName fullName, Address address, ContactDetails contactDetails, InlogCredentials inlogCredentials, PersonalDetails personalDetails);

}

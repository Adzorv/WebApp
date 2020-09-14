package nl.dagobank.webapp.domain;

import java.time.LocalDate;

public class PersonalDetails {
    LocalDate birthDate;
    int bsn;

    public PersonalDetails(LocalDate birthDate, int bsn) {
        this.birthDate = birthDate;
        this.bsn = bsn;
    }

    public PersonalDetails() {
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    @Override
    public String toString() {
        return "UserPersonalDetails{" +
                "birthDate=" + birthDate +
                ", bsn=" + bsn +
                '}';
    }
}

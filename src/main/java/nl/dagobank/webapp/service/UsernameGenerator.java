package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsernameGenerator {
    private String userName;

    @Autowired
    public CustomerDao customerDao;

    public UsernameGenerator() {
    }
    /* public UsernameGenerator(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }*/


    public String createUsername(String firstName, String lastName) {
        if (lastName.length() < 3 && firstName.length() < 3) {
            userName = String.format("%s%s%s%s%03d", lastName, "x", firstName, "x", 1);
        } else if (lastName.length() < 3) {
            userName = String.format("%s%s%s%03d", lastName, "x", firstName.substring(0, 3), 1);
        } else if (firstName.length() < 3) {
            userName = String.format("%s%s%s%03d", lastName.substring(0, 3), firstName, "x", 1);
        } else {
            userName = String.format("%s%s%03d", lastName.substring(0, 3), firstName.substring(0, 3), 1);
        }
           while(customerDao.existsByUserName(userName)) {
        userName = String.format("%s%03d", userName.substring(0, 6), (Integer.parseInt(userName.substring(6)) + 1));
         }
        return userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}



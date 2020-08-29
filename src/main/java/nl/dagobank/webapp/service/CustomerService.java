package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        super();
        this.customerDao = customerDao;
    }

    public boolean checkIfBSNIsInDB(int bsn) {
        return customerDao.findByBsn(bsn) != null;
    }

    public boolean checkIfBSNIsCorrect(int inputBSN) {
        if (inputBSN <= 9999999 || inputBSN > 999999999) {
            return false;
        }
        int bsnToCheck = -1 * inputBSN % 10;
        for (int multiplier = 2; inputBSN > 0; multiplier++) {
            int val = (inputBSN /= 10) % 10;
            bsnToCheck += multiplier * val;
        }
        return bsnToCheck != 0 && bsnToCheck % 11 == 0;
    }


}

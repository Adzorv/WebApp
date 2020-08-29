package nl.dagobank.webapp.service;

import nl.dagobank.webapp.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService( CustomerDao customerDao ) {
        super();
        this.customerDao = customerDao;
    }

    public boolean checkIfBSNInDB(int bsn){
        return this.customerDao.findByBsn(bsn);
    }

    public boolean checkifBSNIsCorrect(){
        //fixme: implement the elftoets
        return false;

    }
}

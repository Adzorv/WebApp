package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.BalanceSumPerBusiness;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.Employee;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes( "user")
public class HeadBusinessController {


    @Autowired
    BankAccountService bankAccountService;

    @GetMapping( "overzichtmkb" )
    public String overview( Model model ) {
        if ( model.containsAttribute( "user" )) {
            Employee employee = (Employee) model.getAttribute( "user" );
            if ( employee != null && employee.getRole().equals( "HoofdMKB" )) {

                List<BalanceSumPerBusiness> top10Balance = bankAccountService.getTop10Businesses();

                model.addAttribute( "top10balance", top10Balance );

                return "overviewHeadBusiness";
            } else {
                return "geenToegang";
            }
        } else {
            return "geenToegang";
        }
    }

}

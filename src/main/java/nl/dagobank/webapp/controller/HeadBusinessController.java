package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.BalanceSumPerBusiness;
import nl.dagobank.webapp.dao.BusinessAccountDao;
import nl.dagobank.webapp.dao.SbiAverage;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.Employee;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes( "user" )
public class HeadBusinessController {

    private BankAccountService bankAccountService;
    private ModelAndView modelAndView;
    private Employee employee;
    public static final String NO_ACCESS = "geenToegang", OVERVIEW = "overviewHeadBusiness";


    @Autowired
    public HeadBusinessController( BankAccountService bankAccountService ) {
        this.bankAccountService = bankAccountService;
        modelAndView = new ModelAndView();
    }

    @GetMapping( "overzichtmkb" )
    public ModelAndView overview( Model model ) {
        if ( employeeIsValidated( model ) ) {
            if ( employeeIsHeadMKB() ) {
                addOverviewsToModel();
                modelAndView.setViewName( OVERVIEW );
            } else {
                modelAndView.setViewName( NO_ACCESS );
            }
        } else {
            modelAndView.setViewName( NO_ACCESS );
        }
        return modelAndView;
    }

    private void addOverviewsToModel() {
        modelAndView.addObject( "top10balance", bankAccountService.getTop10Businesses() );
        modelAndView.addObject( "averagePerSector", bankAccountService.getAverageBalancePerSector() );
    }

    private boolean employeeIsHeadMKB() {
        return employee != null && employee.getRole().equals( "HoofdMKB" );
    }

    private boolean employeeIsValidated( Model model ) {
        if ( model.containsAttribute( "user" ) ) {
            employee = (Employee) model.getAttribute( "user" );
            return true;
        }
        return false;
    }
}

package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.domain.Employee;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes( "user" )
public class HeadBusinessController {

    private BankAccountService bankAccountService;
    private TransactionService transactionService;
    private ModelAndView modelAndView;
    private Employee employee;
    public static final String NO_ACCESS = "geenToegang", OVERVIEW = "overviewHeadBusiness";


    @Autowired
    public HeadBusinessController( BankAccountService bankAccountService, TransactionService transactionService ) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        modelAndView = new ModelAndView();
    }

    @GetMapping( "werknemer/{overview}" )
    public ModelAndView overview( Model model, @PathVariable String overview ) {
        if ( userIsLoggedInAndEmployee( model ) ) {
            if ( employeeIsHeadMKB() && overview.equals( "overzichtmkb" ) ) {
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

/*    @GetMapping( "werknemer/{overview}" )
    public String overviewTest( Model model, @PathVariable() String overview ) {
        if ( overview.equals( "overzichtmkb" ) ) {
            addOverviewsToModel();
        }
//        if ( userIsLoggedInAndEmployee( model ) ) {
//            if ( employeeIsHeadMKB() ) {
//                addOverviewsToModel();
//                modelAndView.setViewName( OVERVIEW );
//            } else {
//                modelAndView.setViewName( NO_ACCESS );
//            }
//        } else {
//            modelAndView.setViewName( NO_ACCESS );
//        }
        return OVERVIEW;
    }*/

    private void addOverviewsToModel() {
        modelAndView.addObject( "top10balance", bankAccountService.getTop10Businesses() );
        modelAndView.addObject( "averagePerSector", bankAccountService.getAverageBalancePerSector() );
        modelAndView.addObject( "top10transactions", transactionService.getTop10SumTransactions() );
    }

    private boolean employeeIsHeadMKB() {
        return employee != null && employee.getRole().equals( "HoofdMKB" );
    }

    private boolean userIsLoggedInAndEmployee( Model model ) {
        if ( model.containsAttribute( "user" ) ) {
            try {
                employee = (Employee) model.getAttribute( "user" );
                return true;
            } catch ( ClassCastException cce ) {
                return false;
            }
        }
        return false;
    }
}

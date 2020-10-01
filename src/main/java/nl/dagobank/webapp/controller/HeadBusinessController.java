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
@SessionAttributes( BaseController.USER_SESSION_ATTR )
public class HeadBusinessController extends BaseController {

    private BankAccountService bankAccountService;
    private TransactionService transactionService;
    private ModelAndView modelAndView;
    private Employee employee;
    public static final String
            OVERVIEW_VIEW = "overviewHeadBusiness",
            HEADMKB_ROLE = "HoofdMKB",
            OVERVIEWMKB_PATHVARIABLE = "overzichtmkb",
            TOP10_BALANCE_ATTR = "top10balance",
            SECTORAVERAGE_ATTR = "averagePerSector",
            TOP10_TRANSACTIONS_ATTR = "top10transactions";

    @Autowired
    public HeadBusinessController( BankAccountService bankAccountService, TransactionService transactionService ) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        modelAndView = new ModelAndView();
    }

    @GetMapping( "werknemer/{selectedOverview}" )
    public ModelAndView overviewMKB( Model model, @PathVariable String selectedOverview ) {
        if ( userIsLoggedInAndEmployee( model ) ) {
            setViewAccordingToUserAccess( selectedOverview );
        } else {
            modelAndView.setViewName( NO_ACCESS_VIEW );
        }
        return modelAndView;
    }

    private void setViewAccordingToUserAccess( String selectedOverview ) {
        if ( employeeIsHeadMKB() && selectedOverview.equals( OVERVIEWMKB_PATHVARIABLE ) ) {
            addOverviewsToModel();
            modelAndView.setViewName( OVERVIEW_VIEW );
        } else {
            modelAndView.setViewName( NO_ACCESS_VIEW );
        }
    }

    private void addOverviewsToModel() {
        modelAndView.addObject( TOP10_BALANCE_ATTR, bankAccountService.getTop10Businesses() );
        modelAndView.addObject( SECTORAVERAGE_ATTR, bankAccountService.getAverageBalancePerSector() );
        modelAndView.addObject( TOP10_TRANSACTIONS_ATTR, transactionService.getTop10SumTransactions() );
    }

    private boolean employeeIsHeadMKB() {
        return employee != null && employee.getRole().equals( HEADMKB_ROLE );
    }

    private boolean userIsLoggedInAndEmployee( Model model ) {
        if ( model.containsAttribute( USER_SESSION_ATTR ) ) {
            try {
                employee = (Employee) model.getAttribute( USER_SESSION_ATTR );
                return true;
            } catch ( ClassCastException cce ) {
                return false;
            }
        }
        return false;
    }
}

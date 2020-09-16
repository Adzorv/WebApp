package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.dto.SumTransactionsPerBusiness;
import nl.dagobank.webapp.domain.Employee;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


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

    @GetMapping( "overzichtmkb" )
    public ModelAndView overview( Model model ) {
        if ( userIsLoggedInAndEmployee( model ) ) {
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
        List<SumTransactionsPerBusiness> result = transactionService.getTop10SumTransactions();
        System.out.println(result.size());
        for ( SumTransactionsPerBusiness sumTransactionsPerBusiness : result ) {
            System.out.println(sumTransactionsPerBusiness.getSumTransactions());
            System.out.println(sumTransactionsPerBusiness.getBusinessName());
        }
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

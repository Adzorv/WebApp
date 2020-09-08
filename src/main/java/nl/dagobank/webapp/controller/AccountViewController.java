package nl.dagobank.webapp.controller;
import nl.dagobank.webapp.dao.TransactionDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Transaction;
import nl.dagobank.webapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@Controller
@SessionAttributes( "user" )
public class AccountViewController {

    @Autowired
    TransactionDao transactionDao;  //TODO: Dao niet direct in deze controller aanroepen maar via serviceklasse

    BankAccountService bankAccountService;

    @Autowired
    public AccountViewController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accountView{id}")
    ModelAndView AccountViewHandler(@RequestParam("id") int id) {

        ModelAndView modelAndView = new ModelAndView("transactionOverview");
        BankAccount selectedBankAccount = bankAccountService.getBankAccountById(id);
        modelAndView.addObject("selectedBankAccount", selectedBankAccount);
        List<Transaction> allTransactions = transactionDao.findAllByDebitAccount(selectedBankAccount);
        modelAndView.addObject("transactions", allTransactions);
         //Fixme: show all transactions werkt nog niet goed.

        return modelAndView;

    }
}

package nl.dagobank.webapp.controller;
import nl.dagobank.webapp.dao.TransactionDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.Transaction;
import nl.dagobank.webapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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
    ModelAndView AccountViewHandler(@RequestParam("id") int id, Model model) {
        Customer customer = (Customer)model.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("transactionOverview");
        BankAccount selectedBankAccount = null;
        try {
            selectedBankAccount = bankAccountService.getBankAccountById(id);
        } catch (NoSuchElementException e){
            modelAndView.setViewName("notAuthorized");
        }
        if ((selectedBankAccount != null) && bankAccountService.isCustomerFirstOrSecundairyAccountHolder(customer, selectedBankAccount)){
            getTransactionsAndPrepareViewToShow(modelAndView, selectedBankAccount);
        } else {
            modelAndView.setViewName("notAuthorized");
        }
        return modelAndView;

    }

    private void getTransactionsAndPrepareViewToShow(ModelAndView modelAndView, BankAccount selectedBankAccount) {
        modelAndView.addObject("selectedBankAccount", selectedBankAccount);
//          List<Transaction> allTransactions = transactionDao.findAllByDebitAccount(selectedBankAccount);
        List<Transaction> allTransactions = transactionDao.findAllByDebitAccountOrCreditAccountOrderByDate(selectedBankAccount, selectedBankAccount);
        modelAndView.addObject("transactions", allTransactions);

        List<BigDecimal> amount = new ArrayList<>();
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            if (transaction.getDebitAccount() == selectedBankAccount) {
                bankAccounts.add(transaction.getCreditAccount());
                amount.add(transaction.getAmount().negate());
            } else {
                bankAccounts.add(transaction.getDebitAccount());
                amount.add(transaction.getAmount());
            }
        }
        modelAndView.addObject("BankAccounts", bankAccounts);
        modelAndView.addObject("amounts", amount);
    }
}

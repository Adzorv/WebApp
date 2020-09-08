package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.dao.TransactionDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes( "user" )
public class AccountViewController {


    BankAccountDao bankAccountDao;

    @Autowired
    TransactionDao transactionDao;
    HttpSession session;

    @Autowired
    AccountViewController(BankAccountDao bankAccountDao, HttpSession session){
        this.bankAccountDao = bankAccountDao;
        this.session = session;
    }


    @GetMapping("/accountView{id}")
    ModelAndView AccountViewHandler(@RequestParam("id") int id){
        ModelAndView modelAndView = new ModelAndView("transactionOverview");
        Optional<BankAccount> bankAccountOptional = bankAccountDao.findById(id);
        if (bankAccountOptional.isPresent()) {
            BankAccount bankAccount = bankAccountOptional.get();
            modelAndView.addObject("selectedBankAccount", bankAccount);
            List<Transaction> allTransactions = transactionDao.findAllByDebitAccount(bankAccount);
            modelAndView.addObject("transactions", allTransactions);
            System.out.println(allTransactions);
//            Fixme: show all transactions werkt nog niet goed.
        }
        return modelAndView;

        // Sebastian's oude code.

//        ArrayList<Integer> convertedId = new ArrayList<>();
//        convertedId.add(id);
//        Iterable<BankAccount> selectedAccount = bankAccountDao.findAllById(convertedId);

//        session.setAttribute("selectedBankAccount", selectedAccount.iterator().next());
//        modelAndView.addObject("selectedBankAccount", session.getAttribute("selectedBankAccount"));
//        List<Transaction> allTransactions = transactionDao.findAllByDebitAccount(debitAccount);
//        model.addAttribute("transactions", allTransactions);
//        return modelAndView;
    }
}

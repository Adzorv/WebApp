package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.TransferForm;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.TransferService;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class TransferController {

    public TransferController() {
        super();
    }

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    TransferService transferService;

    @GetMapping("/transfer{id}")
    public ModelAndView transactionHandler(@RequestParam("id") int id, Model model) {
        BankAccount selectedBankAccount = bankAccountService.getBankAccountById(id);
        ModelAndView modelAndView = new ModelAndView("transfer");
        modelAndView.addObject("selectedBankAccount", selectedBankAccount);
        modelAndView.addObject("TransferForm", new TransferForm());
        return modelAndView;
    }

    @PostMapping("/executeTransfer{id}")
    public String executeTransferHandler(@RequestParam("id") int id,
                                         @ModelAttribute TransferForm transferForm) {
        BankAccount selectedBankAccount = bankAccountService.getBankAccountById(id);
        BigDecimal amount = transferForm.getAmount();
        transferService.getFundsFromSendingAccount(amount, selectedBankAccount);
        Iban iban = Iban.valueOf(transferForm.getIBAN());
        BankAccount recievingBankAccount = bankAccountService.findBankAccountByIban(iban);
        transferService.putFundsInReceivingAccount(recievingBankAccount, amount);
        return "executeTransfer";
    }

}
package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.AjaxIbanCheckResponse;
import nl.dagobank.webapp.backingbeans.TransferForm;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class TransactionController {

    public TransactionController() {
        super();
    }

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    TransactionService transactionService;


    @GetMapping("checkIban")
    @ResponseBody
    public AjaxIbanCheckResponse getBankAccount( String iban, String fullname) {
        BankAccount bankAccount = bankAccountService.findBankAccountByIban(iban);
        AjaxIbanCheckResponse response = new AjaxIbanCheckResponse();
        response.setIbanCorrect( false );
        if (bankAccount == null) {
            response.setMsg( "Onjuiste/niet-bestaande IBAN!" );
            return response;
        }
        StringBuilder fullName = new StringBuilder();
        fullName.append( bankAccount.getAccountHolder().getFullName().getFirstName() )
                .append( " " )
                .append( bankAccount.getAccountHolder().getFullName().getLastName() );
        if(!fullName.toString().equals(fullname)){
            response.setMsg( "IBAN klopt niet met de naam" );
            return response;
        }
        response.setMsg( "" );
        return response;
    }

    @GetMapping("/transaction")
    public ModelAndView transactionHandler(@RequestParam("id") int id, Model model) {
        BankAccount selectedBankAccount = bankAccountService.getBankAccountById(id);
        ModelAndView modelAndView = new ModelAndView("transaction");
        modelAndView.addObject("selectedBankAccount", selectedBankAccount);
        modelAndView.addObject("TransferForm", new TransferForm());
        return modelAndView;
    }

    @PostMapping("/executeTransfer")
    public String executeTransferHandler(@RequestParam("id") int id,
                                         @ModelAttribute TransferForm transferForm) {
        BankAccount senderBankAccount = bankAccountService.getBankAccountById(id);
        BankAccount receivingBankAccount = bankAccountService.findBankAccountByIban(transferForm.getIBAN());
        BigDecimal amount = transferForm.getAmount();
        if (transactionService.performTransaction(senderBankAccount, receivingBankAccount, amount, transferForm.getDescription())) {
            return "redirect:/accountView?id=" + id;
        } else {
            return "redirect:/transactionError?id=" + id;
        }
    }

    @RequestMapping("/transactionError")
    public String getTransferErrorPage(Model model) {
        model.addAttribute("errorMessage", "ERROR: We konden uw verzoek niet verwerken");
        return "transactionError";

    }
}
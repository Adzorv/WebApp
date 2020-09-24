package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.AjaxIbanCheckResponse;
import nl.dagobank.webapp.backingbeans.TransferForm;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.service.BankAccountService;
import nl.dagobank.webapp.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class TransferController {

    public TransferController() {
        super();
    }

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    TransferService transferService;


    @GetMapping("checkIban")
    @ResponseBody
    public AjaxIbanCheckResponse getBankAccount( String iban, String fullname ) {

        BankAccount bankAccount = bankAccountService.findBankAccountByIban(iban);
        AjaxIbanCheckResponse response = new AjaxIbanCheckResponse();
        response.setIbanCorrect( false );
        if (bankAccount == null) {
            response.setMsg( "Wrong/Not existing IBAN!" );
            return response;
        }
        StringBuilder fullName = new StringBuilder();
        fullName.append( bankAccount.getAccountHolder().getFullName().getFirstName() )
                .append( " " )
                .append( bankAccount.getAccountHolder().getFullName().getLastName() );

        if(!fullName.toString().equals(fullname)){
            response.setMsg( "IBAN doesn't match receiver full name!" );
            return response;
        }
        response.setMsg( "" );
        return response;
    }

    @GetMapping("/transfer")
    public ModelAndView transactionHandler(@RequestParam("id") int id, Model model) {
        BankAccount selectedBankAccount = bankAccountService.getBankAccountById(id);
        ModelAndView modelAndView = new ModelAndView("transfer");
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
        if (transferService.performTransaction(senderBankAccount, receivingBankAccount, amount, transferForm.getDescription())) {
            return "redirect:/accountView?id=" + id;
        } else {
            return "redirect:/transferError?id=" + id;
        }
    }

    @RequestMapping("/transferError")
    public String getTransferErrorPage(Model model) {
        model.addAttribute("errorMessage", "ERROR: Insufficient fund to perform this operation!");
        return "transferError";

    }
}
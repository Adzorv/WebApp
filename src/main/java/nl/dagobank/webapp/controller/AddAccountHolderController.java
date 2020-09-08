package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.AddAdditionalBankAccountHolderForm;
import nl.dagobank.webapp.domain.BankAccountHolderToken;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.BankAccountHolderTokenService;
import nl.dagobank.webapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes( "user" )
public class AddAccountHolderController {

    private CustomerService customerService;
    private BankAccountHolderTokenService bankAccountHolderTokenService;

    HttpSession session;

    @Autowired
    public AddAccountHolderController(CustomerService customerService, HttpSession session, BankAccountHolderTokenService bankAccountHolderTokenService){
        super();
        this.customerService = customerService;
        this.session = session;
        this.bankAccountHolderTokenService = bankAccountHolderTokenService;
    }

    @GetMapping("/addAccountHolder")
    String addAccountHolderHandler(Model model) {
        model.addAttribute("error", "");
        model.addAttribute("selectedBankAccount", session.getAttribute("selectedBankAccount"));
        return "addAccountHolder";
    }

    @PostMapping("addAccountHolder")
    ModelAndView sendAddAccountHolderHandler(@ModelAttribute AddAdditionalBankAccountHolderForm addAdditionalBankAccountHolderForm, Model model) {
        Customer user = (Customer)model.getAttribute( "user" );
        PrivateAccount selectedAccount = (PrivateAccount) session.getAttribute("selectedBankAccount");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("selectedBankAccount", selectedAccount);

        String codeFromForm = addAdditionalBankAccountHolderForm.getConnectionCode();
        String userNameFromForm = addAdditionalBankAccountHolderForm.getLoginNameAdditionalAccountHolder();

        if (customerService.isRegisteredUserName(userNameFromForm) && !userNameFromForm.equals(user.getUserName())){
            Customer accountHolderToAdd = customerService.getCustomerByUserName(userNameFromForm);
            BankAccountHolderToken bankAccountHolderToken = new BankAccountHolderToken(accountHolderToAdd, codeFromForm, selectedAccount);
            bankAccountHolderTokenService.saveBankAccountHolderToken(bankAccountHolderToken);
            modelAndView.setViewName("redirect:/bankAccountHolderTokenSuccess");
            session.setAttribute("bankAccountHolderToken", bankAccountHolderToken);
            //modelAndView.addObject("bankAccountHolderToken", bankAccountHolderToken);
        } else {
            modelAndView.setViewName("addAccountHolder");
            modelAndView.addObject("error", "Extra Rekeninghouder moet een bestaande klant login naam zijn, en mag niet je eigen naam zijn!");
        }
    return modelAndView;
    }
}

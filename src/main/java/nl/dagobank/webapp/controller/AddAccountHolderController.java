package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.AddAdditionalBankAccountHolderForm;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.service.CustomerService;
import org.dom4j.rule.Mode;
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

    HttpSession session;

    @Autowired
    public AddAccountHolderController(CustomerService customerService, HttpSession session){
        super();
        this.customerService = customerService;
        this.session = session;
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
        ModelAndView modelAndView = new ModelAndView("");

        String codeFromForm = addAdditionalBankAccountHolderForm.getConnectionCode();
        String userNameFromForm = addAdditionalBankAccountHolderForm.getLoginNameAdditionalAccountHolder();
        //String userNameOfCurrentUser = ((Customer)model.getAttribute("user")).getUserName();
        System.out.println("current user:");
        System.out.println(user.toString());
        System.out.println("current Bank account");
        System.out.println(selectedAccount);
        if (!customerService.isRegisteredUserName(userNameFromForm)){
            modelAndView.setViewName("addAccountHolder");
            modelAndView.addObject("error", "Extra Rekeninghouder moet een bestaande klant login naam zijn!");
            modelAndView.addObject("selectedBankAccount", selectedAccount);
        } else {
            modelAndView.setViewName("addAccountHolderObjectSucess");
        }
    return modelAndView;
    }
}

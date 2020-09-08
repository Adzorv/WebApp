package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.TestForm;
import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.PrivateAccount;
import nl.dagobank.webapp.domain.User;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes( "user" )
public class OverviewController {

    @Autowired
    private BankAccountDao bankAccountDao;


    @GetMapping("overview")
    public String overview(Model model) {
        Customer user = (Customer)model.getAttribute( "user" );
        List<BankAccount> bankAccountsOfUser = bankAccountDao.findAllByAccountHolder(user);
        model.addAttribute("bankAccounts", bankAccountsOfUser);
        return "overview";
    }
}

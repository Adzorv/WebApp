package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@SessionAttributes( "user" )
public class AccountViewController {

    BankAccountDao bankAccountDao;
    HttpSession session;

    @Autowired
    AccountViewController(BankAccountDao bankAccountDao, HttpSession session){
        this.bankAccountDao = bankAccountDao;
        this.session = session;
    }


    @GetMapping("/accountView{id}")
    ModelAndView AccountViewHandler(@RequestParam("id") int id){
        ArrayList<Integer> convertedId = new ArrayList<>();
        convertedId.add(id);
        Iterable<BankAccount> selectedAccount = bankAccountDao.findAllById(convertedId);
        ModelAndView modelAndView = new ModelAndView("accountView");
        session.setAttribute("selectedBankAccount", selectedAccount.iterator().next());
        modelAndView.addObject("selectedBankAccount", session.getAttribute("selectedBankAccount"));
        return modelAndView;
    }
}

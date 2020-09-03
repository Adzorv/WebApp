package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.BankAccountDao;
import nl.dagobank.webapp.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class AccountViewController {

    @Autowired
    BankAccountDao bankAccountDao;

    @GetMapping("/accountView{id}")
    ModelAndView AccountViewHandler(@RequestParam("id") int id){
        ArrayList<Integer> convertedId = new ArrayList<>();
        convertedId.add(id);
        Iterable<BankAccount> selectedAccount = bankAccountDao.findAllById(convertedId);
        String accountName = selectedAccount.iterator().next().getAccountName();
        System.out.println(accountName);
        ModelAndView modelAndView = new ModelAndView("accountView");
        modelAndView.addObject("bankAccountName", accountName);

        return modelAndView;
    }
}

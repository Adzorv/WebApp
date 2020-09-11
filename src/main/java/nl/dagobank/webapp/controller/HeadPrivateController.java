package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.dao.PrivateAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class HeadPrivateController {


    @Autowired
    PrivateAccountDao privateAccountDao;

    @GetMapping( "overzichtparticulier" )
    public ModelAndView privateOverview(Model model) {
        ModelAndView modelAndView = new ModelAndView("overviewHeadPrivate");
        model.addAttribute("sumBalanceList", privateAccountDao.getSumBalancePerPrivateAccount(PageRequest.of(1,10)));
        return modelAndView;
    }
}

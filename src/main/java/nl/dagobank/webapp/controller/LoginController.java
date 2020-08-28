package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.TestForm;
import nl.dagobank.webapp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    public LoginController() { super();
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView( "login" );
        mav.addObject( "testform", new TestForm() );
        mav.addObject( "customer", new Customer() );
        return mav;

    }

    @PostMapping("login")
    public ModelAndView welcomeHandler(
//            @RequestParam( name = "input_id" ) String id,
//            @RequestParam( name = "input_name" ) String naam,
//            @RequestParam( name = "input_date" ) String date,
            @ModelAttribute TestForm testform

    ) {
       ModelAndView mav = new ModelAndView( "login_success" );
        System.out.println(testform.getDate());
        Date date = testform.getDate();
        System.out.println(date);
//        System.out.println( "id = [" + id + "], naam = [" + naam + "], date = [" + date + "]" );
//       mav.addObject( "testform_backingbean", new TestForm( id, naam, date ) )
       return mav;
    }
}

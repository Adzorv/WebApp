package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.TestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    public LoginController() { super();
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView( "login" );
        mav.addObject( "testform", new TestForm() );
        return mav;

    }

    @PostMapping("login")
    public ModelAndView welcomeHandler(
            @RequestParam( name = "input_id" ) String id,
            @RequestParam( name = "input_name" ) String naam,
            @RequestParam( name = "input_date" ) String date

    ) {
       ModelAndView mav = new ModelAndView( "login_success" );
        System.out.println( "id = [" + id + "], naam = [" + naam + "], date = [" + date + "]" );
//       mav.addObject( "testform_backingbean", new TestForm( id, naam, date ) )
       return mav;
    }
}

package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.backingbeans.Username;
import nl.dagobank.webapp.backingbeans.UsernameResponse;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.service.LoginValidatorCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes( "user" )
public class LoginController {

    private static final String POSTLOGIN_VIEW = "redirect:/overview", LOGIN_VIEW = "login";
    private static final Logger LOG = LogManager.getLogger( LoginController.class );
    private static final String USERNAME_ERROR = "Gebruikersnaam bestaat niet";

    private CustomerService customerService;

    @Autowired
    public LoginController( CustomerService customerService ) {
        this.customerService = customerService;
    }

    @GetMapping( "login" )
    public ModelAndView login( Model model ) {
        model.addAttribute( "loginform", new LoginForm() );
        return new ModelAndView( "login" );
    }

    @PostMapping( "login" )
    public ModelAndView loginAttempt( Model model, @ModelAttribute LoginForm loginform ) {
        LoginValidatorCustomer lv = customerService.validateCredentials( loginform );
        ModelAndView mav = new ModelAndView();
        if ( lv.isUserValidated() && lv.isPasswordValidated() ) {
            model.addAttribute( "user", lv.getCustomer() );
            mav.setViewName( POSTLOGIN_VIEW );
        } else {
            model.addAttribute( "loginform", loginform );
            mav.setViewName( LOGIN_VIEW );
        }
        LOG.info( lv.getLogMessage() );
        return mav;
    }

    @PostMapping( "/usernameCheck" )
    public @ResponseBody
    UsernameResponse checkUsername( @RequestBody Username username ) {
        String name = username.getUsername();
        UsernameResponse usernameResponse = new UsernameResponse( username.getUsername() );
        boolean customer = customerService.isRegisteredUserName( name );
        if ( !customer ) {
            usernameResponse.setError( USERNAME_ERROR );
        }
        return usernameResponse;
    }
}

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


@Controller
@SessionAttributes( "user" )
public class LoginController {

    public static final String POSTLOGIN_VIEW = "redirect:/overview", LOGIN_VIEW = "login";
    public static final Logger LOG = LogManager.getLogger( LoginController.class );

    @ModelAttribute( "loginform" )
    public LoginForm getLoginForm() {
        return loginForm;
    }

    private LoginForm loginForm = new LoginForm();
    @Autowired
    private CustomerService customerService;

    @GetMapping( "login" )
    public String login() {
        return "login";
    }

    @PostMapping( "login" )
    public String loginAttempt( Model model, LoginForm loginForm ) {
        LoginValidatorCustomer lv = customerService.validateCredentials( loginForm );
        String view;
        if ( lv.isUserValidated() && lv.isPasswordValidated() ) {
            model.addAttribute( "user", lv.getCustomer() );
            view = POSTLOGIN_VIEW;
        } else {
            model.addAttribute( "loginform", loginForm );
            view = LOGIN_VIEW;
        }
        LOG.info( lv.getLogMessage() );
        return view;
    }


    @PostMapping( "/usernameCheck" )
    public @ResponseBody
    UsernameResponse checkUsername( @RequestBody Username username ) {
        String name = username.getUsername();
        UsernameResponse usernameResponse = new UsernameResponse( username.getUsername() );
        boolean customer = customerService.isRegisteredUserName( name );
        if ( !customer ) {
            usernameResponse.setError( "Gebruikersnaam bestaat niet" );
        }
        return usernameResponse;
    }
}

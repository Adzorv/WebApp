package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.service.LoginValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@SessionAttributes( "user" )
public class LoginController {

    private static final String POSTLOGIN_VIEW = "redirect:/overview", LOGIN_VIEW = "login";
    private static final Logger LOG = LogManager.getLogger( LoginController.class );

    @ModelAttribute( "loginform" )
    public LoginForm getLoginForm() {
        return loginForm;
    }

    @Autowired
    private CustomerDao customerDao;
    private LoginForm loginForm = new LoginForm();
    @Autowired
    private CustomerService customerService;

    @GetMapping( "login" )
    public String login( Model model ) {
        model.addAttribute( "loginform", new LoginForm() );
        return "login";
    }

    @PostMapping( "login" )
    public String loginAttempt( Model model, LoginForm loginForm ) {
        LoginValidation lv = customerService.validateCredentials( loginForm );
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

    @GetMapping( "vuldatabase" )
    public RedirectView fillDB() {
        Customer customer = new Customer();
        customer.setFirstName( "Jan" );
        customer.setPrefix( "van de" );
        customer.setLastName( "Jansen" );
        customer.setStreetName( "Overtoom" );
        customer.setHouseNumber( 23 );
        customer.setHouseNumberAnnex( "2 hoog" );
        customer.setPostCode( "1014AA" );
        customer.setCity( "Amsterdam" );
        customer.setPhoneNumber( "0612345678" );
        //customer.setBirthDate('2010/02/11');
        customer.setEmail( "janj@gmail.com" );
        customer.setBsn( 135076569 );
        customer.setPassword( "test" );
        customer.setUserName( "test" );
        customerDao.save( customer );
        LOG.info( "Database gevuld met test test gebruiker" );
        return new RedirectView( "login" );
    }
}

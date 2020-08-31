package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;


@Controller
@SessionAttributes( "user" )
public class LoginController {

    private CustomerDao customerDao;
    private static final String USERNAMEERROR = "Deze gebruikersnaam bestaat niet";

    @Autowired
    public LoginController( CustomerDao customerDao ) {
        super();
        this.customerDao = customerDao;
    }

    @GetMapping( "login" )
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView( "login" );
        mav.addObject( "loginform", new LoginForm() );
        return mav;
    }

    @PostMapping( "login" )
    public ModelAndView welcomeHandler( @ModelAttribute LoginForm loginForm ) {
        ModelAndView mav = new ModelAndView();
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        Optional<Customer> customerOptional = customerDao.findByUserName( username );

        if ( customerOptional.isPresent() ) {
            Customer customer = customerOptional.get();
            System.out.println( "User gevonden!" );

            if ( customer.getPassword().equals( password ) ) {
                mav.addObject( "user", customer );
                mav.setViewName( "login_success" );
            } else {
                loginForm.setPasswordError( "verkeerd wachtwoord" );
                mav.addObject( "loginform", loginForm );
                mav.setViewName( "login" );
                System.out.println( "Verkeerd wachtwoord ingevoerd!" );
            }

        } else {
            loginForm.setUsernameError( USERNAMEERROR );
            mav.addObject( "user", null );
            mav.addObject( "loginform", loginForm );
            mav.setViewName( "login" );
            System.out.println( "Geen user gevonden!" );
        }

        return mav;
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
        System.out.println( "Database gevuld met test test gebruiker" );
        return new RedirectView( "login" );
    }
}

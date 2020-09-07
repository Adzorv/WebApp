package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.LoginForm;
import nl.dagobank.webapp.dao.EmployeeDao;
import nl.dagobank.webapp.domain.Employee;
import nl.dagobank.webapp.service.EmployeeService;
import nl.dagobank.webapp.service.LoginValidatorEmployee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

@Controller
@SessionAttributes( "user" )
public class LoginEmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeDao employeeDao;
    private static final Logger LOG = LogManager.getLogger( LoginEmployeeController.class );


    @GetMapping( "werknemer" )
    public String loginEmployee( Model model ) {
        model.addAttribute( "loginform", new LoginForm() );
        return "loginEmployee";
    }

    @PostMapping( "werknemer" )
    public String loginAttemptEmployee( Model model, @ModelAttribute LoginForm loginForm ) {
        LoginValidatorEmployee lv = employeeService.validateCredentials( loginForm );
        model.addAttribute( "loginform", loginForm );
        LOG.info( lv.getLogMessage() );
        if ( lv.isLoginValidated() ) {
            model.addAttribute( "user", lv.getEmployee() );
            return "redirect:/overzicht";
        } else {
            return "loginEmployee";
        }
    }
}

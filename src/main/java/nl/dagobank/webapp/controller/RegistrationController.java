package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.dao.CustomerDao;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegistrationController {
    private CustomerService customerService;
    private UsernameGenerator usernameGenerator;
    private PasswordGenerator passwordGenerator;
    private CustomerDao customerDao;

    private UserFactory userFactory;

    @Autowired
    public RegistrationController( CustomerService customerService, UsernameGenerator usernameGenerator, PasswordGenerator passwordGenerator, CustomerDao customerDao ) {
        this.customerService = customerService;
        this.usernameGenerator = usernameGenerator;
        this.passwordGenerator = passwordGenerator;
        this.customerDao = customerDao;
    }

    @GetMapping("registration")
    public ModelAndView registrationPageHandle() {
        ModelAndView registrationPage = new ModelAndView("registration");
        registrationPage.addObject("registrationForm", new RegistrationForm());
        return registrationPage;
    }

    @PostMapping("registration")
    public ModelAndView registrationHandler(@ModelAttribute RegistrationForm registrationForm, Model model) {
        String userName = usernameGenerator.createUsername(registrationForm.getFirstName(), registrationForm.getLastName());
        String password = passwordGenerator.generate(10);
        if (customerService.isBSNValid(registrationForm.getBsn())) {
            return showRegistrationSuccessPage(registrationForm, userName, password, model);
        } else {
            return showRegistrationFailedPage(registrationForm, model);
        }
    }


    private ModelAndView showRegistrationSuccessPage(@ModelAttribute RegistrationForm registrationForm, String
            userName, String password, Model model) {
        ModelAndView registrationSuccess = new ModelAndView("registration_success");
        userFactory = new UserFactory(registrationForm, userName, password);
        Customer customer = userFactory.createCustomer();
        customerService.saveCustomer(customer);
        model.addAttribute("user", customer);
        return registrationSuccess;
    }


    private ModelAndView showRegistrationFailedPage(RegistrationForm registrationForm, Model model) {
        ModelAndView registrationFailedView = new ModelAndView("registration");
        if (customerService.checkIfBSNIsInDB(registrationForm.getBsn())) {
            model.addAttribute("bsnError", "BSN bestaat al!");
            return registrationFailedView;
        } else if (!customerService.checkIfBSNIsCorrect(registrationForm.getBsn())) {
            model.addAttribute("bsnError", "Foute BSN code");
            return registrationFailedView;
        } else {
            model.addAttribute("bsnError", "");
            return registrationFailedView;
        }
    }

    @GetMapping("registration_failed")
    public String registrationFailedHandler() {
        return "registration_failed";
    }

}


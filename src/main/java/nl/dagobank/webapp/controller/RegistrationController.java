package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.CustomerFactory;
import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.service.PasswordGenerator;
import nl.dagobank.webapp.service.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegistrationController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UsernameGenerator usernameGenerator;
    @Autowired
    PasswordGenerator passwordGenerator;

    CustomerFactory customerFactory;


    @GetMapping("registration")
    public ModelAndView registrationPageHandle() {
        ModelAndView registrationPage = new ModelAndView("registration");
        registrationPage.addObject("registrationForm", new RegistrationForm());
        return registrationPage;
    }

    @PostMapping("register")
    public ModelAndView registrationHandler(@ModelAttribute RegistrationForm registrationForm, Model model) {
        String userName = usernameGenerator.createUsername(registrationForm.getFirstName(), registrationForm.getLastName());
        String password = passwordGenerator.generate(10);
        if (customerService.isBSNValid(registrationForm.getBsn())) {
            return showRegistrationSuccessPage(registrationForm, userName, password, model);
        } else {
            return showRegistrationFailedPage(registrationForm, model);
        }
    }

    private ModelAndView showRegistrationSuccessPage (@ModelAttribute RegistrationForm registrationForm, String
        userName, String password, Model model){
            ModelAndView registrationSuccess = new ModelAndView("registration_success");
            customerFactory = new CustomerFactory(registrationForm, userName, password);
            Customer customer = customerFactory.createCustomer();
            customerService.saveCustomer(customer);
            model.addAttribute("user", customer);
            return registrationSuccess;
        }

    private ModelAndView showRegistrationFailedPage(RegistrationForm registrationForm, Model model) {
        ModelAndView registrationFailedView = new ModelAndView("registration_failed");
        if(customerService.checkIfBSNIsInDB(registrationForm.getBsn())){
            model.addAttribute("bsnValue", "BSNFoundInDB");
                return  registrationFailedView;
            }
            else if (!customerService.checkIfBSNIsCorrect(registrationForm.getBsn())){
                model.addAttribute("bsnValue", "BSNInvalid");
                return registrationFailedView;
            }
            else {
                model.addAttribute("bsnValue", "default");
                return registrationFailedView;
        }
    }

        @GetMapping("registration_failed")
        public String registrationFailedHandler () {
            return "registration_failed";
        }

    }


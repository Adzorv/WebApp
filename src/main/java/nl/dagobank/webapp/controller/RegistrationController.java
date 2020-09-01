package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.RegistrationForm;
import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.service.CustomerService;
import nl.dagobank.webapp.service.PasswordGenerator;
import nl.dagobank.webapp.service.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegistrationController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UsernameGenerator usernameGenerator;
    @Autowired
    PasswordGenerator passwordGenerator;


    @GetMapping("registration")
    public ModelAndView registrationPageHandle() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("registrationForm", new RegistrationForm());
        return mav;

    }

    @PostMapping("register")
    public ModelAndView registrationHandler(@ModelAttribute RegistrationForm registrationForm, Model model) {
        ModelAndView mav = new ModelAndView("registration_success");
        String userName = usernameGenerator.createUsername(registrationForm.getFirstName(), registrationForm.getLastName());
        String password = passwordGenerator.generate(10);

        if (customerService.isBSNValid(registrationForm.getBsn())) {
            Customer customer = new Customer(registrationForm.getFirstName(),
                    registrationForm.getPrefix(),
                    registrationForm.getLastName(),
                    registrationForm.getPhoneNumber(),
                    registrationForm.getStreetName(),
                    registrationForm.getHouseNumber(),
                    registrationForm.getHouseNumberAnnex(),
                    registrationForm.getPostCode(),
                    registrationForm.getCity(),
                    registrationForm.getEmail(),
                    registrationForm.getBirthDate(),
                    registrationForm.getBsn(), userName, password);
            customerService.saveCustomer(customer);
            model.addAttribute("user", customer);
            return mav;
        } else {
            ModelAndView mavFail = new ModelAndView("registration_failed");
            if (customerService.checkIfBSNIsInDB(registrationForm.getBsn())) {
                model.addAttribute("bsnValue", "BSNFoundInDB");
                return mavFail;

            } else if (!customerService.checkIfBSNIsCorrect(registrationForm.getBsn())) {
                model.addAttribute("bsnValue", "BSNInvalid");
                return mavFail;

            } else {
                model.addAttribute("bsnValue", "default");
                return mavFail;
            }


        }
    }

    @GetMapping("registration_failed")
    public String registrationFailedHandler() {
        return "registration_failed";
    }

}


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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegistrationController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UsernameGenerator usernameGenerator;
    @Autowired
    PasswordGenerator passwordGenerator;

   /* @GetMapping("registration")
    public String registrationpageHandler() {
        return "registration";
    }*/

    @GetMapping("registration")
    public ModelAndView registrationPageHandle() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("registrationForm", new RegistrationForm());
        return mav;

    }


    @PostMapping("register")
    public ModelAndView registrationHandler(@ModelAttribute RegistrationForm registrationForm) {
        ModelAndView mav = new ModelAndView("registration_success");
        String userName = usernameGenerator.createUsername(registrationForm.getFirstName(), registrationForm.getLastName());
        String password = passwordGenerator.generate(10);
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
        return mav;
    }

   /* @PostMapping("register")
    public String registrationHandler(
            @RequestParam(name = "first_name") String firstName,
            @RequestParam(name = "prefix") String prefix,
            @RequestParam(name = "last_name") String lastName,
            @RequestParam(name = "phone_number") String phoneNumber,
            @RequestParam(name = "street_name") String streetName,
            @RequestParam(name = "house_number") int houseNumber,
            @RequestParam(name = "house_number_annex") String houseNumberAnnex,
            @RequestParam(name = "post_code") String postCode,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "birth_date") Date birthDate,
            @RequestParam(name = "bsn") int bsn,
            Model model) throws ParseException {
        String userName = usernameGenerator.createUsername(firstName, lastName);
        String password = passwordGenerator.generate(10);
        //Date dateOfBirth  = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
        Customer customer = new Customer(firstName, prefix, lastName, phoneNumber, streetName, houseNumber,
                houseNumberAnnex, postCode, city, email, birthDate, bsn, userName, password);
        customerService.saveCustomer(customer);

        model.addAttribute("Voornaam", firstName);
        model.addAttribute("Tussenvoegsel", prefix);
        model.addAttribute("Achternaam", lastName);
        model.addAttribute("Telefoonnummer", phoneNumber);
        model.addAttribute("Straatnaam", streetName);
        model.addAttribute("Huisnummer", houseNumber);
        model.addAttribute("Toevoeging", houseNumberAnnex);
        model.addAttribute("Postcode", postCode);
        model.addAttribute("city", city);
        model.addAttribute("Email", email);
        model.addAttribute("Geboortedatum", birthDate);
        model.addAttribute("BSN", bsn);
        return "registration_success";

    }
*/
    @GetMapping("registration_failed")
    public String registrationFailedHandler() {
        return "registration_failed";
    }

    @GetMapping("switch-case")
    public String switchBSNValue(
            @RequestParam(name = "bsn") int bsn,
            Model model) {
        model.addAttribute(bsn);
        if (customerService.checkIfBSNIsInDB(bsn)) {
            return "BSNFoundInDB";
        }
        if (!customerService.checkIfBSNIsCorrect(bsn))
            return "BSNInvalid";
        else {
            return "default";
        }
    }
}


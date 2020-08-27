package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class RegistrationController {

    public RegistrationController() {
        super();
    }

    @GetMapping("registration")
    public String registrationpageHandler(){
        return "registration";
    }

    @PostMapping("register")
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
                             Model model) {
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
}


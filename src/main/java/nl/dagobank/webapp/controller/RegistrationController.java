package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class RegistrationController {
    public RegistrationController() {
        super();
    }

    @PostMapping("register")
    public String register(@RequestParam("firstName") String firstName,
                             @RequestParam("prefix") String prefix,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("streetName") String streetName,
                             @RequestParam("houseNumber") int houseNumber,
                             @RequestParam("houseNumberAnnex") String houseNumberAnnex,
                             @RequestParam("postCode") String postCode,
                             @RequestParam("city") String city,
                             @RequestParam("email") String email,
                             @RequestParam("birthDate") Date birthDate,
                             @RequestParam("bsn") int bsn,
                             Model model) {
        model.addAttribute("Voornaam", firstName);
        model.addAttribute("Tussenvoegsel", prefix);
        model.addAttribute("Achternaam", lastName);
        model.addAttribute("Telefoonnummer", phoneNumber);
        model.addAttribute("Straatnaam", streetName);
        model.addAttribute("Huisnummer", houseNumber);
        model.addAttribute("Toevoeging", houseNumberAnnex);
        model.addAttribute("Postcode", postCode);
        model.addAttribute("Email", email);
        model.addAttribute("Geboortedatum", birthDate);
        model.addAttribute("BSN", bsn);
        return "registration_success";

    }
}


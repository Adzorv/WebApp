package nl.dagobank.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    public LoginController() { super();
    }

    @GetMapping("login")
    public String login() {
        System.out.println( "LoginController.login" );;
        return "login.html";
    }
}

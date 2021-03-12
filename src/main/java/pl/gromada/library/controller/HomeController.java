package pl.gromada.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "registerForm";
    }

}

package pl.gromada.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

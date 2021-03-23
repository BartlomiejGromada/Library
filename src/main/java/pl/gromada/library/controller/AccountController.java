package pl.gromada.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gromada.library.model.Account;
import pl.gromada.library.service.AccountService;

import javax.validation.Valid;

@Controller
public class AccountController {

    private AccountService accountService;

    @Autowired
    public void setUserService(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new Account());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@Valid @ModelAttribute Account account, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            return "registerForm";
        } else {
            accountService.addWithDefaultRole(account);
            model.addAttribute("successRegister", true);
            return "/loginForm";
        }
    }

}

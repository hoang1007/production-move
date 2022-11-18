package vnu.uet.prodmove.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.domain.Account;

@RestController
@RequestMapping("/account")
public class AccountController {
    @PostMapping("/login")
    public String login(Account account) {
        return account.getUsername();
    }

    @PostMapping("/register")
    public String register(Account account) {
        return account.getUsername();
    }
}

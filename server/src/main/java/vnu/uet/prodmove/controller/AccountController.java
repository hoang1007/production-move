package vnu.uet.prodmove.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.exception.CustomException;
import vnu.uet.prodmove.services.AccountService;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @PostMapping(ApiConfig.SIGN_UP)
    public ResponseEntity<String> signup(@RequestBody AccountModel accountModel) throws CustomException {
        accountService.signup(accountModel);
        return ResponseEntity.ok().body("Register successfully!");
    }

    @PostMapping(ApiConfig.LOG_IN)
    public ResponseEntity<Map<String, String>> login(@RequestBody AccountModel accountModel) throws CustomException {
        String accessToken = accountService.login(accountModel);
        return ResponseEntity.ok().body(Map.of("accessToken", accessToken));

    }

}

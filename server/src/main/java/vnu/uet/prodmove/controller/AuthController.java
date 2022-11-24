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
import vnu.uet.prodmove.services.IAuthService;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private IAuthService authService;
    
    @PostMapping(ApiConfig.SIGN_UP)
    public ResponseEntity<String> signup(@RequestBody AccountModel accountModel) throws CustomException {
        authService.signup(accountModel);
        return ResponseEntity.ok().body("Register successfully!");
    }

    @PostMapping(ApiConfig.LOG_IN)
    public ResponseEntity<Map<String, String>> login(@RequestBody AccountModel accountModel) throws CustomException {
        String accessToken = authService.login(accountModel);
        return ResponseEntity.ok().body(Map.of("accessToken", accessToken));

    }

}

package vnu.uet.prodmove.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.CustomException;
import vnu.uet.prodmove.services.IAuthService;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private IAuthService authService;
    
    // @PostMapping(ApiConfig.SIGN_UP)
    // public ResponseEntity<Map<String, String>> signup(@RequestBody AccountModel accountModel) throws CustomException {
    //     try {
    //         authService.signup(accountModel);
    //         return ResponseEntity.ok().body(Map.of("message", "Register successfully!"));
    //     } catch (ConflictException ce) {
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", ce.getMessage()));
    //     } 
    //     catch (Exception e) {
    //         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
    //     }
    // }

    @PostMapping(ApiConfig.LOG_IN)
    public ResponseEntity<Object> login(HttpServletResponse response, @RequestBody AccountModel accountModel) throws CustomException {
        try {
            Map<String, Object> res = authService.login(accountModel);
            Cookie cookie = new Cookie("accessToken", (String)res.get("accessToken"));
            cookie.setMaxAge(2 * 24 * 60 * 60); // expires in 2 days
            cookie.setSecure(true);
            cookie.setHttpOnly(false); //temp
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.ok().body(res);
        }
        catch (UsernameNotFoundException ue) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", ue.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping(ApiConfig.LOG_OUT)
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        // add cookie to response
        response.addCookie(cookie);
        return ResponseEntity.ok().body("OK");
    }

}

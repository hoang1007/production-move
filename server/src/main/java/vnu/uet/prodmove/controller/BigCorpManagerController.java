package vnu.uet.prodmove.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.utils.dataModel.AccountModel;
import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.services.IBigCorpManagerService;
import vnu.uet.prodmove.services.implement.BigCorpManagerService;

/**
 * Admin
 */
@RestController
@RequestMapping(ApiConfig.MODERATOR)
public class BigCorpManagerController {

    @Autowired
    private IBigCorpManagerService bigCorpManagerService;

    @GetMapping(ApiConfig.MODERATOR_ALL_ACCOUNTS)
    public ResponseEntity<Object> getAllAccounts() {
        try {
            List<Account> accounts = (ArrayList<Account>) bigCorpManagerService.getAllAccounts();
            return ResponseEntity.ok().body(
                    accounts.stream().map(acc -> {
                        return new Object() {
                            public String username = acc.getUsername();
                            public String password = "";
                            public String role = acc.getRole().toString();
                            public Object user = acc.getUser();
                        };
                    }).collect(Collectors.toList())
            );  
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("message", "Something went wrong."));
        }
    }
    
    /**
     * Cấp tài khoản cho các role khác
     * @param accountModel
     * @return
     * @throws ConflictException
     */
    @PostMapping(ApiConfig.MODERATOR_CREATE_ACCOUNT)
    public ResponseEntity<Map<String, String>> createAccount(@RequestBody Map<String, String> info)
            throws ConflictException {
        bigCorpManagerService.createAccount(info);
        return ResponseEntity.ok().body(Map.of("message", "Create new account successfully"));
    }
    
    /**
     * ONLY update password
     * @param body
     * @return
     * @throws ConflictException
     */
    @PutMapping(ApiConfig.MODERATOR_UPDATE_ACCOUNT)
    public ResponseEntity<Object> updateAccount(@RequestBody Map<String, String> body)
            throws ConflictException {
        try {
            bigCorpManagerService.updateAccount(body);
            return ResponseEntity.ok().body(Map.of("message", "Update successfully"));
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping(ApiConfig.MODERATOR_DELETE_ACCOUNT)
    public ResponseEntity<Object> deleteAccount(@RequestParam String username) throws ConflictException {
        try {
            System.out.println("DELETE => " + username);
            bigCorpManagerService.deleteAccount(username);
            return ResponseEntity.ok().body("Delete successfully");
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user was not found");
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    /**
     * Theo dõi và thống kê sản phẩm, filter theo cssx, đại lý, bảo hành.
     * 
     * @param filter not yet - wait on Front-end
     * @param pageNumber 1,2,3,4,...
     * @param sortBy sort by attribute
     * @param typeSort asc | desc
     * @return
     */
    @GetMapping(ApiConfig.MODERATOR_STATISTICAL_ANALYSIS)
    public ResponseEntity<?> statisticalAnalysis(
        @RequestParam(name="filter") String filter,
        @RequestParam(name="page") String pageNumber,
        @RequestParam(name="sort_by") String sortBy,
        @RequestParam(name="type_sort") String typeSort
        ) {
            List<Product> products = bigCorpManagerService.getProducts(filter, Integer.parseInt(pageNumber), sortBy,
                    typeSort);
        return ResponseEntity.ok().body(products);
}

    @PostMapping(ApiConfig.MODERATOR_CHECK_PASSWORD)
    public ResponseEntity<?> checkPassword(@RequestBody Map<String, String> data) {
        try {
            String password = data.get("password");
            boolean isPasswordCorrect = bigCorpManagerService.checkPassword(password);
            if (isPasswordCorrect) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Some thing went wrong!");
        }
    }
}

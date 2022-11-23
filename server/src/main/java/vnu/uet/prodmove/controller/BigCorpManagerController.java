package vnu.uet.prodmove.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.utils.dataModel.AccountModel;
import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.services.BigCorpManagerService;

/**
 * Admin
 */
@RestController
@RequestMapping("/admin")
public class BigCorpManagerController {

    @Autowired
    private BigCorpManagerService bigCorpManagerService;
    
    /**
     * Cấp tài khoản cho các role khác
     * @param accountModel
     * @return
     * @throws ConflictException
     */
    @PostMapping(ApiConfig.CREATE_ACCOUNT)
    public ResponseEntity<String> createAccount(@RequestBody AccountModel accountModel) throws ConflictException {
        bigCorpManagerService.createAccount(accountModel);
        return ResponseEntity.ok().body("Create new account for " + accountModel.getRole() + " successfully");
    }

    @DeleteMapping(ApiConfig.DELETE_ACCOUNT)
    public ResponseEntity<Map<String, String>> deleteAccount(@RequestBody String accountId) throws ConflictException {
        Account deletedUser = bigCorpManagerService.deleteAccount(accountId);
        return ResponseEntity.ok().body(Map.of("message", "Delete " + deletedUser.getUsername() + " successfully"));
    }

    @DeleteMapping(ApiConfig.UPDATE_ACCOUNT)
    public ResponseEntity<Map<String, String>> updateAccount(@RequestBody String accountId) throws ConflictException {
        Account updatedUser = bigCorpManagerService.updateAccount(accountId);
        return ResponseEntity.ok().body(Map.of("message", "Update " + updatedUser.getUsername() + " successfully"));
    }
}

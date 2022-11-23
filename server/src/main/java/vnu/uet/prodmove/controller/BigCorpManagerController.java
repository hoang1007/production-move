package vnu.uet.prodmove.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import vnu.uet.prodmove.entity.Productline;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.services.AuthService;
import vnu.uet.prodmove.services.BigCorpManagerService;

/**
 * Admin
 */
@RestController
@RequestMapping(ApiConfig.MODERATOR)
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
    public ResponseEntity<Map<String, String>> createAccount(@RequestBody AccountModel accountModel)
            throws ConflictException {
        bigCorpManagerService.createAccount(accountModel);
        return ResponseEntity.ok().body(Map.of("message", "Create new account successfully"));
    }
    
    @PutMapping(ApiConfig.UPDATE_ACCOUNT)
    public ResponseEntity<Map<String, String>> updateAccount(@RequestBody Map<String, String> body)
            throws ConflictException {
        bigCorpManagerService.updateAccount(body);
        return ResponseEntity.ok().body(Map.of("message", "Update successfully"));
    }

    @DeleteMapping(ApiConfig.DELETE_ACCOUNT)
    public ResponseEntity<Map<String, String>> deleteAccount(@RequestBody String accountId) throws ConflictException {
        bigCorpManagerService.deleteAccount(accountId);
        return ResponseEntity.ok().body(Map.of("message", "Delete successfully"));
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
    @GetMapping(ApiConfig.STATISTICAL_ANALYSIS)
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
}

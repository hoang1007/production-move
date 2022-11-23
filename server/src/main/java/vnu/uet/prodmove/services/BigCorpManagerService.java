package vnu.uet.prodmove.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.config.UserRole;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@Service
public class BigCorpManagerService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    public void createAccount(AccountModel accountModel) throws ConflictException {
        accountService.create(accountModel);
    }

    public void updateAccount(Map<String, String> body) {
        String accountId = body.get("id");
        AccountModel accountModel = new AccountModel(
                body.get("username"),
                body.get("password"),
                UserRole.fromString(body.get("role"))
            );
        accountService.update(accountId, accountModel);
    }

    public void deleteAccount(String accountId) {
        accountService.delete(accountId);
    }

    public List<Product> getProducts(String filter, int pageNumber, String sortBy, String typeSort) {
        List<Product> products = productService.findProducts(filter, pageNumber, sortBy, typeSort);
        return products;
    }
}

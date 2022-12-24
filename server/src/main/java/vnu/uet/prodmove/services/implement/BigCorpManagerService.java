package vnu.uet.prodmove.services.implement;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.enums.UserRole;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.services.IAccountService;
import vnu.uet.prodmove.services.IBigCorpManagerService;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@Service
public class BigCorpManagerService implements IBigCorpManagerService {

    @Autowired
    private IAccountService accountService;

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
        List<Product> products = (List<Product>)((Collection)productService.findProducts(filter, pageNumber, sortBy, typeSort)).stream().collect(Collectors.toList());
        return products;
    }
}

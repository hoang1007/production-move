package vnu.uet.prodmove.services.implement;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.enums.UserRole;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.services.IAccountService;
import vnu.uet.prodmove.services.IBigCorpManagerService;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@Service
public class BigCorpManagerService implements IBigCorpManagerService {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ProductService productService;

    public Collection<Account> getAllAccounts() {
        List<Account> accounts = accountService.findAccountsByRoleNot(UserRole.MODERATOR);
        return accounts;
    }

    public void createAccount(AccountModel accountModel) throws ConflictException {
        accountService.create(accountModel);
    }

    public void updateAccount(Map<String, String> body) throws NotFoundException {
        String username = body.get("username");
        String newPassword = body.get("password");
        accountService.update(username, newPassword);
    }

    public void deleteAccount(String accountId) {
        accountService.delete(accountId);
    }

    public List<Product> getProducts(String filter, int pageNumber, String sortBy, String typeSort) {
        List<Product> products = (List<Product>) ((Collection) productService.findProducts(filter, pageNumber, sortBy,
                typeSort)).stream().collect(Collectors.toList());
        return products;
    }

    @Override
    public boolean checkPassword(String password) throws NotFoundException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Account admin = accountService.findByUsername("admin");
        boolean isMatches = passwordEncoder.matches(password, admin.getPassword());
        return isMatches;
    }
}

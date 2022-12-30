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
import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Factory;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.enums.UserRole;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.services.IAccountService;
import vnu.uet.prodmove.services.IAgencyService;
import vnu.uet.prodmove.services.IBigCorpManagerService;
import vnu.uet.prodmove.services.IFactoryService;
import vnu.uet.prodmove.services.IWarrantyCenterService;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@Service
public class BigCorpManagerService implements IBigCorpManagerService {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ProductService productService;

    @Autowired
    private IAgencyService agencyService;


    @Autowired
    private IFactoryService factoryService;


    @Autowired
    private IWarrantyCenterService warrantyService;

    public Collection<Account> getAllAccounts() {
        List<Account> accounts = accountService.findAccountsByRoleNot(UserRole.MODERATOR);
        return accounts;
    }

    public void createAccount(Map<String, String> info) throws ConflictException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = info.get("username");
        String password = info.get("password");
        String role = info.get("role");
        String name = info.get("name");
        String address = info.get("address");
        Account acc = new Account();
        acc.setUsername(username);
        acc.setPassword(passwordEncoder.encode(password));
        acc.setRole(UserRole.fromString(role));
        if(role.equals(UserRole.AGENCY.toString())) {
            Agency agency = agencyService.create(name, address);
            acc.setAgency(agency);
        }else if(role.equals(UserRole.FACTORY.toString())) {
            Factory factory = factoryService.create(name, address);
            acc.setFactory(factory);
        }else if(role.equals(UserRole.WARRANTY.toString())) {
            WarrantyCenter warranty = warrantyService.create(name, address);
            acc.setWarrantyCenter(warranty);
        }
        accountService.create(acc);
    }

    public void updateAccount(Map<String, String> body) throws NotFoundException {
        String username = body.get("username");
        String newPassword = body.get("password");
        accountService.update(username, newPassword);
    }

    public void deleteAccount(String username) throws NotFoundException {
        Account acc = accountService.findByUsername(username);
        Object user = acc.getUser();
        if (user instanceof Agency) {
            agencyService.delete(((Agency)user).getId());
        } else if (user instanceof Factory) {
            factoryService.delete(((Factory) user).getId());
        } else if (user instanceof WarrantyCenter) {
            warrantyService.delete(((WarrantyCenter) user).getId());
        }
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

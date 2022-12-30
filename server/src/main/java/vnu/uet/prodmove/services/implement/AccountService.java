package vnu.uet.prodmove.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.enums.UserRole;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.AccountRepository;
import vnu.uet.prodmove.services.IAccountService;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account create(Account acc) throws ConflictException {
        Account existingAccount = accountRepository.findByUsername(acc.getUsername());
        if (existingAccount != null) {
            throw new ConflictException("User with username " + acc.getUsername() + "is already used!");
        }
        return accountRepository.save(acc);
    }

    public Account update(String accountId, AccountModel accountModel) {
        return null;
    }

    public void delete(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account findByUsername(String username) throws vnu.uet.prodmove.exception.NotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new vnu.uet.prodmove.exception.NotFoundException("This user does not exist");
        }
        return account;
    }

    @Override
    public List<Account> findAccountsByRoleNot(UserRole role) {
        return accountRepository.findByRoleNot(role);
    }

    @Override
    public Account update(String username, String password) throws NotFoundException {
        Account user = this.findByUsername(username);
        BCryptPasswordEncoder bcrypt  = new BCryptPasswordEncoder();
        String newPassword = bcrypt.encode(password);
        user.setPassword(newPassword);
        return accountRepository.save(user);
    }
    
}

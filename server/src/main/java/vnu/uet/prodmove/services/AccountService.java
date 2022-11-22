package vnu.uet.prodmove.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.repos.AccountRepository;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account create(AccountModel accountModel) throws ConflictException {
        Account existingAccount = accountRepository.findByUsername(accountModel.getUsername());
        if (existingAccount != null) {
            throw new ConflictException("User with username " + accountModel.getUsername() + "is already used!");
        }

        Account newUser = new Account();
        newUser.setUsername(accountModel.getUsername());
        newUser.setPassword(passwordEncoder.encode(accountModel.getPassword()));
        newUser.setRole(accountModel.getRole());
        return accountRepository.save(newUser);
    }

    public Account update(String accountId, AccountModel accountModel) {
        Optional<Account> wrapperAccount = accountRepository.findById(accountId);
        if (wrapperAccount.isPresent()) {
            Account user = wrapperAccount.get();
            if (accountModel.getUsername() != null) {
                user.setUsername(accountModel.getUsername());
            }

            if (accountModel.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(accountModel.getPassword()));
            }

            if (accountModel.getRole() != null) {
                user.setRole(accountModel.getRole());
            }

            return accountRepository.save(user);
        }
        return null;
    }

    public void delete(String id) {
        accountRepository.deleteById(id);
    }
}

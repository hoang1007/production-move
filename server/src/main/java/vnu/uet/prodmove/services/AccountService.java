package vnu.uet.prodmove.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.custom.CustomUserDetails;
import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.repos.AccountRepository;
import vnu.uet.prodmove.utils.JwtTokenUtil;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Account signup(AccountModel accountModel) throws ConflictException {

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

    public String login(AccountModel accountModel) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                accountModel.getUsername(), accountModel.getPassword());
        Authentication authObject = null;
        authObject = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authObject);
        String accessToken = jwtTokenUtil.generateToken(
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
            );
        return accessToken;
    }
}

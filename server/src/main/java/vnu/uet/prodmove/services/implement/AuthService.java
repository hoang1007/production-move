package vnu.uet.prodmove.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.custom.CustomUserDetails;
import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.services.IAccountService;
import vnu.uet.prodmove.services.IAuthService;
import vnu.uet.prodmove.utils.JwtTokenUtil;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Account signup(AccountModel accountModel) throws ConflictException {
        return accountService.create(accountModel);
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

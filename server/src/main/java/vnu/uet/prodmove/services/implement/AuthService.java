package vnu.uet.prodmove.services.implement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.custom.CustomUserDetails;
import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Factory;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.services.IAccountService;
import vnu.uet.prodmove.services.IAuthService;
import vnu.uet.prodmove.utils.dataModel.AccountModel;
import vnu.uet.prodmove.utils.token.JwtTokenUtil;

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

    public Map<String, Object> login(AccountModel accountModel) throws NotFoundException {
        String username = accountModel.getUsername();
        String password = accountModel.getPassword();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authObject = null;
        authObject = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authObject);
        String accessToken = jwtTokenUtil.generateToken(
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );
        Account user = accountService.findByUsername(username);

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("username", user.getUsername());
        res.put("role", user.getRole().toString());
        Integer id = -1;
        if (user.getUser() instanceof Agency) {
            id = ((Agency)user.getUser()).getId();
        }else if (user.getUser() instanceof Factory) {
            id = ((Factory) user.getUser()).getId();
        }
        if (user.getUser() instanceof WarrantyCenter) {
            id = ((WarrantyCenter) user.getUser()).getId();
        }
        res.put("id", id);
        res.put("accessToken", accessToken);
        return res;
    }
}

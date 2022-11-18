package vnu.uet.prodmove.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vnu.uet.prodmove.domain.Account;
import vnu.uet.prodmove.repos.AccountRepository;

/**
 * CustomUserDetailsService
 */
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = accountRepository.findById(username);

        if (result.isPresent()) {
            Account account = result.get();
    
            return new UserDetails() {

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return account.getPassword();
                }

                @Override
                public String getUsername() {
                    return account.getUsername();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
                
            };
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
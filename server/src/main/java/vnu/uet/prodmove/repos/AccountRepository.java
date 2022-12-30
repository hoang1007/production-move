package vnu.uet.prodmove.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.enums.UserRole;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    public Account findByUsername(String username);

    public List<Account> findByRoleNot(UserRole role);
}

package vnu.uet.prodmove.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vnu.uet.prodmove.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    public Account findByUsername(String username);
}

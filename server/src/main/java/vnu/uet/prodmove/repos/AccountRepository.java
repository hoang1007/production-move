package vnu.uet.prodmove.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import vnu.uet.prodmove.domain.Account;


public interface AccountRepository extends JpaRepository<Account, String> {
}

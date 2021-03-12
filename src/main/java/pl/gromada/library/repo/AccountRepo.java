package pl.gromada.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromada.library.model.Account;


@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}

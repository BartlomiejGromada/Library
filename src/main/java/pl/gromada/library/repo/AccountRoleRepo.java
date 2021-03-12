package pl.gromada.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromada.library.model.AccountRole;

@Repository
public interface AccountRoleRepo extends JpaRepository<AccountRole, Long> {
    AccountRole findByRole(String role);
}

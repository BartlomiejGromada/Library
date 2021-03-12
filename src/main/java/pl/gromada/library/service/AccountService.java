package pl.gromada.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gromada.library.model.Account;
import pl.gromada.library.model.AccountRole;
import pl.gromada.library.repo.AccountRepo;
import pl.gromada.library.repo.AccountRoleRepo;

@Service
public class AccountService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private AccountRepo accountRepo;
    private AccountRoleRepo accountRoleRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepo(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Autowired
    public void setUserRoleRepo(AccountRoleRepo accountRoleRepo) {
        this.accountRoleRepo = accountRoleRepo;
    }

    public void addWithDefaultRole(Account account) {
        AccountRole defaultRole = accountRoleRepo.findByRole(DEFAULT_ROLE);
        account.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(account.getPassword());
        account.setPassword(passwordHash);
        accountRepo.save(account);
    }
}

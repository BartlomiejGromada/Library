package pl.gromada.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;
    @NotBlank(message = "{pl.gromada.model.Account.firstName.message}")
    private String firstName;
    @NotBlank(message = "{pl.gromada.model.Account.lastName.message}")
    private String lastName;
    @Email(message = "{pl.gromada.model.Account.email.message}")
    @NotBlank(message = "{pl.gromada.model.Account.email.message}")
    private String email;
    @NotBlank(message = "{pl.gromada.model.Account.password.message}")
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<AccountRole> roles = new HashSet<>();
}

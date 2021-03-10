package pl.gromada.library.model;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuthor;
    @NotBlank(message = "{pl.gromada.model.Author.firstName.message}")
    private String firstName;
    @NotBlank(message = "{pl.gromada.model.Author.lastName.message}")
    private String lastName;
    @NotBlank(message = "{pl.gromada.model.Author.dateOfBirth.message}")
    private String dateOfBirth;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}

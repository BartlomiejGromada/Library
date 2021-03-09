package pl.gromada.library.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuthor;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Past
    private Date dateOfBirth;

    @Override
    public String toString() {
        return firstName + " "+lastName;
    }
}

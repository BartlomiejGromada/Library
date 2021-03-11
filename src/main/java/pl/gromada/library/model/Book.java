package pl.gromada.library.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBook;
    @NotBlank(message = "{pl.gromada.model.Book.isbn.NotBlank.message}")
    @Length(min = 13, max = 13, message = "{pl.gromada.model.Book.isbn.Length.message}")
    @Column(unique = true)
    private String isbn;
    @NotBlank(message = "{pl.gromada.model.Book.title.message}")
    private String title;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", author=" + author +
                '}';
    }

}

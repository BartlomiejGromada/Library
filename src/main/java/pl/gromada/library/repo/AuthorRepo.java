package pl.gromada.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromada.library.model.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
}

package pl.gromada.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromada.library.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
}

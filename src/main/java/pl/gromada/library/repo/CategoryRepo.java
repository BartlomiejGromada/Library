package pl.gromada.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromada.library.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}

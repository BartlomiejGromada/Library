package pl.gromada.library.service;

import org.springframework.stereotype.Service;
import pl.gromada.library.repo.CategoryRepo;

@Service
public class CategoryService {

    private CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
}

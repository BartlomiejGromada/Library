package pl.gromada.library.service;

import org.springframework.stereotype.Service;
import pl.gromada.library.model.Category;
import pl.gromada.library.repo.CategoryRepo;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }
}

package pl.gromada.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.gromada.library.model.Category;
import pl.gromada.library.repo.CategoryRepo;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Page<Category> findAllCategories(int page) {
        return categoryRepo.findAll(PageRequest.of(page-1, 5));
    }

    public Category findCategoryById(long id) {
        return categoryRepo.findById(id).get();
    }

    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    public void updateCategory(Category category) {
        categoryRepo.save(category);
    }

    public void deleteCategoryById(long id) {
        categoryRepo.deleteById(id);
    }
}

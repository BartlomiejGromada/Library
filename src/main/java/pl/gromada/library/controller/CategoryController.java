package pl.gromada.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gromada.library.model.Category;
import pl.gromada.library.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private int currentPage;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.currentPage = 1;
    }

    @GetMapping
    public String allCategories(Optional<Integer> page, Model model) {
        currentPage = page.orElse(1) - 1;
        Page<Category> categories = categoryService.findAllCategories(currentPage);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", currentPage);
        List<Integer> pages = IntStream.range(1, categories.getTotalPages() + 1).boxed()
                .collect(Collectors.toList());
        model.addAttribute("pages", pages);
        return "categories_template/categories";
    }

    @GetMapping("/addForm")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories_template/addCategoryForm";
    }

    @PostMapping("/addForm")
    public String addCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            categoryService.addCategory(category);
            redirectAttributes.addFlashAttribute("message", "Category with id: " + category.getIdCategory()
                    + " has been added");
            return "redirect:/categories";
        }

        return "categories_template/addCategoryForm";
    }

    @GetMapping("/updateForm/{id}")
    public String updateCategoryForm(Model model, @PathVariable long id) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "categories_template/updateCategoryForm";
    }

    @PostMapping("/updateForm")
    public String updateCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            categoryService.updateCategory(category);
            redirectAttributes.addFlashAttribute("message", "Category with id: " + category.getIdCategory()
                    + " has been updated");
            return "redirect:/categories";
        }
        return "categories_template/updateCategoryForm";
    }

    @GetMapping("/delete/{id}")
    public String deletedCategory(@PathVariable long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategoryById(id);
        redirectAttributes.addFlashAttribute("message", "Category with id: " + id + " has been deleted");
        return "redirect:/categories";
    }
}

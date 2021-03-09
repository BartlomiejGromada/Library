package pl.gromada.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gromada.library.model.Book;
import pl.gromada.library.model.Category;
import pl.gromada.library.service.BookService;
import pl.gromada.library.service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private CategoryService categoryService;
    private int currentPage;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
        currentPage = 1;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String allBooks(@RequestParam Optional<Integer> page, Model model) {
        currentPage = page.orElse(1) - 1;
        Page<Book> books = bookService.findAllBooks(currentPage);
        int totalPages = books.getTotalPages();
        List<Integer> pages = IntStream.range(1, totalPages + 1).boxed().collect(Collectors.toList());
        model.addAttribute("books", books);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", currentPage);
        return "books_templates/books";
    }

    @GetMapping("/addBookForm")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);

        return "books_templates/addBookForm";
    }

    @GetMapping("delete/{idBook}")
    public String delete(@PathVariable long idBook, RedirectAttributes redirectAttributes) {
        bookService.deleteBookById(idBook);
        redirectAttributes.addFlashAttribute("message", "Book with id:" + idBook + " has been deleted.");
        redirectAttributes.addAttribute("page", currentPage);
        return "redirect:/books";
    }

    @GetMapping("updateForm/{id}")
    public String updateForm(@PathVariable long id, Model model) {

        return "books_templates/updateBookForm";
    }
}

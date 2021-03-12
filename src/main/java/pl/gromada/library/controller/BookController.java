package pl.gromada.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gromada.library.model.Author;
import pl.gromada.library.model.Book;
import pl.gromada.library.model.Category;
import pl.gromada.library.service.AuthorService;
import pl.gromada.library.service.BookService;
import pl.gromada.library.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private CategoryService categoryService;
    private AuthorService authorService;
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

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String allBooks(@RequestParam Optional<Integer> page, Model model) {
        currentPage = page.orElse(1);
        Page<Book> books = bookService.findAllBooks(currentPage);
        int totalPages = books.getTotalPages();
        List<Integer> pages = IntStream.range(1, totalPages + 1).boxed().collect(Collectors.toList());
        model.addAttribute("books", books);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", currentPage);
        return "books_templates/books";
    }

    @GetMapping("/addForm")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        List<Category> categories = categoryService.findAllCategories();
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("categoriesSelect", categories);
        model.addAttribute("authorsSelect", authors);
        return "books_templates/addBookForm";
    }

    @PostMapping("/addForm")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        if (!bindingResult.hasErrors()) {
            bookService.addBook(book);
            redirectAttributes.addFlashAttribute("message", "Book with id: " + book.getIdBook()
                    + " has been added");
            redirectAttributes.addAttribute("page", currentPage);
            return "redirect:/books";
        }
        model.addAttribute("categoriesSelect", categoryService.findAllCategories());
        model.addAttribute("authorsSelect", authorService.findAllAuthors());
        return "books_templates/addBookForm";
    }

    @GetMapping("updateForm/{id}")
    public String updateForm(@PathVariable long id, Model model) {
        Book book = bookService.findBookById(id);
        List<Category> categories = categoryService.findAllCategories();
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("categoriesSelect", categories);
        model.addAttribute("authorsSelect", authors);
        return "books_templates/updateBookForm";
    }

    @PostMapping("updateForm")
    public String update(@Valid @ModelAttribute Book book, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        if (!bindingResult.hasErrors()) {
            bookService.updateBook(book);
            redirectAttributes.addFlashAttribute("message", "Book with id:" + book.getIdBook()
                    + " has been updated");
            redirectAttributes.addAttribute("page", currentPage);
            return "redirect:/books";
        }
        model.addAttribute("categoriesSelect", categoryService.findAllCategories());
        model.addAttribute("authorsSelect", authorService.findAllAuthors());
        return "books_templates/updateBookForm";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBookById(id);
        redirectAttributes.addFlashAttribute("message", "Book with id:" + id + " has been deleted");
        redirectAttributes.addAttribute("page", currentPage);
        return "redirect:/books";
    }
}

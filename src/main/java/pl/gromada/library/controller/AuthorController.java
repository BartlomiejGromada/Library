package pl.gromada.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gromada.library.model.Author;
import pl.gromada.library.service.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private int currentPage;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
        this.currentPage = 1;
    }

    @GetMapping
    public String allAuthors(@RequestParam Optional<Integer> page, Model model) {
        currentPage = page.orElse(1) - 1;
        Page<Author> authors = authorService.findAllAuthors(currentPage);
        model.addAttribute("authors", authors);
        model.addAttribute("currentPage", currentPage);
        List<Integer> pages = IntStream.range(1, authors.getTotalPages() + 1).boxed()
                .collect(Collectors.toList());
        model.addAttribute("pages", pages);
        return "authors_template/authors";
    }

    @GetMapping("/addForm")
    public String addAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors_template/addAuthorForm";
    }

    @PostMapping("/addForm")
    public String addAuthor(@Valid @ModelAttribute Author author, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            authorService.addAuthor(author);
            redirectAttributes.addFlashAttribute("message", "Author with id: " + author.getIdAuthor() + " has benn added");
            redirectAttributes.addAttribute("page", currentPage + 1);
            return "redirect:/authors";
        }

        return "/authors_template/addAuthorForm";
    }

    @GetMapping("/updateForm/{id}")
    public String updateAuthorForm(@PathVariable long id, Model model) {
        model.addAttribute("author", authorService.findAuthorById(id));
        return "authors_template/updateAuthorForm";
    }

    @PostMapping("/updateForm")
    public String updateAuthor(@Valid @ModelAttribute Author author, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if(!bindingResult.hasErrors()) {
            authorService.updateAuthor(author);
            redirectAttributes.addFlashAttribute("message", "Author with id: " + author.getIdAuthor() + " has been updated");
            redirectAttributes.addAttribute("page", currentPage + 1);
            return "redirect:/authors";
        }

        return "authors_template/updateAuthorForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        authorService.deleteAuthorById(id);
        redirectAttributes.addFlashAttribute("message", "Author with id: " + id + " has been deleted");
        redirectAttributes.addAttribute("page", currentPage + 1);
        return "redirect:/authors";
    }
}

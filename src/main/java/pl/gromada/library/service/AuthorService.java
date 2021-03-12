package pl.gromada.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.gromada.library.model.Author;
import pl.gromada.library.repo.AuthorRepo;

import java.util.List;

@Service
public class AuthorService {

    private AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public Page<Author> findAllAuthors(int page) {
        return authorRepo.findAll(PageRequest.of(page - 1, 5));
    }

    public List<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    public Author findAuthorById(long id) {
        return authorRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void addAuthor(Author author) {
        authorRepo.save(author);
    }

    public void deleteAuthorById(long id) {
        authorRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        authorRepo.deleteById(id);
    }

    public void updateAuthor(Author author) {
        authorRepo.save(author);
    }
}

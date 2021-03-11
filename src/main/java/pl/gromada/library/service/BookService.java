package pl.gromada.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.gromada.library.model.Book;
import pl.gromada.library.repo.BookRepo;

import java.util.Optional;

@Service
public class BookService {

    private BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Page<Book> findAllBooks(int page) {
        return bookRepo.findAll(PageRequest.of(page - 1, 5));
    }

    public Optional<Book> findBookById(long id) {
        return bookRepo.findById(id);
    }

    public void addBook(Book book) {
        bookRepo.save(book);
    }

    public void updateBook(Book book) {
        bookRepo.save(book);
    }

    public void deleteBookById(long id) {
        bookRepo.deleteById(id);
    }

}

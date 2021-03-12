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

    public Book findBookById(long id) {
        return bookRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void addBook(Book book) {
        bookRepo.save(book);
    }

    public void updateBook(Book book) {
        bookRepo.save(book);
    }

    public void deleteBookById(long id) {
        bookRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        bookRepo.deleteById(id);
    }

}

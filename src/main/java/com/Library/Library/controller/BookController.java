package com.Library.Library.controller;

import com.Library.Library.Models.Book;
import com.Library.Library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}/books")
    public List<Book> getBooksByAuthor(@PathVariable Long id) {
        return bookRepository.findByAuthorId(id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        try {
            return bookRepository.findById(id)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = null;
        try {
            book = bookRepository.findById(id)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        book.setTitle(updatedBook.getTitle());
        book.setCreationDate(updatedBook.getCreationDate());
        book.setNumberOfPages(updatedBook.getNumberOfPages());
        book.setAuthor(updatedBook.getAuthor());
        book.setCategories(updatedBook.getCategories());
        book.setAvailable(updatedBook.isAvailable());

        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}


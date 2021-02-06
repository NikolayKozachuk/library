package com.example.library.controller;


import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;


    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/getBookById/{id}")
    public Optional<Book> getBookById(@PathVariable(value = "id") Long id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable(value = "id") Long id, @RequestBody Book bookDetails) {
        Optional<Book> book = bookRepository.findById(id);
        Book book_new = book.get();
        book_new.setName(bookDetails.getName());
        book_new.setIsbn(bookDetails.getIsbn());
        book_new.setUser(bookDetails.getUser());
        return bookRepository.save(book_new);

    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        Book book_new = book.get();
        bookRepository.delete(book_new);
    }
    @PutMapping("/tookBook/{id}")
    public Book tookBook(@PathVariable(value ="id") Long bookId, @RequestParam Long userId){
        return bookService.tookBook(bookId,userId);

    }
    @PutMapping("/returnBook/{id}")
    public Book returnBook(@PathVariable(value ="id") Long bookId){
        return bookService.returnBook(bookId);
    }


}

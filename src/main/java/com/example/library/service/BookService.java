package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Book tookBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).get();
        if (book.getUser().equals(null)) {
           User user = userRepository.findById(userId).get();
           user.addBook(book);
        }else {
            System.out.println("Book is taken by other users ");
        }
        return book;

    }
    public Book returnBook(Long bookId){
        Book book = bookRepository.findById(bookId).get();
       User user = book.getUser();
        user.removeBook(book);
        return book;

    }


}

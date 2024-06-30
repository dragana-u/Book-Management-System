package com.bookmanagement.service;

import com.bookmanagement.model.Book;
import com.bookmanagement.model.MyBooks;
import com.bookmanagement.model.User;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.repository.MyBooksRepository;
import com.bookmanagement.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {
    MyBooksRepository myBooksRepository;
    BookRepository bookRepository;
    UserRepository userRepository;

    public MyBookService(MyBooksRepository myBooksRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.myBooksRepository = myBooksRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void addToMyBook(Long idBook){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Book book = bookRepository.findById(idBook).get();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        MyBooks myBooks = new MyBooks();
        myBooks.setBook(book);
        myBooks.setUser(user);
        myBooks.setTitle(book.getTitle());
        myBooks.setPrice(book.getPrice());
        myBooks.setAuthor(book.getAuthor());
        myBooksRepository.save(myBooks);
//        bookRepository.deleteById(idBook);
    }
    public List<MyBooks> getMyBooks(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return myBooksRepository.findByUser(user);
    }
    void deleteById(Long id){
        myBooksRepository.deleteById(id);
    }

}

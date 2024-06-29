package com.bookmanagement.service;

import com.bookmanagement.model.Book;
import com.bookmanagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book){
        bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    void deleteById(Long id){
        bookRepository.deleteById(id);
    }
    public Book findById(Long id){
        return bookRepository.findById(id).get();
    }
}

package com.bookmanagement.service;

import com.bookmanagement.model.Book;
import com.bookmanagement.model.MyBooks;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.repository.MyBooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {
    MyBooksRepository myBooksRepository;
    BookRepository bookRepository;

    public MyBookService(MyBooksRepository myBooksRepository, BookRepository bookRepository) {
        this.myBooksRepository = myBooksRepository;
        this.bookRepository = bookRepository;
    }

    public void addToMyBook(Long id){
        Book book = bookRepository.findById(id).get();
        book.setInMyBooks(true);
        MyBooks myBooks = new MyBooks(book.getTitle(),book.getAuthor(),book.getPrice());
        myBooksRepository.save(myBooks);
        bookRepository.deleteById(id);
    }
    public List<MyBooks> getMyBooks(){
        return myBooksRepository.findAll();
    }
    void deleteById(Long id){
        myBooksRepository.deleteById(id);
    }

}

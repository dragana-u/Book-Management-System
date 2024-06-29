package com.bookmanagement.web;

import com.bookmanagement.model.Book;
import com.bookmanagement.model.MyBooks;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.repository.MyBooksRepository;
import com.bookmanagement.service.BookService;
import com.bookmanagement.service.MyBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BookController {
    private final MyBooksRepository myBooksRepository;
    private final BookRepository bookRepository;
    BookService bookService;
    MyBookService myBookService;

    public BookController(BookService bookService, MyBookService myBookService, MyBooksRepository myBooksRepository, BookRepository bookRepository) {
        this.bookService = bookService;
        this.myBookService = myBookService;
        this.myBooksRepository = myBooksRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String register() {
        return "book_register";
    }

    @GetMapping("/available_books")
    public String availableBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "available_books";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam String title,
                           @RequestParam String author,
                           @RequestParam BigDecimal price) {
        Book book = new Book(title, author, price);
        bookService.save(book);
        return "redirect:/available_books";
    }

    @PostMapping("/saveMyBook/{id}")
    public String saveMyBook(@PathVariable Long id) {
        myBookService.addToMyBook(id);
        return "redirect:/my_books";
    }

    @PostMapping("/delete/{id}")
    public String deleteMyBook(@PathVariable Long id) {
        myBooksRepository.deleteById(id);
        return "redirect:/my_books";
    }

    @GetMapping("/my_books")
    public String my_books(Model model) {
        model.addAttribute("my_books", myBookService.getMyBooks());
        return "my_books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "edit_book";
    }
    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/available_books";
    }
}

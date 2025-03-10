package com.bookmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class MyBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @ManyToOne
    User user;
    @ManyToOne
    Book book;

    public MyBooks(Long id, String title, String author, BigDecimal price) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public MyBooks(String title, String author, BigDecimal price) {
        super();
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public MyBooks() {
        super();
    }

}

package com.bookmanagement.repository;

import com.bookmanagement.model.MyBooks;
import com.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyBooksRepository extends JpaRepository<MyBooks, Long> {
    List<MyBooks> findByUser(User user);
}

package com.bookmanagement.repository;

import com.bookmanagement.model.MyBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBooksRepository extends JpaRepository<MyBooks, Long> {

}

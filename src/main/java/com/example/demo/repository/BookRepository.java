package com.example.demo.repository;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface BookRepository extends JpaRepository<Book, Long>{
    public Book findByName(String name);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Book book set book.status =:out where book.name =:bookName")
    public void changeBookStatus(@Param("bookName") String bookName, @Param("out") String status);
}

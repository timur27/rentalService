package com.example.demo.Service;


import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configurable
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book findByName(String name){
        return bookRepository.findByName(name);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public void save(Book book){
        bookRepository.save(book);
    }

}

package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookService {

    @Autowired
    private BookRepository repo;

    public List<Book> getBooks(){
        List<Book> books = repo.findAll();
        return books;
    }
}

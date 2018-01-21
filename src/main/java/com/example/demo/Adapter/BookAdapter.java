package com.example.demo.Adapter;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.Service.BookService;
import com.example.demo.models.Book;
import com.example.demo.models.Thing;
import com.example.demo.repository.BookRepository;

public class BookAdapter extends RentalObject {
    private Book book;
    private BookRepository bookRepository;


    public BookAdapter(Book book){
        this.book = book;
        bookRepository = ApplicationContextHolder.getContext().getBean(BookRepository.class);
    }


    @Override
    public void save(){
        book.setStatus("AVAILABLE");
        bookRepository.save(book);
    }
    @Override
    public String getType(){
        return this.book.getType();
    }
}

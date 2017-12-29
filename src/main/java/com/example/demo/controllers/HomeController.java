package com.example.demo.controllers;


import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {
    private BookRepository bookRepository;

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public String getAllBooks(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("myNewModel", books);
        return "books";
    }
}

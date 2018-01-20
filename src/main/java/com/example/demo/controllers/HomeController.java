package com.example.demo.controllers;

import com.example.demo.Adapter.BookAdapter;
import com.example.demo.Adapter.RentalObject;
import com.example.demo.Service.BookService;
import com.example.demo.Template.BookMaster;
import com.example.demo.models.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ClothingRepository;
import com.example.demo.repository.FlatRepository;
import com.example.demo.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


//MVC pattern
@Controller
public class HomeController {
    @Autowired
    public BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private ClothingRepository clothingRepository;

    @Autowired
    private ShipRepository shipRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addElement(@Valid Book book, @Valid Flat flat, @Valid Clothing clothing, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        RentalObject[] rentalObjects = {new BookAdapter(book)};
        for (RentalObject r : rentalObjects){
            if (r.getType() == "book")
                bookService.save(book);
            else if (r.getType() == "flat")
                flatRepository.save(flat);

        }
        modelAndView.setViewName("add");
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addProducts(){
        ModelAndView modelAndView = new ModelAndView();
        Book book = new Book();
        modelAndView.addObject("book", book);
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @RequestMapping(value = "/books/{bookName}", method = RequestMethod.POST)
    public ModelAndView setShip(@PathVariable("bookName") String bookName, @Valid Ship ship){
        ModelAndView modelAndView = new ModelAndView();
        ship.setProduct(bookName.replace("_", " "));
        shipRepository.save(ship);
        BookMaster bookMaster = new BookMaster();
        Ship refreshedShip = bookMaster.makeWork(ship.getProduct());
        shipRepository.save(refreshedShip);
        modelAndView.setViewName("book");
        return modelAndView;
    }

    @RequestMapping(value = "/books/{bookName}", method = RequestMethod.GET)
    public String getBookName(@PathVariable("bookName") String bookName, Model model){
        Book book = bookService.findByName(bookName.replace("_", " "));
        if (book != null){
            model.addAttribute("bookExists", book);
            model.addAttribute("bookname", bookName);
            model.addAttribute("show", "yes");
            model.addAttribute("ship", new Ship());
            List<ShipType> shipTypes = new ArrayList<>();
            shipTypes.add(new ShipType("FAST"));
            shipTypes.add(new ShipType("STANDARD"));
            shipTypes.add(new ShipType("BUSINESS"));

            model.addAttribute("shipTypes", shipTypes);
        }
        return "book";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getAllBooks(Model model){
        List<Book> bookList = bookService.findAll();
        model.addAttribute("books", bookList);
        model.addAttribute("url", "/books/");
        return "books";
    }

    @RequestMapping(value = "/clothing", method = RequestMethod.GET)
    public String getAllClothing(Model model){
        List<Clothing> clothingList = clothingRepository.findAll();
        model.addAttribute("clothing", clothingList);
        return "clothing";
    }

    @RequestMapping(value = "/flats", method = RequestMethod.GET)
    public String getAllFlats(Model model){
        List<Flat> flatList = flatRepository.findAll();
        model.addAttribute("flats", flatList);
        return "flats";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getRentalList(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "index";
    }
}
package com.example.demo.controllers;

import com.example.demo.Adapter.BookAdapter;
import com.example.demo.Adapter.ClothingAdapter;
import com.example.demo.Adapter.FlatAdapter;
import com.example.demo.Adapter.RentalObject;
import com.example.demo.Facade.SaveMaker;
import com.example.demo.Observer.StatusObserver;
import com.example.demo.Service.BookService;
import com.example.demo.Singleton.MySituation;
import com.example.demo.Template.BookMaster;
import com.example.demo.Template.ClothingMaster;
import com.example.demo.Template.FlatMaster;
import com.example.demo.models.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ClothingRepository;
import com.example.demo.repository.FlatRepository;
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

    SaveMaker saveMaker;

    //Dependency Injection setter
    @Autowired
    public void setSaveMaker(SaveMaker saveMaker){
        this.saveMaker = saveMaker;
    }


    @RequestMapping(value = "/situation", method = RequestMethod.GET)
    public String getSituation(Model model){
        //Singleton pattern
        MySituation situation = MySituation.INSTANCE;
        model.addAttribute("situation", "yes");
        model.addAttribute("situations", situation.getShips());
        return "situation";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addElement(@Valid Book book,@Valid Flat flat, @Valid Clothing clothing, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        //Adapter pattern
        List<RentalObject> rentalObjectList = new ArrayList<>();
        if (book.getAuthor() != null)
            rentalObjectList.add(new BookAdapter(book));
        if (flat.getAddress() != null)
            rentalObjectList.add(new FlatAdapter(flat));
        if (clothing.getCollection() != null)
            rentalObjectList.add(new ClothingAdapter(clothing));

        for (RentalObject r : rentalObjectList){
            r.save();
        }
        modelAndView.setViewName("add");
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addProducts(){
        ModelAndView modelAndView = new ModelAndView();
        Book book = new Book();
        Flat flat = new Flat();
        Clothing clothing = new Clothing();
        modelAndView.addObject("clothing", clothing);
        modelAndView.addObject("flat", flat);
        modelAndView.addObject("book", book);
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @RequestMapping(value = "/books/{bookName}", method = RequestMethod.POST)
    public ModelAndView setShip(@PathVariable("bookName") String bookName, @Valid Ship ship){
        ModelAndView modelAndView = new ModelAndView();
        //Observer pattern
        new StatusObserver(ship);
        ship.setProduct(bookName.replace("_", " "));
        ship.setProductType("book");
        //Facade pattern
        saveMaker.setShip(ship);
        saveMaker.saveShip();
        //Template pattern
        BookMaster bookMaster = new BookMaster();
        Ship refreshedShip = bookMaster.makeWork(ship.getProduct());
        //Facade continuation
        saveMaker.setShip(refreshedShip);
        saveMaker.saveShip();
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
        model.addAttribute("url", "/clothing/");
        return "clothing";
    }

    @RequestMapping(value = "/flats", method = RequestMethod.GET)
    public String getAllFlats(Model model){
        List<Flat> flatList = flatRepository.findAll();
        model.addAttribute("flats", flatList);
        model.addAttribute("url", "/flats/");
        return "flats";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getRentalList(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        List<String> ss = new ArrayList<>();
        ss.add("Ksiazki");
        ss.add("Mieszkania");
        ss.add("Odziez");
        model.addAttribute("ss", ss);
        return "index";
    }





    @RequestMapping(value = "/clothing/{clothingName}", method = RequestMethod.POST)
    public ModelAndView setforCloth(@PathVariable("clothingName") String clothingName, @Valid Ship ship){
        ModelAndView modelAndView = new ModelAndView();
        new StatusObserver(ship);
        ship.setProduct(clothingName.replace("_", " "));
        ship.setProductType("clothing");
        saveMaker.setShip(ship);
        saveMaker.saveShip();
        ClothingMaster clothingMaster = new ClothingMaster();
        Ship refreshedShip = clothingMaster.makeWork(ship.getProduct());
        saveMaker.setShip(refreshedShip);
        saveMaker.saveShip();
        modelAndView.setViewName("cloth");
        return modelAndView;
    }

    @RequestMapping(value = "/clothing/{clothingName}", method = RequestMethod.GET)
    public String getlClothName(@PathVariable("clothingName") String clothingName, Model model){
        Clothing clothing = clothingRepository.findByCollection(clothingName.replace("_", " "));
        if (clothing != null){
            model.addAttribute("clothExists", clothing);
            model.addAttribute("clothname", clothingName);
            model.addAttribute("showw", "yes");
            model.addAttribute("ship", new Ship());
            List<ShipType> shipTypes = new ArrayList<>();
            shipTypes.add(new ShipType("FAST"));
            shipTypes.add(new ShipType("STANDARD"));
            shipTypes.add(new ShipType("BUSINESS"));

            model.addAttribute("shipTypes", shipTypes);
        }
        return "cloth";
    }


    @RequestMapping(value = "/flats/{flatName}", method = RequestMethod.POST)
    public ModelAndView setforFlat(@PathVariable("flatName") String flatName, @Valid Ship ship){
        ModelAndView modelAndView = new ModelAndView();
        new StatusObserver(ship);
        ship.setProduct(flatName.replace("_", " "));
        ship.setProductType("clothing");
        saveMaker.setShip(ship);
        saveMaker.saveShip();
        FlatMaster flatMaster = new FlatMaster();
        Ship refreshedShip = flatMaster.makeWork(ship.getProduct());
        saveMaker.setShip(refreshedShip);
        saveMaker.saveShip();
        modelAndView.setViewName("flat");
        return modelAndView;
    }

    @RequestMapping(value = "/flats/{flatName}", method = RequestMethod.GET)
    public String getFlatName(@PathVariable("flatName") String flatName, Model model){
        Flat flat = flatRepository.findByAddress(flatName.replace("_", " "));
        if (flat != null){
            model.addAttribute("flatExists", flat);
            model.addAttribute("flatname", flatName);
            model.addAttribute("showw", "yes");
            model.addAttribute("ship", new Ship());
            List<ShipType> shipTypes = new ArrayList<>();
            shipTypes.add(new ShipType("FAST"));
            shipTypes.add(new ShipType("STANDARD"));
            shipTypes.add(new ShipType("BUSINESS"));

            model.addAttribute("shipTypes", shipTypes);
        }
        return "flat";
    }
}
package com.example.demo.Template;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.Service.BookService;
import com.example.demo.models.Book;
import com.example.demo.models.Ship;
import com.example.demo.repository.ShipRepository;

public class BookMaster extends ShipService {
    private Book book;
    private Ship ship;

    public BookMaster(){
        super();
    }

    void getShip(String shipName){
        BookService bookService = ApplicationContextHolder.getContext().getBean(BookService.class);
        ShipRepository shipRepository = ApplicationContextHolder.getContext().getBean(ShipRepository.class);
        book = bookService.findByName(shipName);
        ship = shipRepository.findByProduct(shipName);
    }

    void refreshData(){
        if (ship.getType().equals("FAST"))
            ship.setPrice(book.getPrice()*0.5);
        else if (ship.getType().equals("BUSINESS"))
            ship.setPrice(book.getPrice()*0.6);
        else if (ship.getType().equals("STANDARD"))
            ship.setPrice(book.getPrice()*0.1);
        ship.setStatus("SEND");
    }

    Ship writeShip(){
        return ship;
    }
}

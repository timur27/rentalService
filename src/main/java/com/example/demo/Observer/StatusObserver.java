package com.example.demo.Observer;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.models.Ship;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ShipRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class StatusObserver extends Observer {
    public StatusObserver(Ship ship){
        this.ship = ship;
        this.ship.attach(this);
    }

    @Override
    public void update(){
        if (ship.getProductType().equals("book")){
            BookRepository bookRepository = ApplicationContextHolder.getContext().getBean(BookRepository.class);
            bookRepository.changeBookStatus(ship.getProduct(), "OUT");
        }
    }
}

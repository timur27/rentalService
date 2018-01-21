package com.example.demo.Decorator;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.models.Ship;
import com.example.demo.repository.ShipRepository;


public class LongerShipDecorator extends ShipDecorator {

    public LongerShipDecorator(Ship ship){
        super(ship);
    }

    @Override
    public void save(){
        ship.save();
        YearMoreShip();
    }

    private void YearMoreShip(){
        ShipRepository shipRepository = ApplicationContextHolder.getContext().getBean(ShipRepository.class);
        String tmp = ship.getEnd().replace("8", "9");
        ship.setEnd(tmp);
        shipRepository.save(ship);
    }
}

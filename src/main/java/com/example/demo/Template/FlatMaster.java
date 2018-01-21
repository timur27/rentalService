package com.example.demo.Template;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.models.Flat;
import com.example.demo.models.Ship;
import com.example.demo.repository.FlatRepository;
import com.example.demo.repository.ShipRepository;

public class FlatMaster extends ShipService {
    Flat flat;
    Ship ship;

    public FlatMaster(){super();}

    void getShip(String shipName){
        FlatRepository flatRepository = ApplicationContextHolder.getContext().getBean(FlatRepository.class);
        ShipRepository shipRepository = ApplicationContextHolder.getContext().getBean(ShipRepository.class);
        flat = flatRepository.findByAddress(shipName);
        ship = shipRepository.findByProduct(shipName);
    }

    void refreshData(){
        if (ship.getType().equals("FAST"))
            ship.setPrice(flat.getPrice()*0.3);
        else if (ship.getType().equals("BUSINESS"))
            ship.setPrice(flat.getPrice()*0.6);
        else if (ship.getType().equals("STANDARD"))
            ship.setPrice(flat.getPrice()*0.1);
        ship.setStatus("SEND");
    }

    Ship writeShip(){
        return ship;
    }
}

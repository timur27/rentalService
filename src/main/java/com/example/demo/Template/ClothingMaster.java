package com.example.demo.Template;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.models.Clothing;
import com.example.demo.models.Ship;
import com.example.demo.repository.ClothingRepository;
import com.example.demo.repository.ShipRepository;

public class ClothingMaster extends ShipService {
    private Clothing clothing;
    private Ship ship;

    public ClothingMaster(){
        super();
    }

    void getShip(String shipName){
        ClothingRepository clothingRepository = ApplicationContextHolder.getContext().getBean(ClothingRepository.class);
        ShipRepository shipRepository = ApplicationContextHolder.getContext().getBean(ShipRepository.class);
        clothing = clothingRepository.findByCollection(shipName);
        ship = shipRepository.findByProduct(shipName);
    }

    void refreshData(){
        if (ship.getType().equals("FAST"))
            ship.setPrice(clothing.getPrice()*0.1);
        else if (ship.getType().equals("BUSINESS"))
            ship.setPrice(clothing.getPrice()*0.2);
        else if (ship.getType().equals("STANDARD"))
            ship.setPrice(clothing.getPrice()*0.05);
        ship.setStatus("SEND");
    }

    Ship writeShip(){
        return ship;
    }
}

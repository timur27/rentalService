package com.example.demo.Facade;

import com.example.demo.models.Ship;
import org.springframework.stereotype.Service;

@Service
public class SaveMaker {
    private Ship ship;

    public SaveMaker(){ }

    public void saveShip(){
        ship.save();
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}

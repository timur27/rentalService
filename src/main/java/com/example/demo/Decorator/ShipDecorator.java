package com.example.demo.Decorator;

import com.example.demo.models.Ship;

public class ShipDecorator implements IShip {
    protected Ship ship;

    public ShipDecorator(Ship ship){
        this.ship = ship;
    }

    public void save(){
        ship.save();
    }

    public String getType(){
        return ship.getType();
    }

    public String getProduct(){
        return ship.getProduct();
    }
}

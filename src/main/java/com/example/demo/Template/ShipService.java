package com.example.demo.Template;

import com.example.demo.models.Ship;

public abstract class ShipService {
    private Ship ship;
    private String shipName;

    public ShipService(){
       this.shipName = shipName;
    }

    abstract void getShip(String shipName);
    abstract void refreshData();
    abstract Ship writeShip();

    public final Ship makeWork(String shipName){
       getShip(shipName);
       refreshData();
       this.ship = writeShip();
       return this.ship;
    }
}
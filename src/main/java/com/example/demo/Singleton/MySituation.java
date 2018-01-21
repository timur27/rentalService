package com.example.demo.Singleton;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.models.Ship;
import com.example.demo.repository.ShipRepository;

import java.util.List;

public final class MySituation {
    public final static MySituation INSTANCE = new MySituation();

    private MySituation(){};
    public MySituation getInstance(){
        return this.INSTANCE;
    }

    public List<Ship> getShips(){
        ShipRepository shipRepository = ApplicationContextHolder.getContext().getBean(ShipRepository.class);
        return shipRepository.findAll();
    }

}

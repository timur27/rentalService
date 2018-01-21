package com.example.demo.Observer;

import com.example.demo.models.Ship;

public abstract class Observer {
    protected Ship ship;
    public abstract void update();
}

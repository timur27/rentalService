package com.example.demo.models;


import com.example.demo.ApplicationContextHolder;
import com.example.demo.Facade.ObjectToRent;
import com.example.demo.Observer.Observer;
import com.example.demo.repository.ShipRepository;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ship{
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String product;
    protected String productType;
    private String status;
    private double price;
    private String begin;
    private String end;
    @Transient
    private Observer observer;

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public Ship(String type, String product) {
        this.type = type;
        this.product = product;
    }

    public String getProductType() {
        return productType;
    }

    public void attach(Observer observer){
        this.observer =observer;
    }
    public void setProductType(String productType) {
        this.productType = productType;
        notifyObserver();
    }

    public void notifyObserver(){
        this.observer.update();
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Ship(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


    public void save(){
        ShipRepository shipRepository = ApplicationContextHolder.getContext().getBean(ShipRepository.class);
        shipRepository.save(this);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}

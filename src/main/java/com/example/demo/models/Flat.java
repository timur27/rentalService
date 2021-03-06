package com.example.demo.models;

import com.example.demo.Adapter.RentalObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Flat{

    @Id @GeneratedValue
    private Long id;
    private String address;
    private int meters;
    private final String type = "flat";
    private double price;

    public Flat(String address, int meters, double price) {
        this.address = address;
        this.meters = meters;
        this.price = price;
    }

    public Flat(){};

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType(){
        return this.type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMeters() {
        return meters;
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }


    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", meters=" + meters +
                '}';
    }
}

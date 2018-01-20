package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Clothing {

    @Id
    @GeneratedValue
    private Long id;
    private String collection;
    private String model;

    public Clothing(String collection, String model) {
        this.collection = collection;
        this.model = model;
    }

    public Clothing(){};


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public String toString() {
        return "Clothing{" +
                "id=" + id +
                ", collection='" + collection + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}

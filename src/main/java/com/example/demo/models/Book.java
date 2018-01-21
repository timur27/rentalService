package com.example.demo.models;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.Facade.ObjectToRent;
import com.example.demo.Service.BookService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String author;
    private double price;
    private String status;
    private String year;
    private final String type = "book";



    public Book(){};

    public Book(String name, String author, double price, String status, String year) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.status = status;
        this.year = year;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType(){
        return this.type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
package com.example.demo.Adapter;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.Service.BookService;
import com.example.demo.models.Flat;
import com.example.demo.repository.FlatRepository;

public class FlatAdapter extends RentalObject {
    private Flat flat;
    private FlatRepository flatRepository;

    public FlatAdapter(Flat flat){
        this.flat = flat;
        flatRepository = ApplicationContextHolder.getContext().getBean(FlatRepository.class);
    }

    @Override
    public void save(){
        flatRepository.save(this.flat);
    }

    @Override
    public String getType(){
        return flat.getType();
    }

}

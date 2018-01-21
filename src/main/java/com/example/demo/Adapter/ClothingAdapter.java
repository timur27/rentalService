package com.example.demo.Adapter;

import com.example.demo.ApplicationContextHolder;
import com.example.demo.models.Clothing;
import com.example.demo.models.Flat;
import com.example.demo.repository.ClothingRepository;
import com.example.demo.repository.FlatRepository;

public class ClothingAdapter extends RentalObject {
    private Clothing clothing;
    private ClothingRepository clothingRepository;

    public ClothingAdapter(Clothing clothing){
        this.clothing = clothing;
        clothingRepository = ApplicationContextHolder.getContext().getBean(ClothingRepository.class);
    }

    @Override
    public void save(){
        clothingRepository.save(this.clothing);
    }

    @Override
    public String getType(){
        return clothing.getType();
    }

}

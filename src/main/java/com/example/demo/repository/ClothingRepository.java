package com.example.demo.repository;

import com.example.demo.models.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {
    public Clothing findByCollection(String collection);
}

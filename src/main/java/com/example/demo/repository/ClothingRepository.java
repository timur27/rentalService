package com.example.demo.repository;

import com.example.demo.models.Clothing;
import org.springframework.data.repository.CrudRepository;

public interface ClothingRepository extends CrudRepository<Clothing, Long> {
}

package com.example.demo.repository;

import com.example.demo.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long>{
    public Ship findByProduct(String product);
}

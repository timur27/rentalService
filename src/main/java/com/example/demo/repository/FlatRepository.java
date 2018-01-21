package com.example.demo.repository;

import com.example.demo.models.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FlatRepository extends JpaRepository<Flat, Long> {
    public Flat findByAddress(String address);
}

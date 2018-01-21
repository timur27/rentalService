package com.example.demo.repository;

import com.example.demo.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ShipRepository extends JpaRepository<Ship, Long>{
    public Ship findByProduct(String product);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Ship ship set ship.end =:date where ship.product =:bookName")
    public void changeBookStatus(@Param("bookName") String bookName, @Param("date") String endDate);
}

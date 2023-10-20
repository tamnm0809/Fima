package com.fima.repository;

import com.fima.entity.Categories;
import com.fima.entity.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {
    @Query(value = "SELECT * FROM PRICES WHERE PRICES = %:keyword%", nativeQuery = true)
    public List<Prices> findByPrices(@Param("keyword") double keyword);
}

package com.fima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fima.entity.Services;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

    @Query(value = "SELECT * FROM SERVICES WHERE NAME LIKE %:keyword%", nativeQuery = true)
    public List<Services> findAllByNameLike(@Param("keyword") String keyword);
}

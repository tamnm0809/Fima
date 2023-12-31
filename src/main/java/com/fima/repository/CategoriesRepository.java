package com.fima.repository;

import com.fima.entity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Query(value = "SELECT * FROM CATEGORIES WHERE NAME LIKE %:keyword%", nativeQuery = true)
    public Page<Categories> findAllByName(@Param("keyword") String keyword, Pageable pageable);

    public Categories findByName(String name);
}

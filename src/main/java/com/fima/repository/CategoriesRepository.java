package com.fima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fima.entity.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long>{

}

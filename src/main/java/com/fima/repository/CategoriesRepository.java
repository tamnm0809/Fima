package com.fima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fima.entity.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

	@Query("SELECT c FROM Categories c WHERE TRIM(LOWER(c.name)) LIKE LOWER(CONCAT('%', TRIM(:name), '%'))")
	public List<Categories> findAllByNameContainingIgnoreCase(@Param("name") String name);
//	@Query("SELECT c FROM Categories c WHERE LOWER(c.name) LIKE %:name%")
//    public List<Categories> findAllByNameContainingIgnoreCase(String name);
}

package com.fima.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
=======
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> af5eb660ac543a4043639597f5357470a68d03a4
import org.springframework.stereotype.Repository;

import com.fima.entity.Categories;

@Repository
<<<<<<< HEAD
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

	@Query(value = "SELECT NAME FROM CATEGORIES", nativeQuery = true)
	List<String> getCategoriesByName();
=======
public interface CategoriesRepository extends JpaRepository<Categories, Long>{

>>>>>>> af5eb660ac543a4043639597f5357470a68d03a4
}

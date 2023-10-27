package com.fima.repository;

import com.fima.entity.Services;
import com.fima.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, String> {
    @Query(value = "SELECT * FROM USERS WHERE NAME LIKE %:keyword%", nativeQuery = true)
    public Page<Users> findAllByNameLike(@Param("keyword") String keyword, Pageable pageable);

    public Users findByName(String name);
}

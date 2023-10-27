package com.fima.repository;

import com.fima.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    @Query(value = "SELECT * FROM STAFFS WHERE NAME LIKE %:keyword%", nativeQuery = true)
    public Page<Staff> findAllByNameLike(@Param("keyword") String keyword, Pageable pageable);

    public Staff findByName(String name);
}

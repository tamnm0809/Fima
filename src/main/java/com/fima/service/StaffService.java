package com.fima.service;

import com.fima.entity.Staff;
import com.fima.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    public List<Staff> getAllStaff();

    public Page<Staff> getAllStaffPage(Integer pageNo);

    public Staff addStaff(Staff staff);

    public void updateStaff(Staff staff);

    public void deleteStaff(String id_staff);

    public Optional<Staff> getStaffById(String id_staff);

    public Page<Staff> findByNameLike(String name, int pageNo);

    public Staff findByName(String name);
}

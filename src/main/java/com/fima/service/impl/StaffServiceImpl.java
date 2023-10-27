package com.fima.service.impl;

import com.fima.entity.Staff;
import com.fima.entity.Users;
import com.fima.repository.StaffRepository;
import com.fima.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Page<Staff> getAllStaffPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 13);
        return staffRepository.findAll(pageable);
    }

    @Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void updateStaff(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(String id_staff) {
        staffRepository.deleteById(id_staff);
    }

    @Override
    public Optional<Staff> getStaffById(String id_staff) {
        return staffRepository.findById(id_staff);
    }

    @Override
    public Page<Staff> findByNameLike(String name, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 13);
        return staffRepository.findAllByNameLike(name, pageable);
    }

    @Override
    public Staff findByName(String name) {
        return staffRepository.findByName(name);
    }
}

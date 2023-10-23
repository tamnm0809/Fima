package com.fima.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fima.entity.Services;
import com.fima.repository.ServicesRepository;
import com.fima.service.ServicesService;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    ServicesRepository serviceRepository;

    @Override
    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Page<Services> getAllServicesPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 13);
        return serviceRepository.findAll(pageable);
    }

    @Override
    public Services addServices(Services services) {
        if (services != null) {
            return serviceRepository.save(services);
        }
        return null;
    }

    @Override
    public void updateServices(Services services) {
        serviceRepository.save(services);
    }

    @Override
    public void deleteServices(long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Optional<Services> getServicesById(long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public Page<Services> findByNameLike(String name, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 13);
        Page<Services> listPage = serviceRepository.findAllByNameLike(name, pageable);
        return listPage;
    }

    @Override
    public Services findByName(String name) {
        return serviceRepository.findByName(name);
    }

    @Override
    public Services findByDescriptions(String descriptions) {
        return serviceRepository.findByDescriptions(descriptions);
    }

}

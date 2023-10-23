package com.fima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.fima.entity.Services;
import org.springframework.data.domain.Pageable;

public interface ServicesService {

    public List<Services> getAllServices();

    public Page<Services> getAllServicesPage(Integer PageNo);

    public Services addServices(Services services);

    public void updateServices(Services services);

    public void deleteServices(long id);

    public Optional<Services> getServicesById(long id);

    public Page<Services> findByNameLike(String name, int pageNo);

    public Services findByName(String name);

    public Services findByDescriptions(String descriptions);

}

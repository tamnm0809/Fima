package com.fima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Services addServices(Services services) {
		if (services != null) {
			return serviceRepository.save(services);
		}
		return null;
	}

	@Override
	public Services updateServices(long id, Services services) {
		if (services != null) {
			Services services1 = serviceRepository.getById(id);
			if (services1 != null) {
				services1.setName(services.getName());
				services1.setPrices(services.getPrices());
				services1.setDescriptions(services.getDescriptions());
				return serviceRepository.save(services1);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteServices(long id) {
		if (id > 1) {
			Services services = serviceRepository.getById(id);
			if (services != null) {
				serviceRepository.delete(services);
				return true;
			}
		}
		return false;
	}

	@Override
	public Services getServicesById(long id) {
		return serviceRepository.getById(id);
	}

}

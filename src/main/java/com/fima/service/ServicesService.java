package com.fima.service;

import java.util.List;

import com.fima.entity.Services;

public interface ServicesService {

	public List<Services> getAllServices();

	public Services addServices(Services services);
	
	public Services updateServices(long id,Services services);
	
	public Boolean deleteServices(long id);
	
	public Services getServicesById(long id);

}

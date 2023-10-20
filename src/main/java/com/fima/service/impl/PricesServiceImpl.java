package com.fima.service.impl;

import com.fima.entity.Prices;
import com.fima.repository.PricesRepository;
import com.fima.service.PricesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PricesServiceImpl implements PricesServices {

    @Autowired
    public PricesRepository pricesRepository;

    @Override
    public List<Prices> getAllPrices() {
        return pricesRepository.findAll();
    }

    @Override
    public Page<Prices> getAllPricesPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 3);
        return pricesRepository.findAll(pageable);
    }

    @Override
    public Prices addPrices(Prices prices) {
        return pricesRepository.save(prices);
    }

    @Override
    public Prices updatePrices(Prices prices) {
        return pricesRepository.save(prices);
    }

    @Override
    public void deletePrices(long id) {
        pricesRepository.deleteById(id);
    }

    @Override
    public Optional<Prices> getPricesById(long id) {
        return pricesRepository.findById(id);
    }

    @Override
    public List<Prices> findByPrices(double keyword) {
        return pricesRepository.findByPrices(keyword);
    }
}

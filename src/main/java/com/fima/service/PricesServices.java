package com.fima.service;

import com.fima.entity.Prices;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PricesServices {

    public List<Prices> getAllPrices();

    public Page<Prices> getAllPricesPage(Integer PageNo);

    public Prices addPrices(Prices prices);

    public Prices updatePrices(Prices prices);

    public void deletePrices(long id);

    public Optional<Prices> getPricesById(long id);

    List<Prices> findByPrices(double keyword);
}

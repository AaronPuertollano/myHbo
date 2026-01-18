package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepo countryRepo;

    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    public Optional<Country> findById(Integer id) {
        return countryRepo.findById(id);
    }

    public Country save(Country country) {
        return countryRepo.save(country);
    }

    public void delete(Integer id) {
        countryRepo.deleteById(id);
    }
}

package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Person;
import com.esliceu.myHbo.model.ProductionCompany;
import com.esliceu.myHbo.repo.ProductionCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionCompanyService {

    @Autowired
    ProductionCompanyRepo productionCompanyRepo;

    public List<ProductionCompany> findAll() {
        return productionCompanyRepo.findAll();
    }

    public Optional<ProductionCompany> findById(Integer id) {
        return productionCompanyRepo.findById(id);
    }

    public ProductionCompany save(ProductionCompany productionCompany) {
        return productionCompanyRepo.save(productionCompany);
    }

    public void delete(Integer id) {
        productionCompanyRepo.deleteById(id);
    }

    public Integer getNextId() {
        Integer maxId = productionCompanyRepo.findMaxId();
        return (maxId == null) ? 1 : maxId + 1;
    }

    public ProductionCompany update(Integer id, String companyName) {

        ProductionCompany company = productionCompanyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setCompanyName(companyName);
        return productionCompanyRepo.save(company);
    }

}

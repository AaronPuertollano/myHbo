package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionCompanyRepo extends JpaRepository<ProductionCompany, Integer> {
    @Query("SELECT MAX(g.id) FROM ProductionCompany g")
    Integer findMaxId();
}

package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Genre;
import com.esliceu.myHbo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m JOIN m.productionCompanies pc WHERE pc.id = :companyId")
    Set<Movie> findByProductionCompanyId(@Param("companyId") Integer companyId);
}

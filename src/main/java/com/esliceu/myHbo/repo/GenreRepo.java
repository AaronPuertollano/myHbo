package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepo extends JpaRepository <Genre, Integer> {
    @Query("SELECT MAX(g.id) FROM Genre g")
    Integer findMaxId();
}

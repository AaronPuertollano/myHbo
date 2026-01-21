package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepo extends JpaRepository<Gender, Integer> {
    @Query("SELECT MAX(g.id) FROM Gender g")
    Integer findMaxId();
}

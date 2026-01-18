package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepo extends JpaRepository<Gender, Integer> {
}

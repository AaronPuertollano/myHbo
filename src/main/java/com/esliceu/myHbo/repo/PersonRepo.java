package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    @Query(value = "SELECT COALESCE(MAX(person_id), 0) + 1 FROM person", nativeQuery = true)
    Integer getNextId();
}

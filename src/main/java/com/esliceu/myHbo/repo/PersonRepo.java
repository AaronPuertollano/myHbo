package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    @Query(value = "SELECT COALESCE(MAX(person_id), 0) + 1 FROM person", nativeQuery = true)
    Integer getNextId();

    List<Person> findByPersonNameContainingIgnoreCase(String name);

    List<Person> findTop20ByPersonNameContainingIgnoreCase(String name);
}

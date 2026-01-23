package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Language;
import com.esliceu.myHbo.model.Person;
import com.esliceu.myHbo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepo personRepo;

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Optional<Person> findById(Integer id) {
        return personRepo.findById(id);
    }

    public Person save(Person person) {
        return personRepo.save(person);
    }

    public void delete(Integer id) {
        personRepo.deleteById(id);
    }

    public Integer getNextId() {
        return personRepo.getNextId();
    }

    public List<Person> findByName(String term) {
        return personRepo.findByPersonNameContainingIgnoreCase(term);
    }

    public Person update(Integer id, String personName) {

        Person person = personRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        person.setPersonName(personName);
        return personRepo.save(person);
    }
}

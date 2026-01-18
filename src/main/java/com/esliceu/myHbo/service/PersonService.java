package com.esliceu.myHbo.service;

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

    public Person save(Person language) {
        return personRepo.save(language);
    }

    public void delete(Integer id) {
        personRepo.deleteById(id);
    }
}

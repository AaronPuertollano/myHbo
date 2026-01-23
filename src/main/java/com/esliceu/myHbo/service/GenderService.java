package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Department;
import com.esliceu.myHbo.model.Gender;
import com.esliceu.myHbo.repo.GenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {

    @Autowired
    GenderRepo genderRepo;

    public List<Gender> findAll() {
        return genderRepo.findAll();
    }

    public Optional<Gender> findById(Integer id) {
        return genderRepo.findById(id);
    }

    public Gender save(Gender gender) {
        return genderRepo.save(gender);
    }

    public void delete(Integer id) {
        genderRepo.deleteById(id);
    }

    public Integer getNextId() {
        Integer maxId = genderRepo.findMaxId();
        return (maxId == null) ? 1 : maxId + 1;
    }

    public Gender update(Integer id, String genderName) {

        Gender gender = genderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Gender not found"));
        gender.setGender(genderName);

        return genderRepo.save(gender);
    }
}
